package com.example.kideng.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kideng.App;
import com.example.kideng.R;
import com.example.kideng.db.AppDatabase;
import com.example.kideng.db.dao.WordDao;
import com.example.kideng.db.entities.Word;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddWordActivity extends AppCompatActivity {

    private TextInputEditText textInputEditTextEngEt;
    private TextInputEditText textInputEditTextRusEt;
    private TextInputLayout textInputEngLayout;
    private TextInputLayout textInputRusLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_word_activity);

        textInputEditTextEngEt = findViewById(R.id.textInputEngWordEt);
        textInputEditTextRusEt = findViewById(R.id.textInputRusWordEt);
        textInputEngLayout = findViewById(R.id.textInputEngWordLayout);
        textInputRusLayout = findViewById(R.id.textInputRusWordLayout);
    }

    public void additionWord(View view) {
        Intent oldIntent = getIntent();
        int themeId = oldIntent.getIntExtra("id", 0);

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
            Intent newIntent = new Intent(this, WordActivity.class);
            newIntent.putExtra("id", themeId);
            startActivity(newIntent);
        }
    }
}
