package com.example.kideng.ui.fragments;

import android.app.Activity;

import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.example.kideng.db.dao.WordDao;
import com.example.kideng.db.entities.Word;
import com.example.kideng.ui.activities.GameActivity;

import java.util.Random;


public class GameFragment extends Fragment {

    private static final String ARG_L = "language";

    private TextView mCounterTimeTv;
    private EditText mTranslateTv;
    private ImageView mTick, mCross;
    private String mTranslate;
    private String mLanguage;
    private TextView mWordTv;

    private int rCounter = 0, wCounter = 0, tCounter = 0;

    public static GameFragment newInstance(String language) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putString(ARG_L, language);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mLanguage = getArguments().getString(ARG_L);
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

        mTranslate = "";
        setWord();

        Button applyButton = view.findViewById(R.id.answer_btn);
        Button skipButton = view.findViewById(R.id.skip_btn);
        applyButton.setOnClickListener(this::onApplyClick);
        skipButton.setOnClickListener(this::onSkipClick);

        new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(final long l) {
                mCounterTimeTv.setText("Осталось времени: " + (int) (l * .001f));
            }

            @Override
            public void onFinish() {
                mCounterTimeTv.setText("");
                onApplyClick(view);
                Activity activity = getActivity();
                if (activity instanceof GameActivity) {
                    String rString = String.valueOf(rCounter);
                    String wString = String.valueOf(wCounter);
                    String tString = String.valueOf(tCounter);
                    ((GameActivity) activity).onGameStop(rString, wString, tString, mLanguage);
                }
            }
        }.start();
        return view;
    }

    private void onSkipClick(View view) {
        mTranslate = "";
        mTranslateTv.setText("");
        mTick.setVisibility(View.INVISIBLE);
        mCross.setVisibility(View.INVISIBLE);
        setWord();
        tCounter++;
    }

    private void onApplyClick(View view) {
        if (mTranslateTv.getText().toString().toLowerCase().equals(mTranslate)) {
            rCounter++;
            mTranslate = "";
            mTranslateTv.setText("");
            onSkipClick(getView());
            mTick.setVisibility(View.VISIBLE);
            mCross.setVisibility(View.INVISIBLE);
        } else {
            mCross.setVisibility(View.VISIBLE);
            mTick.setVisibility(View.INVISIBLE);
            mTranslateTv.setText("");
            wCounter++;
            tCounter++;
        }
    }

    private void setWord() {
        AppDatabase db = App.getInstance().getDatabase();
        WordDao wordDao = db.wordDao();
        Word word = wordDao.getRandWord();
        if (mLanguage.equals("English")) {
            mWordTv.setText(word.getWordEng());
            mTranslate += word.getWordRus();
        } else {
            mWordTv.setText(word.getWordRus());
            mTranslate += word.getWordEng();
        }


    }
}