package com.example.kideng.ui.fragments.dict;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kideng.App;
import com.example.kideng.R;
import com.example.kideng.db.AppDatabase;
import com.example.kideng.db.dao.WordDao;
import com.example.kideng.db.entities.Word;
import com.example.kideng.ui.activities.DictWordActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Locale;

public class AddWordFragment extends Fragment {

    private int themeId;
    private int wordId;
    private String status;
    private String wordEng;
    private String wordRus;

    private TextInputEditText textInputEditTextEngEt;
    private TextInputEditText textInputEditTextRusEt;
    private TextInputLayout textInputEngLayout;
    private TextInputLayout textInputRusLayout;

    public static AddWordFragment newInstance(int wordId, int themeId, String status, String wordEng, String wordRus) {
        Bundle args = new Bundle();
        args.putInt("themeId", themeId);
        args.putInt("wordId", wordId);
        args.putString("status", status);
        args.putString("wordEng", wordEng);
        args.putString("wordRus", wordRus);
        AddWordFragment fragment = new AddWordFragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            themeId = getArguments().getInt("themeId");
            wordId = getArguments().getInt("wordId");
            status = getArguments().getString("status");
            wordEng = getArguments().getString("wordEng");
            wordRus = getArguments().getString("wordRus");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_word_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textInputEditTextEngEt = view.findViewById(R.id.textInputEngWordEt);
        textInputEditTextRusEt = view.findViewById(R.id.textInputRusWordEt);
        textInputEngLayout = view.findViewById(R.id.textInputEngWordLayout);
        textInputRusLayout = view.findViewById(R.id.textInputRusWordLayout);
        Button button = view.findViewById(R.id.word_bt);

        if (status.equals("add")) {
            button.setOnClickListener(this::addWord);
        } else {
            button.setText("Обновить");
            button.setOnClickListener(this::changeWord);

            textInputEditTextEngEt.setText(wordEng);
            textInputEditTextRusEt.setText(wordRus);
        }

    }

    private void changeWord(View view) {

        if (String.valueOf(textInputEditTextEngEt.getText()).equals("") ||
                String.valueOf(textInputEditTextRusEt.getText()).equals("")) {
            if (String.valueOf(textInputEditTextEngEt.getText()).equals("")) {
                textInputEngLayout.setErrorEnabled(true);
                textInputEngLayout.setError("Обязательное поле!");
            }
            if (String.valueOf(textInputEditTextRusEt.getText()).equals("")) {
                textInputRusLayout.setErrorEnabled(true);
                textInputRusLayout.setError("Обязательное поле!");
            }
        } else {
            AppDatabase db = App.getInstance().getDatabase();
            WordDao wordDao = db.wordDao();
            Word word = wordDao.getWordById(wordId);
            word.setWordEng(String.valueOf(textInputEditTextEngEt.getText()).toLowerCase());
            word.setWordRus(String.valueOf(textInputEditTextRusEt.getText()).toLowerCase());
            wordDao.update(word);
            Activity activity = getActivity();
            if (activity instanceof DictWordActivity) {
                ((DictWordActivity)activity).onBack(this.themeId);
            }
        }
    }

    public void addWord(View view) {
        int themeId = this.themeId;

        if (String.valueOf(textInputEditTextEngEt.getText()).equals("") ||
                String.valueOf(textInputEditTextRusEt.getText()).equals("")) {
            if (String.valueOf(textInputEditTextEngEt.getText()).equals("")) {
                textInputEngLayout.setErrorEnabled(true);
                textInputEngLayout.setError("Обязательное поле!");
            }
            if (String.valueOf(textInputEditTextRusEt.getText()).equals("")) {
                textInputRusLayout.setErrorEnabled(true);
                textInputRusLayout.setError("Обязательное поле!");
            }
        } else {
            AppDatabase db = App.getInstance().getDatabase();
            WordDao wordDao = db.wordDao();
            Word word = new Word(themeId, String.valueOf(textInputEditTextEngEt.getText()).toLowerCase(), String.valueOf(textInputEditTextRusEt.getText()).toLowerCase());
            wordDao.insert(word);
            Activity activity = getActivity();
            if (activity instanceof DictWordActivity) {
                ((DictWordActivity)activity).onBack(this.themeId);
            }
        }
    }

}
