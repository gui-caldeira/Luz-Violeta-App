package com.projeto.luzvioleta.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projeto.luzvioleta.data.model.Report;

import java.util.ArrayList;
import java.util.List;

public class ReportController {

    private LuzDB dbHelper;

    public ReportController(Context context) {
        dbHelper = new LuzDB(context);
    }

    public long insertReport(Report report) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userId", report.getUserId());
        values.put("description", report.getDescription());
        values.put("date", report.getDate());
        long id = db.insert("Report", null, values);
        db.close();
        return id;
    }

    public List<Report> getReportsByUserId(int userId) {
        List<Report> reports = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("Report", null, "userId = ?", new String[]{String.valueOf(userId)}, null, null, "date DESC");
        while (cursor.moveToNext()) {
            Report report = new Report();
            report.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            report.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow("userId")));
            report.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("description")));
            report.setDate(cursor.getString(cursor.getColumnIndexOrThrow("date")));
            reports.add(report);
        }
        cursor.close();
        db.close();
        return reports;
    }

    public int deleteReport(int reportId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows = db.delete("Report", "id = ?", new String[]{String.valueOf(reportId)});
        db.close();
        return rows;
    }
}
