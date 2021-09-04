package com.example.kideng.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kideng.R;
import com.example.kideng.db.entities.Word;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {

    private final List<Word> words;

    public WordAdapter(List<Word> words) {
        this.words = words;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public void removeWord(int pos) {
        words.remove(pos);
        notifyItemRemoved(pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView wordEng, wordRu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wordEng = itemView.findViewById(R.id.wordEng_tv);
            wordRu = itemView.findViewById(R.id.wordRus_tv);
        }

        public void bindView(int position) {
            wordEng.setText(words.get(position).getWordEng());
            wordRu.setText(words.get(position).getWordRus());
        }

        public int getItemDBId(int id) {
            return words.get(id).getId();
        }

    }
}