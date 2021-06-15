package com.example.kideng.supporting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kideng.R;
import com.example.kideng.entities.Word;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {

    List<Word> words;

    private final LayoutInflater inflater;

    public WordAdapter(Context context, List<Word> words) {
        this.words = words;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.word_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.wordEng.setText(words.get(position).getWordEng());
        holder.wordRu.setText(words.get(position).getWordRus());

    }


    @Override
    public int getItemCount() {return words.size();}


    static class ViewHolder  extends RecyclerView.ViewHolder {
        final TextView wordEng, wordRu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            wordEng = itemView.findViewById(R.id.wordEng_tv);
            wordRu = itemView.findViewById(R.id.wordRus_tv);
        }
    }
}