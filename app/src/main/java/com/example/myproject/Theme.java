package com.example.myproject;

import java.io.Serializable;
import java.util.Collection;

public class Theme implements Serializable {
    private String id;
    private String theme;
    private String descriptionTheme;

    public Theme(String id, String theme, String descriptionTheme) {
        this.id = id;
        this.theme = theme;
        this.descriptionTheme = descriptionTheme;

    }
    public String getId() {return theme;}

    public String getTheme() {
        return theme;
    }

    public String getDescriptionTheme() {
        return descriptionTheme;
    }





}
