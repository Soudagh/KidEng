
package com.example.kideng.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import com.example.kideng.App;
import com.example.kideng.R;
import com.example.kideng.db.AppDatabase;
import com.example.kideng.db.dao.UserDao;


public class MainActivity extends AppCompatActivity {

    final static String showWelcomeScreenString = "showWelcome";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        boolean mWelcomeScreenShown = mPrefs.getBoolean(showWelcomeScreenString, true);

        if (mWelcomeScreenShown) {
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
        AppDatabase db = App.getInstance().getDatabase();
        UserDao userDao = db.userDao();

        TextView mNickname_tv = findViewById(R.id.user_tv);
        //mNickname_tv.setText(userDao.get());
    }

}