package com.example.myproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChooseThemeActivity extends AppCompatActivity {

    ArrayList<Theme> themes = new ArrayList<>();
    RecyclerView mRecycler;
    ThemeAdapter mThemeAdapter;

    ThemeDBHelper databaseHelper;
    ArrayList<String> theme_title, theme_description;

//    SQLiteDatabase db;
//    Cursor themeCursor;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_theme);

        mRecycler = findViewById(R.id.recycler);

        databaseHelper = new ThemeDBHelper(this);
        List<Theme> themeList = databaseHelper.getTheme();

        ThemeAdapter.ThemeChoiceListener themeChoiceListener = (theme, position) ->
                Toast.makeText(getApplicationContext(), "Был выбран пункт " + theme_title.get(position),
                Toast.LENGTH_SHORT).show();


        mThemeAdapter = new ThemeAdapter(this, themeChoiceListener, themeList);

        mRecycler.setAdapter(mThemeAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();


    }

    public void onThemeChoiceClick(View view) {
        Intent intent = new Intent(this, ThemeActivity.class);
        startActivityForResult(intent, 0);
    }

//    private void setInitialData() {
//        themes.add(new Theme("Буквы", "Изучение алфавита 📕"));
//        themes.add(new Theme("Цифры", "Учим цифры от 1 до 10 💯"));
//        themes.add(new Theme("Приветствия и фразы", "Скажем Hello! 👋"));
//        themes.add(new Theme("Семья", "Обратимся на английском к родителям? 👨‍👩‍👧‍👦"));
//        themes.add(new Theme("Цвета", "Сможем назвать цвета радуги! 🌈"));
//        themes.add(new Theme("Еда", "Назовём своё любимое блюдо 😋"));
//        themes.add(new Theme("Животные", "Скажем котику, какой он милый 🐱"));
//        themes.add(new Theme("Природа и город", "На прогулке с родителями покажем им новые умения 😎"));
//    }
}
