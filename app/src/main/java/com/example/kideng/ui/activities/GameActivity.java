package com.example.kideng.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kideng.ui.fragments.GameFragment;
import com.example.kideng.ui.fragments.GameResultFragment;
import com.example.kideng.ui.fragments.GameStartFragment;
import com.example.kideng.ui.fragments.GameTextFragment;
import com.example.kideng.R;

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

    public void onGameStop(String counter1, String counter2, String counter3, String language) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_start, GameResultFragment.newInstance(counter1, counter2, counter3, language))
                .commit();
    }

    public void onHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onAgain(String language) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_start, GameTextFragment.newInstance(language))
                .commit();
    }

    public void onChange() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_start, GameStartFragment.newInstance())
                .commit();
    }
}
