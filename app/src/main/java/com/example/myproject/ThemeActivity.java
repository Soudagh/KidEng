package com.example.myproject;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class ThemeActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_fragment);

        ViewPager2 pager = findViewById(R.id.pager);
        FragmentStateAdapter pageAdapter = new PageAdapter(this);
        pager.setAdapter(pageAdapter);
    }
}
