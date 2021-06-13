
package com.example.kideng.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kideng.R;
import com.example.kideng.entities.Theme;
import com.example.kideng.supporting.App;
import com.example.kideng.supporting.ThemeAdapter;
//import com.example.kideng.supporting.ThemeDBHelper;
import com.example.kideng.supporting.ThemeDao;

import java.util.List;

public class ChooseThemeActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private ThemeAdapter mThemeAdapter;

    private ThemeDao themeDao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_theme);

        mRecycler = findViewById(R.id.recycler_themes);

        //databaseHelper = new ThemeDBHelper(this);
        themeDao = App.getInstance().getDatabase().themeDao();

        //List<Theme> themeList = databaseHelper.getTheme();

        mThemeAdapter = new ThemeAdapter(this, themeDao.getAll());

        mRecycler.setAdapter(mThemeAdapter);
    }

}