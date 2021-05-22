package com.example.myproject;

import java.io.Serializable;

public class Word implements Serializable {
    //private String category;
    private String wordRus;
    private String wordEng;

    public Word(/*String category,*/ String wordRus, String wordEng) {
       // this.category = category;
        this.wordRus = wordRus;
        this.wordEng = wordEng;
    }


    public String getWordRus() {
        return wordRus;
    }

    public void setWordRus(String wordRus) {
        this.wordRus = wordRus;
    }

//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }

    public String getWordEng() {
        return wordEng;
    }

    public void setWordEng(String wordEng) {
        this.wordEng = wordEng;
    }
}
