package com.example.kideng.supporting;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kideng.R;
import com.example.kideng.activities.WordActivity;
import com.example.kideng.entities.Theme;

import java.util.List;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {

    List<Theme> themes;
    Context mContext;


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
          holder.bindView(position);
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

        }

        public void bindView(int position) {
            themeName.setText(themes.get(position).getTheme());
            themeDescription.setText(themes.get(position).getDescriptionTheme());
            linearLayout.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, WordActivity.class);
                intent.putExtra("id", themes.get(position).getId());
                mContext.startActivity(intent);
            });
        }


    }


}