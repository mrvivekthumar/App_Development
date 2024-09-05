package com.example.asynk_task;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button startButton;
    private EditText editTextTime;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.btn_run);
        editTextTime = findViewById(R.id.edittext_intime);
        textViewResult = findViewById(R.id.textview_result);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAsyncTaskRunner myAsyncTaskRunner = new MyAsyncTaskRunner();
                String sleepTime = editTextTime.getText().toString();
                myAsyncTaskRunner.execute(sleepTime);
            }
        });
    }

    private class MyAsyncTaskRunner extends AsyncTask<String, String, String> {

        private String response;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this, "Progress Dialog",
                    "Wait for " + editTextTime.getText().toString() + " seconds");
        }

        @Override
        protected void onProgressUpdate(String... updateText) {
            textViewResult.setText(updateText[0]);
        }

        @Override
        protected String doInBackground(String... params) {
            publishProgress("slepping ...");

            try {
                int timeInSeconds = Integer.parseInt(params[0]);
                for (int i = 0; i < timeInSeconds; i++) {
                    Thread.sleep(1000);

                    response = "Slept for " + (i + 1) + " of " + timeInSeconds + " seconds";

                    publishProgress(response); // Calls onProgressUpdate()
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                response = e.getMessage();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result){
            progressDialog.dismiss();
            textViewResult.setText(result);
        }

    }

}
