package com.example.kideng.db.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Theme implements Serializable {
    @PrimaryKey
    private int id;
    private String theme;
    private String descriptionTheme;

    public Theme(int id, String theme, String descriptionTheme) {
        this.id = id;
        this.theme = theme;
        this.descriptionTheme = descriptionTheme;
    }

    public int getId() {return id;}

    public String getTheme() {
        return theme;
    }

    public String getDescriptionTheme() {
        return descriptionTheme;
    }





}
