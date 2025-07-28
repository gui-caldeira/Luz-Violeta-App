package com.projeto.luzvioleta.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projeto.luzvioleta.data.model.MedicalAssistance;

import java.util.ArrayList;
import java.util.List;

public class MedicalAssistanceController {

    private LuzDB dbHelper;

    public MedicalAssistanceController(Context context) {
        dbHelper = new LuzDB(context);
    }

    public long insertMedicalAssistance(MedicalAssistance medicalAssistance) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userId", medicalAssistance.getUserId());
        values.put("type", medicalAssistance.getType());
        values.put("date", medicalAssistance.getDate());
        long id = db.insert("MedicalAssistance", null, values);
        db.close();
        return id;
    }

    public List<MedicalAssistance> getMedicalAssistancesByUserId(int userId) {
        List<MedicalAssistance> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("MedicalAssistance", null, "userId = ?", new String[]{String.valueOf(userId)}, null, null, "date DESC");
        while (cursor.moveToNext()) {
            MedicalAssistance ma = new MedicalAssistance();
            ma.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            ma.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow("userId")));
            ma.setType(cursor.getString(cursor.getColumnIndexOrThrow("type")));
            ma.setDate(cursor.getString(cursor.getColumnIndexOrThrow("date")));
            list.add(ma);
        }
        cursor.close();
        db.close();
        return list;
    }

    public int deleteMedicalAssistance(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows = db.delete("MedicalAssistance", "id = ?", new String[]{String.valueOf(id)});
        db.close();
        return rows;
    }
}
