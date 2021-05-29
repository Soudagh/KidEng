package com.example.myproject.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.fragments.GameFragment;
import com.example.myproject.fragments.GameResultFragment;
import com.example.myproject.fragments.GameStartFragment;
import com.example.myproject.fragments.GameTextFragment;
import com.example.myproject.R;

public class GameActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_start, GameStartFragment.newInstance())
                    .commit();
        }
    }

    public void onGameCountDown(String language) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_start, GameTextFragment.newInstance(language))
                .commit();
    }

    public void onGameStart(String language) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_start, GameFragment.newInstance(language))
                .commit();
    }

    public void onGameStop(String counter1, String counter2, String counter3) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_start, GameResultFragment.newInstance(counter1, counter2, counter3))
                .commit();
    }

    public void onHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
