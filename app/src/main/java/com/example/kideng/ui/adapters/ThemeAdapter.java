package com.example.kideng.ui.adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kideng.R;
import com.example.kideng.db.entities.Theme;
import com.example.kideng.ui.activities.DictThemeActivity;
import com.example.kideng.ui.activities.WordActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> implements Filterable {
    private final List<Theme> themeList;
    private List<Theme> themeListFull;

    public ThemeAdapter(List<Theme> themeList) {
        this.themeList = themeList;
        themeListFull = new ArrayList<>(themeList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.theme_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return themeList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

   Filter filter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Theme> filteredList = new ArrayList<>();

            if (charSequence.toString().isEmpty()) {
                filteredList.addAll(themeListFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (Theme theme : themeListFull) {
                    if (theme.getTheme().toLowerCase().contains(filterPattern)) {
                        filteredList.add(theme);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;

        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            themeList.clear();
            themeList.addAll((Collection<? extends Theme>) filterResults.values);
            notifyDataSetChanged();
        }
    };


    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView themeName, themeDescription;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            themeName = itemView.findViewById(R.id.themeName_tv);
            themeDescription = itemView.findViewById(R.id.description_tv);
            linearLayout = itemView.findViewById(R.id.linear);
        }

        public void bindView(int position) {
            themeName.setText(themeList.get(position).getTheme());
            themeDescription.setText(themeList.get(position).getDescriptionTheme());
            Theme theme = themeList.get(position);
            linearLayout.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), WordActivity.class);
                intent.putExtra("id", theme.getId());
                itemView.getContext().startActivity(intent);
            });

            linearLayout.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), DictThemeActivity.class);
                    intent.putExtra("status", "change");
                    intent.putExtra("themeName", theme.getTheme());
                    intent.putExtra("themeDesc", theme.getDescriptionTheme());
                    itemView.getContext().startActivity(intent);
                    return false;
                }

            });
        }
    }

}