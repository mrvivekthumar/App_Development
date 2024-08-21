package com.example.sqlite_database;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FetchActivity extends AppCompatActivity {
    private TextView tvResults;
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);

        tvResults = findViewById(R.id.tv_results);
        databaseManager = new DatabaseManager(this);

        fetchData();
    }

    private void fetchData() {
        Cursor cursor = databaseManager.fetchData();
        StringBuilder builder = new StringBuilder();
        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            int age = cursor.getInt(cursor.getColumnIndexOrThrow("age"));
            builder.append("ID: ").append(id).append(", Name: ").append(name).append(", Age: ").append(age).append("\n");
        }
        tvResults.setText(builder.toString());
    }
}