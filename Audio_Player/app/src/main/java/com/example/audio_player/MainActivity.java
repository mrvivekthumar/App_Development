package com.example.audio_player;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button playButton;
    private static final String TAG = "AudioPlayer";
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.vivek);
        videoView = findViewById(R.id.video);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video);

        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();

        // Check if mediaPlayer is prepared
        if (mediaPlayer != null) {
            Log.d(TAG, "MediaPlayer is prepared and ready to play.");
        } else {
            Log.e(TAG, "MediaPlayer creation failed.");
        }

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying() || videoView.isPlaying()) {
                    pauseMedia();
                } else {
                    playMedia();
                }
            }
        });
    }

    public void playMedia() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
            Log.d(TAG, "Audio started.");
        }
        if (videoView != null) {
            videoView.start();
            Log.d(TAG, "Video started.");
        }
        playButton.setText(R.string.pause_text);
    }

    public void pauseMedia() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            Log.d(TAG, "Audio paused.");
        }
        if (videoView != null) {
            videoView.pause();
            Log.d(TAG, "Video paused.");
        }
        playButton.setText(R.string.play_text);
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
