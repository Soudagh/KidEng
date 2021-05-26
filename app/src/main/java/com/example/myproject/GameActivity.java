package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

        Fragment f1 = new GameTextFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_start, f1)
                .commit();
    }

    public void onGameStart() {
        Fragment f1 = new GameFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_start, f1)
                .commit();
    }

    public void onGameStop(int rCounter, int wCounter, int tCounter) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_start, GameResultFragment.newInstance(rCounter, wCounter, tCounter))
                .commit();
    }

    public void onHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
