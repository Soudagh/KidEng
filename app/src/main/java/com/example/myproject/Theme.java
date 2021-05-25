package com.example.myproject;

import java.io.Serializable;
import java.util.Collection;

public class Theme implements Serializable {
    private String theme;
    private String descriptionTheme;

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





}
