package com.example.kideng.db.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Theme implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private final String theme;
    private final String descriptionTheme;

    public Theme(String theme, String descriptionTheme) {
        this.theme = theme;
        this.descriptionTheme = descriptionTheme;
    }

    public String getTheme() {
        return theme;
    }

    public String getDescriptionTheme() {
        return descriptionTheme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
