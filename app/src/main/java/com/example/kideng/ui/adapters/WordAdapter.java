package com.example.kideng.ui.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kideng.App;
import com.example.kideng.R;
import com.example.kideng.db.AppDatabase;
import com.example.kideng.db.dao.WordDao;
import com.example.kideng.db.entities.Word;
import com.example.kideng.ui.activities.DictWordActivity;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {

    private final List<Word> words;

    public WordAdapter(List<Word> words) {
        this.words = words;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_item, parent, false);
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
        AppDatabase db = App.getInstance().getDatabase();
        WordDao wordDao = db.wordDao();
        wordDao.delete(words.get(pos));
        words.remove(pos);
        notifyItemRemoved(pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView wordEng, wordRu;
        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wordEng = itemView.findViewById(R.id.wordEng_tv);
            wordRu = itemView.findViewById(R.id.wordRus_tv);
            layout = itemView.findViewById(R.id.layout);
        }

        public void bindView(int position) {
            wordEng.setText(words.get(position).getWordEng());
            wordRu.setText(words.get(position).getWordRus());
            Word word = words.get(position);
            layout.setOnLongClickListener(view -> {
                Intent intent = new Intent(itemView.getContext(), DictWordActivity.class);
                intent.putExtra("themeId", word.getIdTheme());
                intent.putExtra("wordId", word.getId());
                intent.putExtra("status", "change");
                intent.putExtra("wordEng", word.getWordEng());
                intent.putExtra("wordRus", word.getWordRus());
                itemView.getContext().startActivity(intent);
                return false;
            });
        }

    }
}