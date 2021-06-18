package com.example.kideng.ui.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kideng.R;
import com.example.kideng.db.legacy.ThemeDBHelper;

public class FirstEntranceActivity extends AppCompatActivity {
    private EditText mName;
    private EditText mNickname_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.firstentrance_activity);
        super.onCreate(savedInstanceState);

        mName = findViewById(R.id.userName_et);
        mNickname_et = findViewById(R.id.userNickName_et);
    }

    public void confirmClick(View view) {
        ThemeDBHelper dbHelper = new ThemeDBHelper(getApplicationContext());
        dbHelper.insertUser(String.valueOf(mNickname_et.getText()),String.valueOf(mName.getText()));
        startActivity(new Intent(this, MainActivity.class));
    }
}
