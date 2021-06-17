package com.example.kideng.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kideng.R;
import com.example.kideng.db.entities.Word;

import com.example.kideng.db.legacy.ThemeDBHelper;
import com.example.kideng.ui.adapters.WordAdapter;


import java.util.List;

public class WordActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private WordAdapter mWordAdapter;

    private ThemeDBHelper mDatabaseHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_activity);

        mRecycler = findViewById(R.id.recycler_words);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        mDatabaseHelper = new ThemeDBHelper(this);

        List<Word> wordList = mDatabaseHelper.getWord(id);

        mWordAdapter = new WordAdapter(wordList);

        mRecycler.setAdapter(mWordAdapter);
    }




}