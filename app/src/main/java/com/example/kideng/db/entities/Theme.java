package com.example.kideng.db.entities;

import java.io.Serializable;

public class Theme implements Serializable {
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
