package com.example.kideng.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kideng.db.entities.Theme;
import com.example.kideng.ui.fragments.game.GameFragment;
import com.example.kideng.ui.fragments.game.GameResultFragment;
import com.example.kideng.ui.fragments.game.GameStartFragment;
import com.example.kideng.ui.fragments.game.GameTextFragment;
import com.example.kideng.R;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_fragment);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_start, GameStartFragment.newInstance())
                    .commit();
        }
    }

    public void onGameCountDown(String translate, String goal, String duration, ArrayList<Integer> themeList) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_start, GameTextFragment.newInstance(translate, goal, duration, themeList))
                .commit();
    }

    public void onGameStart(String translate, String goal, String duration, ArrayList<Integer> themeList) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_start, GameFragment.newInstance(translate, goal, duration, themeList))
                .commit();
    }

    public void onGameStop(String counter1, String counter2, String counter3, String translate, String goal, String duration, ArrayList<Integer> themeList) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_start, GameResultFragment.newInstance(counter1, counter2, counter3, translate, goal, duration, themeList))
                .commit();
    }

    public void onHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onAgain(String translate, String goal, String duration, ArrayList<Integer> themeList) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_start, GameTextFragment.newInstance(translate, goal, duration, themeList))
                .commit();
    }

    public void onChange() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_start, GameStartFragment.newInstance())
                .commit();
    }
}
