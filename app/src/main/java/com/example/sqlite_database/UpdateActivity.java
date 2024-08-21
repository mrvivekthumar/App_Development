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

public class UpdateActivity extends AppCompatActivity {

    private EditText etId, etName, etAge;
    private TextView tvStatus;
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        etId = findViewById(R.id.et_id);
        etName = findViewById(R.id.et_name);
        etAge = findViewById(R.id.et_age);
        tvStatus = findViewById(R.id.tv_status);

        Button btnUpdate = findViewById(R.id.btn_update);
        databaseManager = new DatabaseManager(this);

        btnUpdate.setOnClickListener(view -> updateData());
    }

    private void updateData() {
        long id = Long.parseLong(etId.getText().toString());
        String name = etName.getText().toString();
        int age = Integer.parseInt(etAge.getText().toString());
        int rows = databaseManager.updateData(id, name, age);
        tvStatus.setText("Updated Rows: " + rows);
    }
}