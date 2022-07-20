package com.example.kideng.ui.fragments.game;

import android.app.Activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.kideng.App;
import com.example.kideng.R;
import com.example.kideng.db.AppDatabase;
import com.example.kideng.db.dao.ThemeDao;
import com.example.kideng.db.dao.WordDao;
import com.example.kideng.db.entities.Word;
import com.example.kideng.ui.activities.GameActivity;

import java.util.ArrayList;
import java.util.HashSet;

public class GameFragment extends Fragment {

    private static final String ARG_L = "translate";
    private static final String ARG_G = "goal";
    private static final String ARG_D = "duration";
    private static final String ARG_LIST = "themeList";

    AppDatabase db = App.getInstance().getDatabase();
    WordDao wordDao = db.wordDao();
    ThemeDao themeDao = db.themeDao();

    private TextView mCounterTimeTv;
    private TextView mWordTv;
    private EditText mTranslateTv;

    private ImageView mTick, mCross;

    private String rightAnswer;
    private String langMode;
    private String goal;
    private String duration;
    private ArrayList<Integer> themeIds;
    private ArrayList<Integer> skippedWords = new ArrayList<>();

    private int rCounter = 0, wCounter = 0, tCounter = 0;

    public static GameFragment newInstance(String translate, String goal, String duration, ArrayList<Integer> themeList) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putString(ARG_L, translate);
        args.putString(ARG_G, goal);
        args.putString(ARG_D, duration);
        args.putIntegerArrayList(ARG_LIST, themeList);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            langMode = getArguments().getString(ARG_L);
            goal = getArguments().getString(ARG_G);
            duration = getArguments().getString(ARG_D);
            themeIds = getArguments().getIntegerArrayList(ARG_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.game_fragment, container, false);

        mCounterTimeTv = view.findViewById(R.id.time_counter_tv);
        mWordTv = view.findViewById(R.id.word_tv);
        mTranslateTv = view.findViewById(R.id.translate_et);
        mTick = view.findViewById(R.id.tick_iv);
        mCross = view.findViewById(R.id.cross_iv);

        mTick.setVisibility(View.INVISIBLE);
        mCross.setVisibility(View.INVISIBLE);

        rightAnswer = "";
        setWord();

        Button applyButton = view.findViewById(R.id.answer_btn);
        Button skipButton = view.findViewById(R.id.skip_btn);
        applyButton.setOnClickListener(this::onApplyClick);
        skipButton.setOnClickListener(this::onSkipClick);

        if (goal.equals("time")) {
            new CountDownTimer(60000L * Integer.parseInt(duration), 1000) {
                @Override
                public void onTick(final long l) {
                    mCounterTimeTv.setText("Осталось времени: " + (int) (l * .001f));
                }

                @Override
                public void onFinish() {
                    onApplyClick(view);
                    endGame();
                }
            }.start();
        } else {
            mCounterTimeTv.setText("Осталось слов: " + (Integer.parseInt(duration) - rCounter));
        }
        return view;
    }

    private void onSkipClick(View view) {
        Word word = wordDao.getWordByName(String.valueOf(mWordTv.getText()));
        if (!skippedWords.contains(word.getId())) skippedWords.add(word.getId());
        rightAnswer = "";
        mTranslateTv.setText("");
        mTick.setVisibility(View.INVISIBLE);
        mCross.setVisibility(View.INVISIBLE);
        setWord();
    }

    private void onApplyClick(View view) {
        if (mTranslateTv.getText().toString().toLowerCase().equals(rightAnswer)) {
            rCounter++;
            if (goal.equals("words") ) {
                if (rCounter == Integer.parseInt(duration)) {
                    endGame();
                }
                mCounterTimeTv.setText("Осталось слов: " + (Integer.parseInt(duration) - rCounter));
            }
            rightAnswer = "";
            mTranslateTv.setText("");
            onSkipClick(getView());
            mTick.setVisibility(View.VISIBLE);
            mCross.setVisibility(View.INVISIBLE);
        } else {
            mCross.setVisibility(View.VISIBLE);
            mTick.setVisibility(View.INVISIBLE);
            mTranslateTv.setText("");
            wCounter++;
        }
    }

    private void setWord() {
        Word word;
        if (themeIds.isEmpty()) {
            themeIds.addAll(themeDao.getAllIds());
        }

        tCounter++;

        word = wordDao.getRandWord(themeIds);
        if (langMode.equals("ENG")) {
            mWordTv.setText(word.getWordEng());
            rightAnswer += word.getWordRus();
        } else {
            mWordTv.setText(word.getWordRus());
            rightAnswer += word.getWordEng();
        }
    }

    private void endGame() {
        mCounterTimeTv.setText("");
        Activity activity = getActivity();
        if (activity instanceof GameActivity) {
            String rString = String.valueOf(rCounter);
            String wString = String.valueOf(wCounter);
            String tString = String.valueOf(tCounter);
            ((GameActivity) activity).onGameStop(rString, wString, tString, langMode, goal, duration, themeIds, skippedWords);
        }
    }
}