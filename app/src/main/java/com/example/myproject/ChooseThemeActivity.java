package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChooseThemeActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private ThemeAdapter mThemeAdapter;

    ArrayList<Theme> themes = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_theme);

        setInitialData();
        mRecycler = findViewById(R.id.recycler);
        ThemeAdapter.ThemeChoiceListener themeChoiceListener = (theme, position) -> Toast.makeText(getApplicationContext(), "Был выбран пункт " + theme.getTheme(),
                Toast.LENGTH_SHORT).show();
        mThemeAdapter = new ThemeAdapter(this, themes, themeChoiceListener);
        mRecycler.setAdapter(mThemeAdapter);

    }

    public void onThemeChoiceClick(View view) {
        Intent intent = new Intent(this, ThemeActivity.class);
        startActivityForResult(intent, 0);
    }

    private void setInitialData() {
        themes.add(new Theme("Буквы", "Изучение алфавита 📕"));
        themes.add(new Theme("Цифры", "Учим цифры от 1 до 10 💯"));
        themes.add(new Theme("Приветствия и фразы", "Скажем Hello! 👋"));
        themes.add(new Theme("Семья", "Обратимся на английском к родителям? 👨‍👩‍👧‍👦"));
        themes.add(new Theme("Цвета", "Сможем назвать цвета радуги! 🌈"));
        themes.add(new Theme("Еда", "Назовём своё любимое блюдо 😋"));
        themes.add(new Theme("Животные", "Скажем котику, какой он милый 🐱"));
        themes.add(new Theme("Природа и город", "На прогулке с родителями покажем им новые умения 😎"))
        ;
    }
}