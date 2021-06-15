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
    final String welcomeScreenShownPrefs = "welcomeScreenShown";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean welcomeScreenShown = mPrefs.getBoolean(welcomeScreenShownPrefs, false);

//        if (!welcomeScreenShown) {
//            startActivity(new Intent(this, FirstEntranceActivity.class));
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