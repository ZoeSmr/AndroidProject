package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.gauravk.audiovisualizer.visualizer.BarVisualizer;

import java.io.File;
import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {

    Button btn_play, btn_next, btn_prev;
    TextView txt_songName, txt_start, txt_end;
    SeekBar seekMusic;
    BarVisualizer visualizer;
    String song_name;
    public static final String EXTRA_NAME = "song_name";
    static MediaPlayer mediaPlayer;
    int pos;
    ArrayList<File> mySong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        btn_prev = findViewById(R.id.btn_prev);
        btn_next = findViewById(R.id.btn_next);
        btn_play = findViewById(R.id.btn_play);
        txt_songName = findViewById(R.id.txt_sn);
        txt_start = findViewById(R.id.txt_start);
        txt_end = findViewById(R.id.txt_end);
        seekMusic = findViewById(R.id.seekbar);
        visualizer = findViewById(R.id.bar);

        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        mySong = (ArrayList) bundle.getParcelableArrayList("songs");
        String songName = i.getStringExtra("songname");
        pos = bundle.getInt("pos", 0);
        txt_songName.setSelected(true);
        Uri uri = Uri.parse(mySong.get(pos).toString());
        song_name = mySong.get(pos).getName();
        txt_songName.setText(song_name);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
        mediaPlayer.start();

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()) {
                    btn_play.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24);
                    mediaPlayer.pause();
                }
                else {
                    btn_play.setBackgroundResource(R.drawable.ic_baseline_pause_24);
                    mediaPlayer.start();
                }
            }
        });
    }
}