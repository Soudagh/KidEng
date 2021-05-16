package com.example.myproject;

import java.io.Serializable;
import java.util.Collection;

public class Theme implements Serializable {
//    private final long id;
    private String theme;
    private String descriptionTheme;
    private int pages;

    public Theme(String theme, String descriptionTheme, int pages) {

        this.theme = theme;
        this.descriptionTheme = descriptionTheme;
        this.pages = pages;
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

    public int getPages() { return pages;}

    public void setPages(int pages) {this.pages = pages;}

//    public long getId() {
//        return id;
//    }
}
