package com.example.myproject;

import java.io.Serializable;
import java.util.Collection;

public class Theme implements Serializable {
//    private final long id;
    private String theme;
    private String descriptionTheme;

    public Theme(String theme, String descriptionTheme) {

        this.theme = theme;
        this.descriptionTheme = descriptionTheme;
    }




    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDescriptionTheme() {
        return descriptionTheme;
    }

    public void setDescriptionTheme(String descriptionTheme) {
        this.descriptionTheme = descriptionTheme;
    }

//    public long getId() {
//        return id;
//    }
}
