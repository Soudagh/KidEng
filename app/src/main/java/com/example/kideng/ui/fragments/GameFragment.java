package com.example.kideng.ui.fragments;

import android.app.Activity;
import android.database.Cursor;
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

import com.example.kideng.R;
import com.example.kideng.db.legacy.ThemeDBHelper;
import com.example.kideng.ui.activities.GameActivity;

import java.util.Random;


public class GameFragment extends Fragment {

    private static final String ARG_L = "language";

    private TextView mWordTv, mCounterTimeTv;
    private EditText mTranslateTv;
    private ImageView mTick, mCross;
    private String mTranslate;
    private String mLanguage;
    private Random mRandom;

    private int id;
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
        mRandom = new Random();

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
        setWord();
        tCounter++;
    }

    private void onApplyClick(View view) {
        if (mTranslateTv.getText().toString().equals(mTranslate)) {
            mTick.setVisibility(View.VISIBLE);
            mCross.setVisibility(View.INVISIBLE);
            rCounter++;
            mTranslate = "";
            mTranslateTv.setText("");
            onSkipClick(getView());
        } else {
            mCross.setVisibility(View.VISIBLE);
            mTick.setVisibility(View.INVISIBLE);
            mTranslateTv.setText("");
            wCounter++;
            tCounter++;
        }

    }

    //TODO: ой сразу две ошибки
    // 1. Обращение в главном потоке
    // 2. Лучше кэшировать 1-3 слова, потому что доступ может быть условно 2 секунды
    // и не очень праивльно будет заставлять пользователя ждать 2 секунды
    private void setWord() {
        ThemeDBHelper dbHelper = new ThemeDBHelper(getActivity());
        id = mRandom.nextInt(dbHelper.getDBNoteCount() + 1);
        Cursor cursor = dbHelper.getWord1(id);
        if (!cursor.isAfterLast()) {
            if (mLanguage.equals("English")) {
                mWordTv.setText(cursor.getString(cursor.getColumnIndex(ThemeDBHelper.COLUMN_ENG)));
                mTranslate += cursor.getString(cursor.getColumnIndex(ThemeDBHelper.COLUMN_RU));
            } else {
                mWordTv.setText(cursor.getString(cursor.getColumnIndex(ThemeDBHelper.COLUMN_RU)));
                mTranslate += cursor.getString(cursor.getColumnIndex(ThemeDBHelper.COLUMN_ENG));
            }
        }

    }


}