
package com.example.kideng.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kideng.App;
import com.example.kideng.R;
import com.example.kideng.databinding.ActivityMainBinding;
import com.example.kideng.databinding.ThemeActivityBinding;
import com.example.kideng.db.AppDatabase;
import com.example.kideng.db.dao.ThemeDao;
import com.example.kideng.db.entities.Theme;
import com.example.kideng.ui.adapters.ThemeAdapter;

import java.util.List;

public class ChooseThemeActivity extends AppCompatActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        AppDatabase db = App.getInstance().getDatabase();
        ThemeDao themeDao = db.themeDao();
        List<Theme> themeList = themeDao.getAll();
        RecyclerView mRecycler = findViewById(R.id.recycler_themes);
        ThemeAdapter mThemeAdapter = new ThemeAdapter(themeList);
        mRecycler.setAdapter(mThemeAdapter);

        TextView mEmptyTv = findViewById(R.id.empty_tv);
        if (themeList.isEmpty()) {
            mEmptyTv.setVisibility(View.VISIBLE);
        } else {
            mEmptyTv.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void addTheme(View view) {
        Intent intent = new Intent(this, DictThemeActivity.class);
        startActivity(intent);
    }
}