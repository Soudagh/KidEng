package com.example.myproject;

import android.os.Bundle;
import android.os.PersistableBundle;

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

        mThemeAdapter = new ThemeAdapter(this, themes);
        mRecycler.setAdapter(mThemeAdapter);

    }

    private void setInitialData() {
        themes.add(new Theme(10, "Фрукты", "Учим фрукты"));
        themes.add(new Theme(10, "Фрукты", "Учим фрукты"));
        themes.add(new Theme(10, "Фрукты", "Учим фрукты"));
        themes.add(new Theme(10, "Фрукты", "Учим фрукты"));
    }
}
