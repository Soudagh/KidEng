package com.example.myproject;

import java.io.Serializable;
import java.util.Collection;

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
