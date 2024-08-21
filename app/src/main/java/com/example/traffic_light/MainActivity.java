package com.example.traffic_light;

import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button stop, wait, go;
    private ImageView i1;
    private Bitmap b1;
    private Canvas c1;
    private Paint p1, borderPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = Bitmap.createBitmap(300, 900, Bitmap.Config.ARGB_8888); // Create a taller bitmap for three circles
        c1 = new Canvas(b1);
        i1 = findViewById(R.id.imageView);
        p1 = new Paint();

        // Paint for the black border
        borderPaint = new Paint();
        borderPaint.setColor(Color.BLACK);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(10);

        // Initially draw white circles for all three lights
        drawTrafficLight(Color.WHITE, Color.WHITE, Color.WHITE);

        stop = findViewById(R.id.button1);
        wait = findViewById(R.id.button2);
        go = findViewById(R.id.button3);

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawTrafficLight(Color.RED, Color.WHITE, Color.WHITE);
            }
        });

        wait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawTrafficLight(Color.WHITE, Color.YELLOW, Color.WHITE);
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawTrafficLight(Color.WHITE, Color.WHITE, Color.GREEN);
            }
        });
    }

    private void drawTrafficLight(int topColor, int middleColor, int bottomColor) {
        // Top Circle
        c1.drawCircle(150, 150, 105, borderPaint); // Draw border
        p1.setColor(topColor);
        c1.drawCircle(150, 150, 100, p1); // Draw filled circle

        // Middle Circle
        c1.drawCircle(150, 450, 105, borderPaint); // Draw border
        p1.setColor(middleColor);
        c1.drawCircle(150, 450, 100, p1); // Draw filled circle

        // Bottom Circle
        c1.drawCircle(150, 750, 105, borderPaint); // Draw border
        p1.setColor(bottomColor);
        c1.drawCircle(150, 750, 100, p1); // Draw filled circle

        i1.setImageBitmap(b1); // Set the updated bitmap to the ImageView
    }
}