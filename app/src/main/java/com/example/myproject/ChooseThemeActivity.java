
package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChooseThemeActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private ThemeAdapter mThemeAdapter;

    private ThemeDBHelper databaseHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_theme);

        mRecycler = findViewById(R.id.recycler_themes);

        databaseHelper = new ThemeDBHelper(this);
        List<Theme> themeList = databaseHelper.getTheme();


//        ThemeAdapter.ThemeChoiceListener themeChoiceListener = (theme, position) ->
//                Toast.makeText(getApplicationContext(), "Был выбран пункт " + theme.getTheme(),
//                        Toast.LENGTH_SHORT).show();


        mThemeAdapter = new ThemeAdapter(this, themeList);

        mRecycler.setAdapter(mThemeAdapter);
    }

}