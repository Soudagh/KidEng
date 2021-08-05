package com.example.kideng.ui.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kideng.R;
import com.example.kideng.db.legacy.ThemeDBHelper;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddActivity extends AppCompatActivity {

    private TextInputEditText textInputEditTextEngEt;
    private TextInputEditText textInputEditTextRusEt;
    private TextInputLayout textInputEngLayout;
    private TextInputLayout textInputRusLayout;
    private AutoCompleteTextView dropDownTv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        textInputEditTextEngEt = findViewById(R.id.textInputEngWordEt);
        textInputEditTextRusEt = findViewById(R.id.textInputRusWordEt);
        textInputEngLayout = findViewById(R.id.textInputEngWordLayout);
        textInputRusLayout = findViewById(R.id.textInputRusWordLayout);

        dropDownTv = findViewById(R.id.autoCompleteTextView);

        dropDownTv.setDropDownBackgroundResource(R.color.primary);

        String[] themes = getResources().getStringArray(R.array.themes);
        ArrayAdapter<String> themeAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item, themes);
        dropDownTv.setAdapter(themeAdapter);
    }

    public void additionWord(View view) {
        int themeId = 9;
        String themeName = String.valueOf(dropDownTv.getText());

        if (String.valueOf(textInputEditTextEngEt.getText()).equals("") || String.valueOf(textInputEditTextRusEt.getText()).equals("")) {
            if (String.valueOf(textInputEditTextEngEt.getText()).equals("")) {
                textInputEngLayout.setErrorEnabled(true);
                textInputEngLayout.setError("Обязательное поле!");
            }
            if (String.valueOf(textInputEditTextRusEt.getText()).equals("")) {
                textInputRusLayout.setErrorEnabled(true);
                textInputRusLayout.setError("Обязательное поле!");
            }
        } else {
            if (themeName.equals("Цифры")) themeId = 2;
            if (themeName.equals("Приветствия и фразы")) themeId = 3;
            if (themeName.equals("Семья")) themeId = 4;
            if (themeName.equals("Цвета")) themeId = 5;
            if (themeName.equals("Еда")) themeId = 6;
            if (themeName.equals("Животные")) themeId = 7;
            if (themeName.equals("Природа и город")) themeId = 8;
            ThemeDBHelper dbHelper = new ThemeDBHelper(getApplicationContext());
            dbHelper.insertWord(themeId, String.valueOf(textInputEditTextEngEt.getText()).toLowerCase(), String.valueOf(textInputEditTextRusEt.getText()).toLowerCase());
            Intent intent = new Intent(this, ChooseThemeActivity.class);
            startActivity(intent);
        }
    }
}
