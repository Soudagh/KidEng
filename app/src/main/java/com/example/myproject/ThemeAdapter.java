package com.example.myproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {

    List<Theme> themes;
    Context mContext;

    interface ThemeChoiceListener {
        void onThemeChoiceClick(View view, int position);
    }

//    private final ThemeChoiceListener onClickListener;

    private final LayoutInflater inflater;

    public ThemeAdapter(Context context, List<Theme> themes) {
        this.themes = themes;
        this.inflater = LayoutInflater.from(context);
        mContext = context;
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
//        holder.itemView.setOnClickListener(v -> onClickListener.onThemeChoiceClick(theme, position));
    }

    @Override
    public int getItemCount() {
        return themes.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder  {
        final TextView themeName, themeDescription;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            themeName = itemView.findViewById(R.id.themeName_tv);
            themeDescription = itemView.findViewById(R.id.description_tv);
            linearLayout = itemView.findViewById(R.id.linear);
            linearLayout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, WordActivity.class);
                    intent.putExtra("id", getLayoutPosition());
                    mContext.startActivity(intent);
                }
            });
        }


    }


}