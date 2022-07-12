package com.example.kideng.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kideng.App;
import com.example.kideng.R;
import com.example.kideng.db.AppDatabase;
import com.example.kideng.db.dao.ThemeDao;
import com.example.kideng.db.entities.Theme;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddThemeActivity extends AppCompatActivity {

    private TextInputEditText textInputEditTextThemeNameEt;
    private TextInputEditText textInputEditTextThemeDescEt;
    private TextInputLayout textInputThemeNameLayout;
    private TextInputLayout textInputThemeDescLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_theme_activity);

        textInputEditTextThemeNameEt = findViewById(R.id.textInputThemeNamedEt);
        textInputEditTextThemeDescEt = findViewById(R.id.textInputThemeDescEt);
        textInputThemeNameLayout = findViewById(R.id.textInputThemeNameLayout);
        textInputThemeDescLayout = findViewById(R.id.textInputThemeDescLayout);

    }

    public void addTheme(View view) {
        if (String.valueOf(textInputEditTextThemeNameEt.getText()).equals("") || String.valueOf(textInputEditTextThemeDescEt.getText()).equals("")) {
            if (String.valueOf(textInputEditTextThemeDescEt.getText()).equals("")) {
                textInputThemeNameLayout.setErrorEnabled(true);
                textInputThemeNameLayout.setError(getString(R.string.requiredField));
            }
            if (String.valueOf(textInputEditTextThemeDescEt.getText()).equals("")) {
                textInputThemeDescLayout.setErrorEnabled(true);
                textInputThemeDescLayout.setError(getString(R.string.requiredField));
            }
        } else {
            AppDatabase db = App.getInstance().getDatabase();
            ThemeDao themeDao = db.themeDao();
            Theme theme = new Theme(String.valueOf(textInputEditTextThemeNameEt.getText()),
                    String.valueOf(textInputEditTextThemeDescEt.getText()));
            themeDao.insert(theme);
            Intent intent = new Intent(this, ChooseThemeActivity.class);
            startActivity(intent);
        }
    }

    public void isEmpty() {

    }

}
