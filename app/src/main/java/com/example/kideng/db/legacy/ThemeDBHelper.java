package com.example.kideng.db.legacy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kideng.db.entities.Word;
import com.example.kideng.db.entities.Theme;

import java.util.ArrayList;
import java.util.List;


public class ThemeDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "themesList.db";
    private static final int SCHEMA = 10;
    static final String THEMES_LIST_TABLE_NAME = "THEMES_LIST";
    static final String WORDS_LIST_TABLE_NAME = "WORDS_LIST";
    static final String USERS_LIST_TABLE_NAME = "USERS_LIST";

    public static final String COLUMN_THEME_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_WORD_ID = "_id";
    public static final String COLUMN_WORD_THEME = "id_theme";
    public static final String COLUMN_ENG = "eng";
    public static final String COLUMN_RU = "ru";
    public static final String COLUMN_USER_ID = "_id";
    public static final String COLUMN_USER_NICKNAME = "nickname";
    public static final String COLUMN_USER_NAME = "name";

    public static final String[] THEMES_COLUMNS = {COLUMN_THEME_ID, COLUMN_NAME, COLUMN_DESCRIPTION};
    public static final String[] WORDS_COLUMNS = {COLUMN_WORD_ID, COLUMN_WORD_THEME, COLUMN_ENG, COLUMN_RU};
    public static final String[] USERS_COLUMNS = {COLUMN_USER_ID, COLUMN_USER_NICKNAME, COLUMN_USER_NAME};


    private static final String CREATE_THEMES_LIST_TABLE_NAME = "CREATE TABLE " + THEMES_LIST_TABLE_NAME + "("
            + COLUMN_THEME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_DESCRIPTION + " TEXT )";

    private static final String CREATE_WORDS_LIST_TABLE_NAME = "CREATE TABLE " + WORDS_LIST_TABLE_NAME + "("
            + COLUMN_WORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_WORD_THEME + " TEXT, "
            + COLUMN_ENG + " TEXT, "
            + COLUMN_RU + " TEXT )";

    private static final String CREATE_USERS_LIST_TABLE_NAME = "CREATE TABLE " + USERS_LIST_TABLE_NAME + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USER_NICKNAME + " TEXT, "
            + COLUMN_USER_NAME + " TEXT )";

    public ThemeDBHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_THEMES_LIST_TABLE_NAME);
        db.execSQL(CREATE_WORDS_LIST_TABLE_NAME);
        db.execSQL(CREATE_USERS_LIST_TABLE_NAME);

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
                " ('Природа и город', 'На прогулке с родителями покажем им новые умения \uD83D\uDE0E')," +
                "('Другое', 'Слова, которые не вошли в другие категории 😶')"
        );

        db.execSQL("INSERT INTO " + WORDS_LIST_TABLE_NAME + " ("
                + COLUMN_WORD_THEME + ", "
                + COLUMN_ENG + ", "
                + COLUMN_RU + ") VALUES "
                + "(1, 'ant', 'муравей'),"
                + "(1, 'ball', 'мяч'),"
                + "(1, 'carrot', 'морковка'),"
                + "(1, 'dog', 'собака'),"
                + "(1, 'eggs', 'яйца'),"
                + "(1, 'frog', 'лягушка'),"
                + "(1, 'grapes', 'виноград'),"
                + "(1, 'house', 'дом'),"
                + "(1, 'ice-cream', 'мороженое'),"
                + "(1, 'jelly-fish', 'медуза'),"
                + "(1, 'kite', 'воздушный змей'),"
                + "(1, 'lion', 'лев'),"
                + "(1, 'mouse', 'мышь'),"
                + "(1, 'nut', 'орех'),"
                + "(1, 'orange (fruit)', 'апельсин'),"
                + "(1, 'peas', 'горох'),"
                + "(1, 'queen', 'королева'),"
                + "(1, 'rabbit', 'кролик'),"
                + "(1, 'strawberry', 'клубника'),"
                + "(1, 'tomato', 'помидор'),"
                + "(1, 'umbrella', 'зонтик'),"
                + "(1, 'volcano', 'вулкан'),"
                + "(1, 'water', 'вода'),"
                + "(1, 'xylophone', 'ксилофон'),"
                + "(1, 'yogurt', 'йогурт'),"
                + "(1, 'zebra', 'зебра'),"
                + "(2, 'one', 'один'),"
                + "(2, 'two', 'два'),"
                + "(2, 'three', 'три'),"
                + "(2, 'four', 'четыре'),"
                + "(2, 'five', 'пять'),"
                + "(2, 'six', 'шесть'),"
                + "(2, 'seven', 'семь'),"
                + "(2, 'eight', 'восемь'),"
                + "(2, 'nine', 'девять'),"
                + "(2, 'ten', 'десять'),"
                + "(3, 'hello', 'привет'),"
                + "(3, 'hi', 'привет'),"
                + "(3, 'goodbye', 'до свидания'),"
                + "(3, 'bye', 'пока'),"
                + "(3, 'good morning', 'доброе утро'),"
                + "(3, 'good afternoon', 'добрый день'),"
                + "(3, 'good evening', 'добрый вечер'),"
                + "(3, 'good night', 'доброй ночи'),"
                + "(3, 'how are you?', 'как дела?'),"
                + "(3, 'see you later', 'до встречи'),"
                + "(3, 'see you soon', 'до скорой встречи'),"
                + "(4, 'mother', 'мама'),"
                + "(4, 'father', 'папа'),"
                + "(4, 'brother', 'брат'),"
                + "(4, 'sister', 'сестра'),"
                + "(4, 'grandfather', 'дедушка'),"
                + "(4, 'grandmother', 'бабушка'),"
                + "(4, 'aunt', 'тётя'),"
                + "(4, 'uncle', 'дядя'),"
                + "(4, 'son', 'сын'),"
                + "(4, 'daughter', 'дочь'),"
                + "(5, 'red', 'красный'),"
                + "(5, 'orange (color)', 'оранжевый'),"
                + "(5, 'yellow', 'жёлтый'),"
                + "(5, 'green', 'зелёный'),"
                + "(5, 'blue', 'голубой'),"
                + "(5, 'dark blue', 'синий'),"
                + "(5, 'purple', 'фиолетовый'),"
                + "(5, 'white', 'белый'),"
                + "(5, 'black', 'чёрный'),"
                + "(5, 'brown', 'коричневый'),"
                + "(6, 'milk', 'молоко'),"
                + "(6, 'water', 'вода'),"
                + "(6, 'tea', 'чай'),"
                + "(6, 'coffee', 'кофе'),"
                + "(6, 'juice', 'сок'),"
                + "(6, 'cucumber', 'огурец'),"
                + "(6, 'apple', 'яблоко'),"
                + "(6, 'banana', 'банан'),"
                + "(6, 'cereal', 'хлопья'),"
                + "(6, 'pear', 'груша'),"
                + "(7, 'cat', 'кот'),"
                + "(7, 'chicken', 'курица'),"
                + "(7, 'parrot', 'попугай'),"
                + "(7, 'pig', 'свинья'),"
                + "(7, 'sheep', 'овца'),"
                + "(7, 'cow', 'корова'),"
                + "(7, 'pigeon', 'голубь'),"
                + "(7, 'sparrow', 'воробей'),"
                + "(7, 'rat', 'крыса'),"
                + "(7, 'dragon', 'дракон'),"
                + "(7, 'dog', 'собака'),"
                + "(8, 'city', 'город'),"
                + "(8, 'street', 'улица'),"
                + "(8, 'square', 'площадь'),"
                + "(8, 'forest', 'лес'),"
                + "(8, 'bridge', 'мост'),"
                + "(8, 'scyscraper', 'небоскрёб'),"
                + "(8, 'house', 'дом'),"
                + "(8, 'building', 'здание'),"
                + "(8, 'school', 'школа'),"
                + "(8, 'store', 'магазин'),"
                + "(8, 'garden', 'сад'),"
                + "(8, 'river', 'река'),"
                + "(8, 'sea', 'море'),"
                + "(8, 'ocean', 'океан'),"
                + "(8, 'sun', 'солнце'),"
                + "(8, 'moon', 'луна'),"
                + "(8, 'lake', 'озеро')"

        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + THEMES_LIST_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + WORDS_LIST_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USERS_LIST_TABLE_NAME);
        this.onCreate(db);
    }

    public List<Theme> getTheme() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(THEMES_LIST_TABLE_NAME, THEMES_COLUMNS, null, null, null, null, null, null);

        List<Theme> theme = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                theme.add(new Theme(cursor.getInt(cursor.getColumnIndex(COLUMN_THEME_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))));
                cursor.moveToNext();
            }
        }
        return theme;
    }

    public List<Word> getWord(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(WORDS_LIST_TABLE_NAME, WORDS_COLUMNS, "id_theme = ?",
                new String[]{Integer.toString(id)}, null, null, null);

        List<Word> word = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                word.add(new Word(cursor.getInt(cursor.getColumnIndex(COLUMN_WORD_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_RU)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ENG))));
                cursor.moveToNext();
            }
        }
        return word;
    }

    public Cursor getWord1(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(WORDS_LIST_TABLE_NAME, WORDS_COLUMNS, null,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToPosition(id);
        }
        return cursor;
    }

    public String getUser() {
        String nickname = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(USERS_LIST_TABLE_NAME, USERS_COLUMNS, null,
                null, null, null, null);
        if ( cursor != null && cursor.moveToFirst() ){
             nickname = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NICKNAME));
            cursor.close();
        }
        return nickname;

    }

    public int getDBNoteCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Count(*) FROM WORDS_LIST", null);
        cursor.moveToLast();
        return cursor.getInt(0);
    }

    public long insertUser(String nickname, String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USER_NICKNAME, nickname);
        cv.put(COLUMN_USER_NAME, name);
        return db.insert(USERS_LIST_TABLE_NAME, null, cv);
    }

    public long insertWord(int wordTheme, String engWord, String rusWord) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_WORD_THEME, wordTheme);
        cv.put(COLUMN_ENG, engWord);
        cv.put(COLUMN_RU, rusWord);
        return db.insert(WORDS_LIST_TABLE_NAME, null, cv);
    }

}

