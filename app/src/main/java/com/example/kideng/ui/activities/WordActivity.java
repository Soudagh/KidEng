package com.example.kideng.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kideng.App;
import com.example.kideng.R;
import com.example.kideng.db.AppDatabase;
import com.example.kideng.db.dao.ThemeDao;
import com.example.kideng.db.dao.WordDao;
import com.example.kideng.db.entities.Word;

import com.example.kideng.ui.adapters.WordAdapter;


import java.util.List;

public class WordActivity extends AppCompatActivity {

    private WordAdapter mWordAdapter;
    RecyclerView mRecycler;
    public List<Word> wordList;
    AppDatabase db = App.getInstance().getDatabase();
    WordDao wordDao = db.wordDao();
    ThemeDao themeDao = db.themeDao();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_activity);

        int id = getIntentId();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setTitle(themeDao.getThemeById(id).getTheme());
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        mRecycler = findViewById(R.id.recycler_words);

        ItemTouchHelper.SimpleCallback callback = new SwipeItem(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        new ItemTouchHelper(callback).attachToRecyclerView(mRecycler);

        wordList = wordDao.getByThemeId(id);
        mWordAdapter = new WordAdapter(wordList);
        mRecycler.setAdapter(mWordAdapter);

        TextView mEmptyTv = findViewById(R.id.empty_tv);
        if (wordList.isEmpty()) {
            mEmptyTv.setVisibility(View.VISIBLE);
        } else {
            mEmptyTv.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.theme_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mWordAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ChooseThemeActivity.class);
        startActivity(intent);
    }

    public void addWord(View view) {
        Intent intent = new Intent(this, DictWordActivity.class);
        intent.putExtra("themeId", getIntentId());
        intent.putExtra("status", "add");
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
                mWordAdapter.removeWord(viewHolder.getAdapterPosition());
            }
        }
    }

    public int getIntentId() {
        Intent intent = getIntent();
        return intent.getIntExtra("id", 0);
    }


}