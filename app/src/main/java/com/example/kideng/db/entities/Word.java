package com.example.kideng.db.entities;

import java.io.Serializable;

public class Word implements Serializable {

    private int id;
    private String wordRus;
    private String wordEng;

    public Word(int id, String wordRus, String wordEng) {
        this.id = id;
        this.wordRus = wordRus;
        this.wordEng = wordEng;
    }


    public int getId() {return id;}

    public String getWordRus() {
        return wordRus;
    }

    public String getWordEng() {
        return wordEng;
    }

}
