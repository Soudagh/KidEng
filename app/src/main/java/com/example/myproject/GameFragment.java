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

    TextView wordTv, counterTimeTv;
    EditText translateTv;
    ImageView tick, cross;
    String translate;
    Random random = new Random();
    int id;
    int rCounter = 0, wCounter = 0, tCounter = 0;

    public GameFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.game_fragment, container, false);

        counterTimeTv = (TextView) view.findViewById(R.id.time_counter_tv);
        wordTv = (TextView) view.findViewById(R.id.word_tv);

        translateTv = (EditText) view.findViewById(R.id.translate_et);

        tick = (ImageView) view.findViewById(R.id.tick_iv);
        cross = (ImageView) view.findViewById(R.id.cross_iv);

        tick.setVisibility(View.INVISIBLE);
        cross.setVisibility(View.INVISIBLE);

        translate = "";
        random = new Random();

        ThemeDBHelper dbHelper = new ThemeDBHelper(getActivity());
        id = random.nextInt(dbHelper.getDBNoteCount() + 1);
        Cursor cursor = dbHelper.getWord1(id);
        if (!cursor.isAfterLast()) {
            wordTv.setText(cursor.getString(cursor.getColumnIndex(ThemeDBHelper.COLUMN_ENG)));
            translate += cursor.getString(cursor.getColumnIndex(ThemeDBHelper.COLUMN_RU));
        }

        Button applyButton = (Button) view.findViewById(R.id.answer_btn);
        Button skipButton = (Button) view.findViewById(R.id.skip_btn);
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
                Toast.makeText(getActivity(), "tCounter = " + tCounter, Toast.LENGTH_SHORT).show();
                Activity activity = getActivity();
                if (activity instanceof GameActivity) {
                    ((GameActivity) activity).onGameStop(rCounter, wCounter, tCounter);
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
        ThemeDBHelper dbHelper = new ThemeDBHelper(getActivity());
        id = random.nextInt(dbHelper.getDBNoteCount() + 1);
        Cursor cursor = dbHelper.getWord1(id);
        if (!cursor.isAfterLast()) {
            translate = "";
            wordTv.setText(cursor.getString(cursor.getColumnIndex(ThemeDBHelper.COLUMN_ENG)));
            translate += cursor.getString(cursor.getColumnIndex(ThemeDBHelper.COLUMN_RU));
            translateTv.setText("");
        }
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
        }
        tCounter++;
    }
}