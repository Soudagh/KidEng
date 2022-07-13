package com.example.kideng.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kideng.R;
import com.example.kideng.ui.fragments.dict.AddThemeFragment;

public class DictThemeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_fragment);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_start, AddThemeFragment.newInstance())
                    .commit();
        }
    }

    public void onBack() {
        Intent intent = new Intent(this, ChooseThemeActivity.class);
        startActivity(intent);
        finish();
    }
}
