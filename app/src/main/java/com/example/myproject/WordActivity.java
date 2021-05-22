package com.example.myproject;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordActivity extends AppCompatActivity {

    private  RecyclerView mRecycler;
    private WordAdapter mWordAdapter;

    private ThemeDBHelper databaseHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_fragment);

        mRecycler = findViewById(R.id.recycler_words);

        databaseHelper = new ThemeDBHelper(this);
        List<Word> wordList = databaseHelper.getWord();

        mWordAdapter = new WordAdapter(this, wordList);

        mRecycler.setAdapter(mWordAdapter);
    }




}
