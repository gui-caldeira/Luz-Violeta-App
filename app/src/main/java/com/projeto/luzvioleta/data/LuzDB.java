package com.projeto.luzvioleta.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LuzDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "luzvioleta.db";
    private static final int DATABASE_VERSION = 1;

    public LuzDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE User (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "email TEXT NOT NULL UNIQUE, " +
                "password TEXT NOT NULL)";
        db.execSQL(createUserTable);

        String createReportTable = "CREATE TABLE Report (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "userId INTEGER NOT NULL, " +
                "description TEXT, " +
                "date TEXT, " +
                "FOREIGN KEY(userId) REFERENCES User(id))";
        db.execSQL(createReportTable);

        String createMedicalAssistanceTable = "CREATE TABLE MedicalAssistance (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "userId INTEGER NOT NULL, " +
                "type TEXT, " +
                "date TEXT, " +
                "FOREIGN KEY(userId) REFERENCES User(id))";
        db.execSQL(createMedicalAssistanceTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS MedicalAssistance");
        db.execSQL("DROP TABLE IF EXISTS Report");
        db.execSQL("DROP TABLE IF EXISTS User");
        onCreate(db);
    }
}
