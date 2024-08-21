package com.example.sqlite_database;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InsertActivity extends AppCompatActivity {

    private EditText etName, etAge;
    private TextView tvResult;
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        etName = findViewById(R.id.et_name);
        etAge = findViewById(R.id.et_age);
        tvResult = findViewById(R.id.tv_result);

        databaseManager = new DatabaseManager(this);

        Button btnInsert = findViewById(R.id.btn_insert);
        btnInsert.setOnClickListener(view -> insertData());
    }

    private void insertData() {
        String name = etName.getText().toString();
        int age = Integer.parseInt(etAge.getText().toString());
        long id = databaseManager.insertData(name, age);
        tvResult.setText("Inserted with ID: " + id);
    }
}