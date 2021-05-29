package com.example.myproject.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.R;
import com.example.myproject.entities.Word;
import com.example.myproject.supporting.ThemeDBHelper;
import com.example.myproject.supporting.WordAdapter;

import java.util.List;

public class WordActivity extends AppCompatActivity {

    private  RecyclerView mRecycler;
    private WordAdapter mWordAdapter;

    private ThemeDBHelper databaseHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_activity);

        mRecycler = findViewById(R.id.recycler_words);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        databaseHelper = new ThemeDBHelper(this);

        List<Word> wordList = databaseHelper.getWord(id);

        mWordAdapter = new WordAdapter(this, wordList);

        mRecycler.setAdapter(mWordAdapter);
    }




}