
package com.example.kideng.ui.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kideng.R;
import com.example.kideng.db.entities.Theme;
import com.example.kideng.ui.adapters.ThemeAdapter;
//import com.example.kideng.db.legacy.ThemeDBHelper;
import com.example.kideng.db.legacy.ThemeDBHelper;

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

        mThemeAdapter = new ThemeAdapter(this, themeList);

        mRecycler.setAdapter(mThemeAdapter);
    }

}