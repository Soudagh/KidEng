package com.example.kideng.fragments;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kideng.R;
import com.example.kideng.supporting.ThemeDBHelper;
import com.example.kideng.activities.GameActivity;

import java.util.Random;


public class GameFragment extends Fragment {

    private static final String ARG_L = "language";

    private String mLanguage;

    TextView wordTv, counterTimeTv;
    EditText translateTv;
    ImageView tick, cross;
    String translate;
    Random random = new Random();

    int id;
    int rCounter = 0, wCounter = 0, tCounter = 0;

    public GameFragment() {

    }

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

        counterTimeTv = view.findViewById(R.id.time_counter_tv);
        wordTv = view.findViewById(R.id.word_tv);

        translateTv = view.findViewById(R.id.translate_et);

        tick = view.findViewById(R.id.tick_iv);
        cross = view.findViewById(R.id.cross_iv);

        tick.setVisibility(View.INVISIBLE);
        cross.setVisibility(View.INVISIBLE);

        translate = "";
        random = new Random();

        setWord();

        Button applyButton = view.findViewById(R.id.answer_btn);
        Button skipButton = view.findViewById(R.id.skip_btn);
        applyButton.setOnClickListener(this::onApplyClick);
        skipButton.setOnClickListener(this::onSkipClick);

        new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(final long l) {
                counterTimeTv.setText("Осталось времени: " + (int) (l * .001f));

            }

            @Override
            public void onFinish() {
                counterTimeTv.setText("");
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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void onSkipClick(View view) {
        setWord();
        tCounter++;

    }

    private void onApplyClick(View view) {
        if (translateTv.getText().toString().equals(translate)) {
            tick.setVisibility(View.VISIBLE);
            cross.setVisibility(View.INVISIBLE);
            rCounter++;
            translate = "";
            translateTv.setText("");
            onSkipClick(getView());
        } else {
            cross.setVisibility(View.VISIBLE);
            tick.setVisibility(View.INVISIBLE);
            translateTv.setText("");
            wCounter++;
            tCounter++;
        }

    }

    private void setWord() {
        ThemeDBHelper dbHelper = new ThemeDBHelper(getActivity());
        id = random.nextInt(dbHelper.getDBNoteCount() + 1);
        Cursor cursor = dbHelper.getWord1(id);
        if (!cursor.isAfterLast()) {
            if (mLanguage.equals("English")) {
                wordTv.setText(cursor.getString(cursor.getColumnIndex(ThemeDBHelper.COLUMN_ENG)));
                translate += cursor.getString(cursor.getColumnIndex(ThemeDBHelper.COLUMN_RU));
            } else {
                wordTv.setText(cursor.getString(cursor.getColumnIndex(ThemeDBHelper.COLUMN_RU)));
                translate += cursor.getString(cursor.getColumnIndex(ThemeDBHelper.COLUMN_ENG));
            }
        }

    }


}