package com.example.kideng.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kideng.App;
import com.example.kideng.R;
import com.example.kideng.db.AppDatabase;
import com.example.kideng.db.dao.UserDao;
import com.google.android.material.textfield.TextInputLayout;

public class FirstEntranceActivity extends AppCompatActivity {
    private EditText mName;
    private EditText mNickName;
    private TextInputLayout mNickLayout;
    private TextInputLayout mNameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.firstentrance_activity);
        super.onCreate(savedInstanceState);

        mName = findViewById(R.id.userName_et);
        mNickName = findViewById(R.id.userNickName_et);
        mNickLayout = findViewById(R.id.textInputNickLayout);
        mNameLayout = findViewById(R.id.textInputNameLayout);
    }

    public void confirmClick(View view) {
        if (String.valueOf(mName.getText()).equals("") || String.valueOf(mNickName.getText()).equals("")) {
            if (String.valueOf(mName.getText()).equals("")) {
                mNameLayout.setErrorEnabled(true);
                mNameLayout.setError("Обязательное поле!");
            }

            if (String.valueOf(mNickName.getText()).equals("")) {
                mNickLayout.setErrorEnabled(true);
                mNickLayout.setError("Обязательное поле!");
            }
        } else {
            AppDatabase db = App.getInstance().getDatabase();
            UserDao userDao = db.userDao();
            //userDao.insert(String.valueOf(mNickName.getText()),String.valueOf(mName.getText()));
            startActivity(new Intent(this, MainActivity.class));
        }

    }
}
