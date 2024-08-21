package com.example.externalstorage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Environment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    EditText uname, pwd;

    Button saveBtn;
    FileOutputStream fstream;
    Intent intent;
    private String filename = "file.txt";
    File externalFile;
    private String filepath = "files";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uname = (EditText)findViewById(R.id.txtName);
        pwd = (EditText)findViewById(R.id.txtPwd);
        saveBtn = (Button)findViewById(R.id.btnSave);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = uname.getText().toString()+"\n";
                String password = pwd.getText().toString();

                try {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},23);
                    // on below line we are checking if the external storage is available on the device and the external storage is not read only.
                    if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
                        // if the external storage is not avialable then we are displaying the toast message on below line.
                        Toast.makeText(MainActivity.this, "External storage not available on the device..", Toast.LENGTH_SHORT).show();
                    } else {
                        // on below line we are initializing external file variable and specifying the file path along with file name.
                        Toast.makeText(MainActivity.this, "External storage is available on the device..", Toast.LENGTH_SHORT).show();
                        externalFile= new File(getExternalFilesDir(filepath), filename);

                    }


                    // externalFile2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                    //File myFile = new File(externalFile2,"user_details.txt");
                    //fstream = new FileOutputStream(myFile);
                    fstream = new FileOutputStream(externalFile);
                    //Toast.makeText(getApplicationContext(), " Working till here",Toast.LENGTH_SHORT).show();
                    fstream.write(username.getBytes());
                    fstream.write(password.getBytes());
                    fstream.close();
                    Toast.makeText(getApplicationContext(), "Details Saved in "+externalFile.getAbsolutePath(),Toast.LENGTH_SHORT).show();
                    intent = new Intent(MainActivity.this,details.class);
                    startActivity(intent);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    // on below line creating a method to checking weather external storage is read only.
    private static boolean isExternalStorageReadOnly() {
        // on below line getting external storage and checking if it is media mounted read only.
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }
    // on below line creating a method to check weather external storage is available or not.
    private static boolean isExternalStorageAvailable() {
        // on below line checking external storage weather it is available or not.
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }
}