package com.example.kideng.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "THEMES_LIST")
public class Theme{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    public int themeId;
    @ColumnInfo(name = "name")
    public @NonNull
    String theme;
    @ColumnInfo(name = "description")
    public @NonNull
    String descriptionTheme;

    public Theme(@NonNull int themeId, @NonNull String theme, @NonNull String descriptionTheme) {
        this.themeId = themeId;
        this.theme = theme;
        this.descriptionTheme = descriptionTheme;

    }

//    public Theme() {
//    }

//    public void setThemeId(int themeID) {}
//
//    public void setTheme(String theme) {}
//
//    public void setDescriptionTheme(String descriptionTheme) {}



}
