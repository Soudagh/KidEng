package com.example.myproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {

    List<Theme> themes;

    interface ThemeChoiceListener {
        void onThemeChoiceClick(Theme theme, int position);
    }

    private final ThemeChoiceListener onClickListener;

    private final LayoutInflater inflater;
   // private final List<Theme> themes;
    //ArrayList theme_title, theme_description;


    public ThemeAdapter(Context context, ThemeChoiceListener onClickListener, List<Theme> themes) {
        this.onClickListener = onClickListener;
        this.themes = themes;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Theme theme = themes.get(position);
        holder.themeName.setText(themes.get(position).getTheme());
        holder.themeDescription.setText(themes.get(position).getDescriptionTheme());
//          holder.bindView(theme);
//          holder.themeName.setText(theme.getTheme());
//          holder.themeDescription.setText(theme.getDescriptionTheme());
            holder.itemView.setOnClickListener(v -> onClickListener.onThemeChoiceClick(theme, position));
    }

    @Override
    public int getItemCount() {
        return themes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {


        public Theme theme;
        final TextView themeName, themeDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            themeName = itemView.findViewById(R.id.themeName_tv);
            themeDescription = itemView.findViewById(R.id.description_tv);

        }

        void bindView(Theme theme) {
            this.theme = theme;
        }


    }


}