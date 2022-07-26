package com.example.kideng.ui.fragments.game;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kideng.App;
import com.example.kideng.R;
import com.example.kideng.db.AppDatabase;
import com.example.kideng.db.dao.ThemeDao;
import com.example.kideng.db.dao.WordDao;
import com.example.kideng.ui.activities.GameActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GameStartFragment extends Fragment {

    View translateLayout;
    View goalLayout;
    View valueLayout;

    TextView mThemesTv;
    TextView mEmptyTv;

    RadioButton ruButton;
    RadioButton engButton;
    RadioButton timeButton;
    RadioButton wordButton;

    Button emptyButton;

    TextInputEditText mGoal;
    TextInputLayout mGoalLayout;

    String translate;
    String goal;

    ArrayList<Integer> themeList = new ArrayList<>();
    ArrayList<Integer> themeList1 = new ArrayList<>();

    AppDatabase db = App.getInstance().getDatabase();
    ThemeDao themeDao = db.themeDao();
    WordDao wordDao = db.wordDao();

    String[] themeNamesList = themeDao.getAllThemes();
    boolean[] selectedThemes = new boolean[themeNamesList.length];
    boolean[] disabledThemes = new boolean[themeNamesList.length];

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

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(view1 -> {
            Activity activity = getActivity();
            if (activity instanceof GameActivity) {
                ((GameActivity)activity).onHome();
            }
        });

        for (int i = 1; i <= themeNamesList.length; i++) {
            if (!wordDao.getByThemeId(i).isEmpty()) {
                disabledThemes[i - 1] = true;
            }
        }

        translateLayout = view.findViewById(R.id.translate_layout);
        goalLayout = view.findViewById(R.id.goal_layout);
        valueLayout = view.findViewById(R.id.value_layout);

        mThemesTv = view.findViewById(R.id.choose_themes_tv);
        mGoalLayout = view.findViewById(R.id.goal_in_layout);
        mGoal = view.findViewById(R.id.number_goal);
        mEmptyTv = view.findViewById(R.id.empty_tv);

        emptyButton = view.findViewById(R.id.empty_bt);

        ruButton = view.findViewById(R.id.ru_btn);
        engButton = view.findViewById(R.id.eng_btn);

        timeButton = view.findViewById(R.id.time_btn);
        wordButton = view.findViewById(R.id.word_btn);

        emptyButton.setOnClickListener(this::onEmpty);

        if (wordDao.getAllWords().size() == 0) {
            emptyButton.setVisibility(View.VISIBLE);
            mEmptyTv.setVisibility(View.VISIBLE);
            translateLayout.setVisibility(View.INVISIBLE);
            goalLayout.setVisibility(View.INVISIBLE);
            valueLayout.setVisibility(View.INVISIBLE);
        }

        timeButton.setOnClickListener(view12 -> mGoalLayout.setSuffixText(getString(R.string.minutes)));
        wordButton.setOnClickListener(view12 -> mGoalLayout.setSuffixText(getString(R.string.words_count)));

        Button startButton = view.findViewById(R.id.start_bt);
        startButton.setOnClickListener(this::onStartClick);

        mThemesTv.setOnClickListener(view1 -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    getContext(), R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Background
            );

            builder.setTitle(R.string.theme_selection);
            builder.setCancelable(false);

            builder.setMultiChoiceItems(themeNamesList, selectedThemes, (dialogInterface, i, b) -> {
                if (b) {
                    themeList.add(i);
                    themeList1.add(i + 1);
                    Collections.sort(themeList);
                } else {
                    themeList.remove((Integer) i);
                    themeList1.remove((Integer) (i + 1));
                }
            });

            builder.setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> mThemesTv.setText(R.string.themes_choosed));

            builder.setNegativeButton(R.string.cancel, (dialogInterface, i) -> dialogInterface.dismiss());

            builder.setNeutralButton(R.string.clear, (dialogInterface, i) -> {
                Arrays.fill(selectedThemes, false);

                themeList.clear();
                mThemesTv.setText("");
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.getListView().setOnHierarchyChangeListener(
                    new ViewGroup.OnHierarchyChangeListener() {
                        @Override
                        public void onChildViewAdded(View parent, View child) {
                            CharSequence text = ((AppCompatCheckedTextView)child).getText();
                            int itemIndex = Arrays.asList(themeNamesList).indexOf(text);
                            boolean disabledTheme = disabledThemes[itemIndex];
                            if (!disabledTheme) {
                                child.setOnClickListener(view2 -> {
                                    child.setEnabled(false);
                                    Toast.makeText(getActivity(), getString(R.string.theme_empty), Toast.LENGTH_LONG).show();
                                });
                            }
                        }

                        @Override
                        public void onChildViewRemoved(View view, View view1) {
                        }
                    });

            alertDialog.show();
        });
    }

    private void onEmpty(View view) {
        Activity activity = getActivity();
        if (activity instanceof GameActivity) {
            ((GameActivity)activity).onEmpty();
        }

    }

    private void onStartClick(View view) {
        String duration = String.valueOf(mGoal.getText());

        translate = ruButton.isChecked() ? "RU" : "ENG";
        goal = timeButton.isChecked() ? "time" : "words";

        if ((ruButton.isChecked() || engButton.isChecked())
                && (timeButton.isChecked() || wordButton.isChecked())
                && (duration.length() > 0)
                && (Integer.parseInt(duration) > 0)) {
            Activity activity = getActivity();
            if (activity instanceof GameActivity) {
                ((GameActivity)activity).onGameCountDown(translate, goal, duration, themeList1);
            }
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.didnt_choosed));
            if (!(ruButton.isChecked() || engButton.isChecked())) {
                sb.append(getString(R.string.translate_snack));

                if (!(timeButton.isChecked() || wordButton.isChecked())) {
                    sb.append(getString(R.string.goal_snack));
                }
                if (duration.isEmpty()) {
                    sb.append(getString(R.string.value_goal_snack));
                }
            } else if (!(timeButton.isChecked() || wordButton.isChecked())) {
                sb.append(getString(R.string.goal_snack));
                if (duration.isEmpty()) {
                    sb.append(getString(R.string.value_goal_snack));
                }
            } else if (duration.isEmpty()) {
                sb.append(getString(R.string.value_goal_snack_without));
            }
            Snackbar snackbar = Snackbar.make(view, sb, Snackbar.LENGTH_LONG);
            snackbar.setTextColor(getResources().getColor(R.color.white));
            snackbar.show();
        }
    }
}


