package com.example.kideng.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kideng.R;
import com.example.kideng.entities.Word;
import com.example.kideng.supporting.App;
//import com.example.kideng.supporting.ThemeDBHelper;
import com.example.kideng.supporting.WordAdapter;
import com.example.kideng.supporting.WordDao;

import java.util.List;

public class WordActivity extends AppCompatActivity {

    private  RecyclerView mRecycler;
    private WordAdapter mWordAdapter;

    private WordDao wordDao;
    //private ThemeDBHelper databaseHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_activity);

        mRecycler = findViewById(R.id.recycler_words);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        wordDao = App.getInstance().getDatabase().wordDao();
        //databaseHelper = new ThemeDBHelper(this);

       // List<Word> wordList = databaseHelper.getWord(id);

        mWordAdapter = new WordAdapter(this, wordDao.getWords(id));

        mRecycler.setAdapter(mWordAdapter);
    }




}