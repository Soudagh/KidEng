package com.example.kideng.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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

        ItemTouchHelper.SimpleCallback callback = new SwipeItem(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        new ItemTouchHelper(callback).attachToRecyclerView(mRecycler);

        int id = getIntentId();
        wordList = wordDao.getByThemeId(id);
        mWordAdapter = new WordAdapter(wordList);
        mRecycler.setAdapter(mWordAdapter);
    }

    public void addWord(View view) {
        Intent intent = new Intent(this, AddWordActivity.class);
        intent.putExtra("id", getIntentId());
        startActivity(intent);
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
                Log.d("getAdapterPosition", String.valueOf(viewHolder.getAdapterPosition()));
                Log.d("getItemId", String.valueOf(viewHolder.getItemId()));
                Log.d("toString", viewHolder.toString());
                //int id = viewHolder.getAdapterPosition();
                //Word word = new Word();
               //wordDao.delete(((WordAdapter.ViewHolder) viewHolder).getItemById(viewHolder.getAdapterPosition()));
                mWordAdapter.removeWord(viewHolder.getAdapterPosition());
            }
        }
    }

    public int getIntentId() {
        Intent intent = getIntent();
        return intent.getIntExtra("id", 0);
    }


}