package com.example.camera_permission;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;  // Import the Log class
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";  // Tag for logging
    private Button btnCapture;
    private ImageView imgCapture;
    private static final int IMAGE_CAPTURE_CODE = 1;
    private static final int CAMERA_PERMISSION_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCapture = findViewById(R.id.btnTakePicture);
        imgCapture = findViewById(R.id.capturedImage);

        if (ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.CAMERA"}, CAMERA_PERMISSION_CODE);
        }

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Capture button clicked");
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    Log.d(TAG, "Camera intent resolved");
                    startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE);
                } else {
                    Log.e(TAG, "Camera not available");
                    Toast.makeText(MainActivity.this, "Camera not available", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult called with requestCode: " + requestCode + ", resultCode: " + resultCode);

        if (requestCode == IMAGE_CAPTURE_CODE) {
            if (resultCode == RESULT_OK) {
                Log.d(TAG, "Image capture successful");
                if (data != null && data.getExtras() != null) {
                    Bitmap pic = (Bitmap) data.getExtras().get("data");
                    imgCapture.setImageBitmap(pic);
                    try {
                        MediaStore.Images.Media.insertImage(getContentResolver(), pic, "Image", "Image info");
                        Toast.makeText(this, "Image saved to gallery", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Image saved to gallery");
                    } catch (Exception e) {
                        Log.e(TAG, "Failed to save image", e);
                        Toast.makeText(this, "Failed to save image", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e(TAG, "Data or extras are null");
                    Toast.makeText(this, "Failed to capture image", Toast.LENGTH_SHORT).show();
                }
            } else if (resultCode == RESULT_CANCELED) {
                Log.d(TAG, "Camera operation canceled");
                Toast.makeText(this, "Camera operation canceled", Toast.LENGTH_SHORT).show();
            } else {
                Log.e(TAG, "Failed to capture image with resultCode: " + resultCode);
                Toast.makeText(this, "Failed to capture image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "Camera permission granted");
            } else {
                Log.e(TAG, "Camera permission denied");
                Toast.makeText(this, "Camera permission is required to use the camera", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
