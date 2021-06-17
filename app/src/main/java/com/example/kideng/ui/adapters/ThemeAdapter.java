package com.example.kideng.ui.adapters;

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
import com.example.kideng.db.entities.Theme;
import com.example.kideng.ui.activities.WordActivity;

import java.util.List;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {
    //TODO: может быть ограничить область видимости?
    List<Theme> themes;

    //TODO: зачем они?!
    Context mContext;
    private final LayoutInflater inflater;

    public ThemeAdapter(Context context, List<Theme> themes) {
        this.themes = themes;
        this.inflater = LayoutInflater.from(context); //TODO: утчека памяти
        mContext = context; //TODO: утчека памяти
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //TODO: Попробуй использовать здесь LayoutInflater.from(parent.getContext())
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
            linearLayout.setOnClickListener(v -> {
                //TODO: у нас контексты есть во всех VIEW элементах,
                // т.к. они нужны для того, чтобы понимать когда они нам не нужны
                // попробуй itemView.getContext()
                // Вообще логикой клика лучше, чтобы управлял не адаптер, а тот кто его создаёт
                // чтобы если тебе надо было перейти на другую активность не надо было копипастить его
                Intent intent = new Intent(mContext, WordActivity.class);
                intent.putExtra("id", themes.get(position).getId());
                mContext.startActivity(intent);
            });
        }


    }


}