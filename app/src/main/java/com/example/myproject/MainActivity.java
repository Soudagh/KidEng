package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void gameClick(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void themeClick(View view) {
        Intent intent = new Intent(this, ChooseThemeActivity.class);
        startActivityForResult(intent, 0);
    }

    public void restartClick(View view) {
        MainDialogFragment dialog = new MainDialogFragment();
        dialog.show(getFragmentManager(), "custom");
    }

    public void profileClick(View view) {
    }


}