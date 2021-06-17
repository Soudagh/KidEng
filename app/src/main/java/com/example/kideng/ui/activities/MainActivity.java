package com.example.kideng.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.example.kideng.R;

public class MainActivity extends AppCompatActivity {

    //TODO: где то мы соблюдаем нейминг, а где то нет.
    // Пиши в одном стиле и не забывай про модификаторы доступа
    SharedPreferences mPrefs;
    boolean welcomeScreenShown;
    //TODO: можно сделать статичной.
    // Вообще подобные константы обычно выносят в отдельный дополнительный класс
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
        //TODO: устаревший метод
        startActivityForResult(intent, 0);
    }
//TODO: мне кажется неиспользуемый и закомментированный код нужно удалить

//    public void restartClick(View view) {
//        MainDialogFragment dialog = new MainDialogFragment();
//        dialog.show(getFragmentManager(), "custom");
//    }

    public void profileClick(View view) {
    }


}