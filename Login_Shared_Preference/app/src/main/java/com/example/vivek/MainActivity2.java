package com.example.vivek;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {

    private static final String FILE_NAME = "user_data.txt";
    FileInputStream fi = null;
    StringBuilder stringBuilder = new StringBuilder();
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        try{
            fi = openFileInput("user_data.txt");
            int ch;
            while((ch = fi.read() )!= -1){
              stringBuilder.append( (char)ch);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if (fi != null) {
                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        t = findViewById(R.id.adminInfoTextView);
        String s =  stringBuilder.toString();
        t.setText(s);

    }
}