package com.example.kideng.ui.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kideng.R;

// TODO: что это? :)
public class FirstEntranceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.firstentrance_activity);
        super.onCreate(savedInstanceState);
    }

    public void confirmClick(View view) {
        finish();
    }
}
