package com.example.kideng.ui.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kideng.R;
import com.example.kideng.db.entities.Theme;
import com.example.kideng.ui.activities.DictThemeActivity;
import com.example.kideng.ui.activities.WordActivity;

import java.util.List;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {
    private final List<Theme> themes;

    public ThemeAdapter(List<Theme> themes) {
        this.themes = themes;
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
        return themes.size();
    }

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
            themeName.setText(themes.get(position).getTheme());
            themeDescription.setText(themes.get(position).getDescriptionTheme());
            Theme theme = themes.get(position);
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