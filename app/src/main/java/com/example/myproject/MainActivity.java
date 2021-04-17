package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void continueClick(View view) {
    }

    public void themeClick(View view) {
        Intent intent = new Intent(this, ChooseTheme.class);
        startActivityForResult(intent, 0);
    }

    public void restartClick(View view) {
    }

    public void settingsClick(View view) {
    }


}