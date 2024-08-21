package com.example.audio_player;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button playButton;
    private static final String TAG = "AudioPlayer";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.vandemataram);

        // Check if mediaPlayer is prepared
        if (mediaPlayer != null) {
            Log.d(TAG, "MediaPlayer is prepared and ready to play.");
        } else {
            Log.e(TAG, "MediaPlayer creation failed.");
        }

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    pauseMusic();
                } else {
                    playMusic();
                }
            }
        });

        // Setting max volume for testing
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
    }

    public void playMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
            Log.d(TAG, "Music started.");
            playButton.setText(R.string.pause_text);
        }
    }

    public void pauseMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            Log.d(TAG, "Music paused.");
            playButton.setText(R.string.play_text);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            Log.d(TAG, "MediaPlayer released.");
        }
    }
}
