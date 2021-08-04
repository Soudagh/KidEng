package com.example.kideng.db.entities;

import java.io.Serializable;

public class Word implements Serializable {

    private int id;
    private int idTheme;
    private String wordRus;
    private String wordEng;

    public Word(int id, int idTheme, String wordRus, String wordEng) {
        this.id = id;
        this.idTheme = idTheme;
        this.wordRus = wordRus;
        this.wordEng = wordEng;
    }

    public int getId() {return id;}

    public int getIdTheme() {return idTheme;}

    public String getWordRus() {
        return wordRus;
    }

    public String getWordEng() {
        return wordEng;
    }

}
