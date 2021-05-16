package com.example.myproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class ThemeDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "themesList.db";
    private static final int SCHEMA = 2;
    static final String THEMES_LIST_TABLE_NAME = "THEMES_LIST";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PAGES = "pages";

    public static final String[] THEMES_COLUMNS = {COLUMN_ID, COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_PAGES};

    private static final String CREATE_THEMES_LIST_TABLE_NAME = "CREATE TABLE " + THEMES_LIST_TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_DESCRIPTION + " TEXT, "
            + COLUMN_PAGES + " INTEGER )";

    public ThemeDBHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_THEMES_LIST_TABLE_NAME);

        db.execSQL("INSERT INTO " + THEMES_LIST_TABLE_NAME + " ("
                + COLUMN_NAME + ", "
                + COLUMN_DESCRIPTION + ", "
                + COLUMN_PAGES + ") VALUES " +
                "('Буквы', 'Изучение алфавита \uD83D\uDCD5', 15)," +
                " ('Цифры', 'Учим цифры от 1 до 10 \uD83D\uDCAF', 12)," +
                " ('Приветствия и фразы', 'Скажем Hello! \uD83D\uDC4B\"', 12)," +
                " ('Семья', 'Обратимся на английском к родителям? \uD83D\uDC68\u200D\uD83D\uDC69\u200D\uD83D\uDC67\u200D\uD83D\uDC66', 13)," +
                " ('Цвета', 'Сможем назвать цвета радуги! \uD83C\uDF08', 12)," +
                " ('Еда', 'Назовём своё любимое блюдо \uD83D\uDE0B', 13)," +
                " ('Животные', 'Скажем котику, какой он милый \uD83D\uDC31', 7)," +
                " ('Природа и город', 'На прогулке с родителями покажем им новые умения \uD83D\uDE0E', 7)"
        );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + THEMES_LIST_TABLE_NAME);
        this.onCreate(db);
    }

    public long addItem(String name, String description, String pages) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_PAGES, pages);

        SQLiteDatabase db = this.getWritableDatabase();
        long returnId = db.insert(THEMES_LIST_TABLE_NAME, null, values);
        db.close();
        return returnId;
    }

    public List<Theme> getTheme() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(THEMES_LIST_TABLE_NAME, THEMES_COLUMNS, null, null, null, null, null, null);


        List<Theme> theme = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                theme.add(new Theme(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_PAGES))));
                cursor.moveToNext();
            }
        }
        return theme;
    }




}
