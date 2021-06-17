package com.example.kideng.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.example.kideng.R;

public class MainActivity extends AppCompatActivity {

    SharedPreferences mPrefs;
    boolean welcomeScreenShown ;
    final String showWelcomeScreenString = "showWelcome";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        welcomeScreenShown = mPrefs.getBoolean(showWelcomeScreenString, true);

//        if (welcomeScreenShown) {
//            welcomeScreenShown = false;
//
//            SharedPreferences.Editor editor = mPrefs.edit();
//            editor.putBoolean(showWelcomeScreenString, false);
//            editor.apply();
//            startActivity(new Intent(this, FirstEntranceActivity.class));
//
//        }
    }

    public void gameClick(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void themeClick(View view) {
        Intent intent = new Intent(this, ChooseThemeActivity.class);
        startActivityForResult(intent, 0);
    }

//    public void restartClick(View view) {
//        MainDialogFragment dialog = new MainDialogFragment();
//        dialog.show(getFragmentManager(), "custom");
//    }

    public void profileClick(View view) {
    }


}