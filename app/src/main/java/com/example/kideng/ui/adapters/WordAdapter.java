package com.example.kideng.ui.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> implements Filterable {

    private final List<Word> wordList;
    private final List<Word> wordListFull;

    public WordAdapter(List<Word> words) {
        this.wordList = words;
        wordListFull = new ArrayList<>(wordList);
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
        return wordList.size();
    }

    public void removeWord(int pos) {
        AppDatabase db = App.getInstance().getDatabase();
        WordDao wordDao = db.wordDao();
        wordDao.delete(wordList.get(pos));
        wordList.remove(pos);
        notifyItemRemoved(pos);
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
           List<Word> filteredList = new ArrayList<>();

           if (charSequence.toString().isEmpty()) {
               filteredList.addAll(wordListFull);
           } else {
               String filterPattern = charSequence.toString().toLowerCase().trim();

               for (Word word : wordListFull) {
                   if (word.getWordEng().toLowerCase().contains(filterPattern) ||
                   word.getWordRus().toLowerCase().contains(filterPattern)) {
                       filteredList.add(word);
                   }
               }
           }

           FilterResults results = new FilterResults();
           results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            wordList.clear();
            wordList.addAll((Collection<? extends Word>) filterResults.values);
            notifyDataSetChanged();
        }
    };

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
            wordEng.setText(wordList.get(position).getWordEng());
            wordRu.setText(wordList.get(position).getWordRus());
            Word word = wordList.get(position);
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