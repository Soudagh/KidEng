package com.example.kideng.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kideng.R;
import com.example.kideng.db.entities.Word;

import java.util.List;

public class SkippedAdapter extends RecyclerView.Adapter<SkippedAdapter.ViewHolder> {

    private final List<Word> wordList;

    public SkippedAdapter(List<Word> wordList) {
        this.wordList = wordList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkippedAdapter.ViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView wordEng, wordRus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wordEng = itemView.findViewById(R.id.wordEng_tv);
            wordRus = itemView.findViewById(R.id.wordRus_tv);
        }

        public void bindView(int position) {
            wordEng.setText(wordList.get(position).getWordRus());
            wordRus.setText(wordList.get(position).getWordEng());
        }
    }
}
