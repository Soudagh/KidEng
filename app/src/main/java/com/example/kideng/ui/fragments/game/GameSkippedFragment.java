package com.example.kideng.ui.fragments.game;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kideng.App;
import com.example.kideng.R;
import com.example.kideng.db.AppDatabase;
import com.example.kideng.db.dao.WordDao;
import com.example.kideng.db.entities.Word;
import com.example.kideng.ui.activities.GameActivity;
import com.example.kideng.ui.adapters.SkippedAdapter;

import java.util.ArrayList;
import java.util.List;

public class GameSkippedFragment extends Fragment {

    private static final String ARG_SET = "skippedWords";

    private ArrayList<Integer> skippedWords;
    public List<Word> wordList = new ArrayList<>();
    private SkippedAdapter mSkippedAdapter;

    AppDatabase db = App.getInstance().getDatabase();
    WordDao wordDao = db.wordDao();
    RecyclerView mRecycler;

    public static GameSkippedFragment newInstance(ArrayList<Integer> skippedWords) {
        GameSkippedFragment fragment = new GameSkippedFragment();
        Bundle args = new Bundle();
        args.putIntegerArrayList(ARG_SET, skippedWords);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            skippedWords = getArguments().getIntegerArrayList(ARG_SET);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_skipped, container, false);

        mRecycler = view.findViewById(R.id.recycler_words);

        for (int wordId : skippedWords) {
            Word word = wordDao.getWordById(wordId);
            wordList.add(word);
        }


        mSkippedAdapter = new SkippedAdapter(wordList);
        mRecycler.setAdapter(mSkippedAdapter);

        TextView mEmptyTv = view.findViewById(R.id.empty_tv);
        if (wordList.isEmpty()) {
            mEmptyTv.setVisibility(View.VISIBLE);
        } else {
            mEmptyTv.setVisibility(View.GONE);
        }

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(view1 -> {
            Activity activity = getActivity();
            if (activity instanceof GameActivity) {
                ((GameActivity)activity).onBackResults();
            }
        });

        return view;
    }

}