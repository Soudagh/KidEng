package com.example.kideng.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kideng.R;
import com.example.kideng.ui.fragments.dict.AddWordFragment;

public class DictWordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int themeId = intent.getIntExtra("themeId", 0);
        int wordId = intent.getIntExtra("wordId", 0);
        String status = intent.getStringExtra("status");
        String wordEng = intent.getStringExtra("wordEng");
        String wordRus = intent.getStringExtra("wordRus");
        setContentView(R.layout.blank_fragment);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_start, AddWordFragment.newInstance(wordId, themeId, status, wordEng, wordRus))
                    .commit();
        }
    }

    public void onBack(int id) {
        Intent intent = new Intent(this, WordActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
        finish();
    }
}
