package com.example.sqlite_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
    private DatabaseHelper dbHelper;

    public DatabaseManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long insertData(String name, int age) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        return db.insert("myTable", null, values);
    }

    public Cursor fetchData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.query("myTable", null, null, null, null, null, null);
    }

    public int updateData(long id, String name, int age) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        return db.update("myTable", values, "id=?", new String[]{String.valueOf(id)});
    }

    public int deleteData(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete("myTable", "id=?", new String[]{String.valueOf(id)});
    }
}

