package com.projeto.luzvioleta.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projeto.luzvioleta.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserController {

    private LuzDB dbHelper;

    public UserController(Context context) {
        dbHelper = new LuzDB(context);
    }

    public long insertUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());
        long id = db.insert("User", null, values);
        db.close();
        return id;
    }

    public User getUserByEmail(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("User", null, "email = ?", new String[]{email}, null, null, null);
        User user = null;
        if (cursor.moveToFirst()) {
            user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            user.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
            user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("email")));
            user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow("password")));
        }
        cursor.close();
        db.close();
        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("User", null, null, null, null, null, "name ASC");
        while (cursor.moveToNext()) {
            User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            user.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
            user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("email")));
            user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow("password")));
            users.add(user);
        }
        cursor.close();
        db.close();
        return users;
    }

    public int updateUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());
        int rows = db.update("User", values, "id = ?", new String[]{String.valueOf(user.getId())});
        db.close();
        return rows;
    }

    public int deleteUser(int userId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows = db.delete("User", "id = ?", new String[]{String.valueOf(userId)});
        db.close();
        return rows;
    }
    public int autenticarUsuario(String email, String senha) {
        User user = getUserByEmail(email);
        if (user != null && user.getPassword().equals(senha)) {
            return user.getId();
        }
        return -1;
    }

}
