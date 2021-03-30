package com.example.myproject;

import java.io.Serializable;
import java.util.Collection;

public class Theme implements Serializable {
    private final long id;
    short themeNumber = 0;
    String theme = "";
    String descriptionTheme = "";

    public Theme(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
