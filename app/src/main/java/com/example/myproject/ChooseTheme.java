package com.example.myproject;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChooseTheme extends AppCompatActivity {

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

    private void setInitialData() {
        themes.add(new Theme(1, "Буквы", "Изучение алфавита 📕"));
        themes.add(new Theme(2, "Цифры", "Учим цифры от 1 до 10 💯"));
        themes.add(new Theme(4, "Приветствия и фразы", "Скажем Hello! 👋"));
        themes.add(new Theme(3, "Семья", "Обратимся на английском к родителям? 👨‍👩‍👧‍👦"));
        themes.add(new Theme(5, "Цвета", "Сможем назвать цвета радуги! 🌈"));
        themes.add(new Theme(6, "Еда", "Назовём своё любимое блюдо 😋"));
        themes.add(new Theme(7, "Животные", "Скажем котику, какой он милый 🐱"));
        themes.add(new Theme(8, "Природа и город", "На прогулке с родителями покажем им новые умения 😎"));
    }
}
