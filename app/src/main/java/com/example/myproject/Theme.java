package com.example.myproject;

import java.io.Serializable;
import java.util.Collection;

public class Theme implements Serializable {
//    private final long id;
    private int themeNumber;
    private String theme;
    private String descriptionTheme;

    public Theme(int themeNumber, String theme, String descriptionTheme) {
        this.themeNumber = themeNumber;
        this.theme = theme;
        this.descriptionTheme = descriptionTheme;
    }

    public int getThemeNumber() {
        return themeNumber;
    }

    public void setThemeNumber(int themeNumber) {
        this.themeNumber = themeNumber;
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
