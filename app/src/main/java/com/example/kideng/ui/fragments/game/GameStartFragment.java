package com.example.kideng.ui.fragments.game;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.kideng.App;
import com.example.kideng.R;
import com.example.kideng.db.AppDatabase;
import com.example.kideng.db.dao.ThemeDao;
import com.example.kideng.ui.activities.GameActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collections;

public class GameStartFragment extends Fragment {

    TextView mThemesTv;

    RadioButton ruButton;
    RadioButton engButton;
    RadioButton timeButton;
    RadioButton wordButton;

    TextInputEditText mGoal;
    TextInputLayout mGoalLayout;


    ArrayList<Integer> themeList = new ArrayList<>();
    AppDatabase db = App.getInstance().getDatabase();
    ThemeDao themeDao = db.themeDao();
    String[] themeNamesList = themeDao.getAllThemes();
    boolean[] selectedThemes = new boolean[themeNamesList.length];

    public static GameStartFragment newInstance() {
        GameStartFragment fragment = new GameStartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.game_fragment_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mThemesTv = view.findViewById(R.id.choose_themes_tv);
        mGoalLayout = view.findViewById(R.id.goal_in_layout);
        mGoal = view.findViewById(R.id.number_goal);

        ruButton = view.findViewById(R.id.ru_btn);
        engButton = view.findViewById(R.id.eng_btn);

        timeButton = view.findViewById(R.id.time_btn);
        wordButton = view.findViewById(R.id.word_btn);

        Button startButton = view.findViewById(R.id.start_bt);
        startButton.setOnClickListener(this::onStartClick);

        timeButton.setOnClickListener(view12 -> mGoalLayout.setSuffixText("мин"));

        wordButton.setOnClickListener(view12 -> mGoalLayout.setSuffixText("слов"));

        mThemesTv.setOnClickListener(view1 -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    getContext()
            );

            builder.setTitle("Выбор темы");
            builder.setCancelable(false);

            builder.setMultiChoiceItems(themeNamesList, selectedThemes, (dialogInterface, i, b) -> {
                if (b) {
                    themeList.add(i);
                    Collections.sort(themeList);
                } else {
                    themeList.remove(i);
                }
            });

            builder.setPositiveButton("OK", (dialogInterface, i) -> mThemesTv.setText("Темы выбраны"));

            builder.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());

            builder.setNeutralButton("Clear all", (dialogInterface, i) -> {
                for (int j = 0; j < selectedThemes.length; j++) {
                    selectedThemes[j] = false;
                    themeList.clear();
                    mThemesTv.setText("");
                }
            });
            builder.show();
        });


    }

    private void onStartClick(View view) {
        String translate = ruButton.isActivated() ? "RU" : "ENG";
        String goal = timeButton.isActivated() ? "time" : "words";
        int value = Integer.parseInt(String.valueOf(mGoal.getText()));

        Activity activity = getActivity();
        if (activity instanceof GameActivity) {
            ((GameActivity)activity).onGameCountDown(translate, goal);
        }
    }

}