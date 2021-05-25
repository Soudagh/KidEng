package com.example.myproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;


public class ThemeDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "themesList.db";
    private static final int SCHEMA = 2;
    static final String THEMES_LIST_TABLE_NAME = "THEMES_LIST";
    static final String WORDS_LIST_TABLE_NAME = "WORDS_LIST";

    public static final String COLUMN_THEME_ID = "_id";
    public static final String COLUMN_WORD_ID = "_id";
    public static final String COLUMN_WORD_THEME = "id_theme";
    public static final String COLUMN_ENG = "eng";
    public static final String COLUMN_RU = "ru";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";

    public static final String[] THEMES_COLUMNS = {COLUMN_THEME_ID, COLUMN_NAME, COLUMN_DESCRIPTION};
    final String[] WORDS_COLUMNS = {COLUMN_WORD_ID, COLUMN_WORD_THEME, COLUMN_ENG, COLUMN_RU};


    private static final String CREATE_THEMES_LIST_TABLE_NAME = "CREATE TABLE " + THEMES_LIST_TABLE_NAME + "("
            + COLUMN_THEME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_DESCRIPTION + " TEXT )";

    private static final String CREATE_WORDS_LIST_TABLE_NAME = "CREATE TABLE " + WORDS_LIST_TABLE_NAME + "("
            + COLUMN_WORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_WORD_THEME + " TEXT, "
            + COLUMN_ENG + " TEXT, "
            + COLUMN_RU + " TEXT )";


    public ThemeDBHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_THEMES_LIST_TABLE_NAME);
        db.execSQL(CREATE_WORDS_LIST_TABLE_NAME);

        db.execSQL("INSERT INTO " + THEMES_LIST_TABLE_NAME + " ("
                + COLUMN_NAME + ", "
                + COLUMN_DESCRIPTION + ") VALUES " +
                "('Буквы', 'Изучение алфавита \uD83D\uDCD5')," +
                " ('Цифры', 'Учим цифры от 1 до 10 \uD83D\uDCAF')," +
                " ('Приветствия и фразы', 'Скажем Hello! \uD83D\uDC4B\')," +
                " ('Семья', 'Обратимся на английском к родителям? \uD83D\uDC68\u200D\uD83D\uDC69\u200D\uD83D\uDC67\u200D\uD83D\uDC66')," +
                " ('Цвета', 'Сможем назвать цвета радуги! \uD83C\uDF08')," +
                " ('Еда', 'Назовём своё любимое блюдо \uD83D\uDE0B')," +
                " ('Животные', 'Скажем котику, какой он милый \uD83D\uDC31')," +
                " ('Природа и город', 'На прогулке с родителями покажем им новые умения \uD83D\uDE0E')"
        );

        db.execSQL("INSERT INTO " + WORDS_LIST_TABLE_NAME + " ("
                + COLUMN_WORD_THEME + ", "
                + COLUMN_ENG + ", "
                + COLUMN_RU + ") VALUES " +
                "('Животные', 'cat', 'кот')," +
                "('Цифры', 'one', 'один')," +
                "('Приветствия и фразы', 'hello', 'привет')"
        );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + THEMES_LIST_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + WORDS_LIST_TABLE_NAME);
        this.onCreate(db);
    }


    public List<Theme> getTheme() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(THEMES_LIST_TABLE_NAME, THEMES_COLUMNS, null, null, null, null, null, null);

        List<Theme> theme = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                theme.add(new Theme(cursor.getString(cursor.getColumnIndex(COLUMN_THEME_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))));
                cursor.moveToNext();
            }
        }
        return theme;
    }

    public List<Word> getWord(String id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(WORDS_LIST_TABLE_NAME, WORDS_COLUMNS, "id_theme = ?",
                new String[] {id},  null, null, null);

        List<Word> word = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                    //TODO: Проверка на соответствие COLUMN_WORD_THEME_ID  и COLUMN_THEME_ID
                    word.add(new Word(cursor.getString(cursor.getColumnIndex(COLUMN_RU)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_ENG))));
                    cursor.moveToNext();
            }
        }
        return word;
    }

}
