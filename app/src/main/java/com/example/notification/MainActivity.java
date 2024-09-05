package com.example.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "notification_channel";
    private static final int NOTIFICATION_ID = 1;

    private EditText editTextMessage;
    private Button buttonSendNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMessage = findViewById(R.id.text_message);
        buttonSendNotification = findViewById(R.id.button);

        buttonSendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editTextMessage.getText().toString();
                sendNotification(message);
            }
        });
    }

    private void sendNotification(String message) {
        createNotificationChannel();

        Intent intent = new Intent(this, MessageActivity.class);
        intent.putExtra("message", message);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE);

        Notification.Builder builder = new Notification.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Notification Demo")
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void createNotificationChannel() {
        // checking if android version is greater than oreo (API 26) or not
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Check version to allow channel.

            CharSequence channelName = "Message Channel";
            String channelDescription = "This is a message channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, channelName, importance);

            // Configure and create notification channel
            notificationChannel.enableVibration(false);
            notificationChannel.setDescription(channelDescription);

            // Get notification manager
            NotificationManager notificationManager = getSystemService(NotificationManager.class);

            // Create notification channel using notification manager.
            // It is kind of registration
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}