package com.example.kideng.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kideng.App;
import com.example.kideng.R;
import com.example.kideng.db.AppDatabase;
import com.example.kideng.db.dao.WordDao;
import com.example.kideng.db.entities.Word;

import com.example.kideng.ui.adapters.WordAdapter;


import java.util.List;

public class WordActivity extends AppCompatActivity {

    private WordAdapter mWordAdapter;
    public List<Word> wordList;
    AppDatabase db = App.getInstance().getDatabase();
    WordDao wordDao = db.wordDao();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_activity);

        RecyclerView mRecycler = findViewById(R.id.recycler_words);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        ItemTouchHelper.SimpleCallback callback = new SwipeItem(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        new ItemTouchHelper(callback).attachToRecyclerView(mRecycler);
        wordList = wordDao.getAll();
        mWordAdapter = new WordAdapter(wordList);
        mRecycler.setAdapter(mWordAdapter);
    }

    class SwipeItem extends ItemTouchHelper.SimpleCallback {

        public SwipeItem(int dragDirs, int swipeDirs) {
            super(dragDirs, swipeDirs);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            if (viewHolder instanceof WordAdapter.ViewHolder) {
               // wordDao.delete(((WordAdapter.ViewHolder) viewHolder).getItemDBId(viewHolder.getAdapterPosition()));
                mWordAdapter.removeWord(viewHolder.getAdapterPosition());
            }
        }
    }


}