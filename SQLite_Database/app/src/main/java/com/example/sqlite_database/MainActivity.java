package com.example.sqlite_database;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnInsert = findViewById(R.id.btn_insert);
        Button btnFetch = findViewById(R.id.btn_fetch);
        Button btnUpdate = findViewById(R.id.btn_update);
        Button btnDelete = findViewById(R.id.btn_delete);

        btnInsert.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, InsertActivity.class)));
        btnFetch.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, FetchActivity.class)));
        btnUpdate.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, UpdateActivity.class)));
        btnDelete.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, DeleteActivity.class)));

    }
}