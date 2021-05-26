package com.example.myproject;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import static com.example.myproject.ThemeDBHelper.WORDS_LIST_TABLE_NAME;


public class GameFragment extends Fragment {

    ThemeDBHelper db;


   TextView wordTv, counterTimeTv;
   EditText translateTv;
   ImageView tick, cross;
   String translate;
   int rCounter = 0, wCounter = 0, tCounter = 0;

    public GameFragment() {

    }


    public static GameFragment newInstance() {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.game_fragment, container, false);

        db = new ThemeDBHelper(getContext());

        counterTimeTv = (TextView) view.findViewById(R.id.time_counter_tv);
        wordTv = (TextView) view.findViewById(R.id.word_tv);

        translateTv = (EditText) view.findViewById(R.id.translate_et);
        translate = translateTv.getText().toString();

        tick = (ImageView) view.findViewById(R.id.tick_iv);
        cross = (ImageView) view.findViewById(R.id.cross_iv);

        tick.setVisibility(View.INVISIBLE);
        cross.setVisibility(View.INVISIBLE);

        //wordTv.setText((databaseHelper.getEngWord(id)).getWordEng());

        Button applyButton = (Button) view.findViewById(R.id.answer_btn);
        Button skipButton = (Button) view.findViewById(R.id.skip_btn);
        applyButton.setOnClickListener(this::onApplyClick);
        skipButton.setOnClickListener(this::onSkipClick);


        new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(final long l) {
                counterTimeTv.setText("Осталось времени: "  + (int) (l * .001f));

            }

            @Override
            public void onFinish() {
                counterTimeTv.setText("");
                Activity activity = getActivity();
                if (activity instanceof GameActivity) {
                    ((GameActivity)activity).onGameStop(rCounter, wCounter, tCounter);
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
        ThemeDBHelper databaseHelper;
        databaseHelper = new ThemeDBHelper(getContext());

        String word = "";


        Cursor cursor = databaseHelper.getReadableDatabase().query(databaseHelper.getDatabaseName(), new String[] {"END", "RUS"}, null, null, null, null, null, null);
        while (!cursor.isAfterLast()) {
            word = cursor.getString(1);
            cursor.moveToNext();
        }
        cursor.close();

        wordTv.setText(word);
    }

    private void onApplyClick(View view) {

        if (translate.equals(wordTv.getText().toString())) {
            tick.setVisibility(View.VISIBLE);
            cross.setVisibility(View.INVISIBLE);
            rCounter++;
        } else {
            cross.setVisibility(View.VISIBLE);
            tick.setVisibility(View.INVISIBLE);
            wCounter++;
        }
        tCounter++;
    }
}