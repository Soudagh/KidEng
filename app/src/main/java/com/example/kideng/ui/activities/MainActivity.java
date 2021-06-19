package com.example.kideng.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import com.example.kideng.R;
import com.example.kideng.db.legacy.ThemeDBHelper;


public class MainActivity extends AppCompatActivity {

    private SharedPreferences mPrefs;
    private boolean mWelcomeScreenShown;
    final static String showWelcomeScreenString = "showWelcome";

    private TextView mNickname_tv;
    private ThemeDBHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        mWelcomeScreenShown = mPrefs.getBoolean(showWelcomeScreenString, true);

        if (mWelcomeScreenShown) {
            mWelcomeScreenShown = false;
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putBoolean(showWelcomeScreenString, false);
            editor.apply();
            startActivity(new Intent(this, FirstEntranceActivity.class));
        }
        setUser();
    }

    public void gameClick (View view){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void themeClick (View view){
        Intent intent = new Intent(this, ChooseThemeActivity.class);
        startActivity(intent);
    }

    public void setUser() {
        mDatabaseHelper = new ThemeDBHelper(this);
        mNickname_tv = findViewById(R.id.user_tv);
        mNickname_tv.setText(mDatabaseHelper.getUser());
    }

    public void profileClick (View view) {
    }


}