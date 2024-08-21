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

public class DeleteActivity extends AppCompatActivity {

    private EditText etId;
    private TextView tvStatus;
    private DatabaseManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        etId = findViewById(R.id.et_id);
        tvStatus = findViewById(R.id.tv_status);

        Button btnDelete = findViewById(R.id.btn_delete);
        databaseManager = new DatabaseManager(this);

        btnDelete.setOnClickListener(view -> deleteData());
    }

    private void deleteData() {
        long id = Long.parseLong(etId.getText().toString());
        int rows = databaseManager.deleteData(id);
        tvStatus.setText("Deleted Rows: " + rows);
    }
}