package com.example.musicplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gauravk.audiovisualizer.visualizer.BarVisualizer;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class PlayerActivity extends AppCompatActivity {

    Button btn_play, btn_next, btn_prev;
    RadioButton rbtn_random, rbtn_single, rbtn_seq;
    TextView txt_songName, txt_start, txt_end;
    SeekBar seekMusic;
    BarVisualizer visualizer;
    ImageView imageView;
    String song_name;
    public static final String EXTRA_NAME = "song_name";
    static MediaPlayer mediaPlayer;
    int pos;
    ArrayList<File> mySong;
    Thread updateseekbar;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        if(visualizer != null) {
            visualizer.release();
        }
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btn_prev = findViewById(R.id.btn_prev);
        btn_next = findViewById(R.id.btn_next);
        btn_play = findViewById(R.id.btn_play);
        txt_songName = findViewById(R.id.txt_sn);
        txt_start = findViewById(R.id.txt_start);
        txt_end = findViewById(R.id.txt_end);
        seekMusic = findViewById(R.id.seekbar);
        visualizer = findViewById(R.id.bar);
        imageView = findViewById(R.id.imageview);
        rbtn_single = findViewById(R.id.single);
        rbtn_seq = findViewById(R.id.seq);
        rbtn_random = findViewById(R.id.random);

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

        updateseekbar = new Thread() {
            @Override
            public void run() {
                int totalDuration = mediaPlayer.getDuration();
                int currentposition = 0;

                while (currentposition < totalDuration) {
                    try {
                        sleep(500);
                        currentposition = mediaPlayer.getCurrentPosition();
                        seekMusic.setProgress(currentposition);
                    }
                    catch (InterruptedException | IllegalStateException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        };
        seekMusic.setMax(mediaPlayer.getDuration());
        updateseekbar.start();
        seekMusic.getProgressDrawable().setColorFilter(getResources().getColor(R.color.design_default_color_primary), PorterDuff.Mode.MULTIPLY);
        seekMusic.getThumb().setColorFilter(getResources().getColor(R.color.design_default_color_primary), PorterDuff.Mode.SRC_IN);
        seekMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        String endTime = createTime(mediaPlayer.getDuration());
        txt_end.setText(endTime);

        final Handler handler = new Handler();
        final int delay = 1000;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String currentTime = createTime(mediaPlayer.getCurrentPosition());
                txt_start.setText(currentTime);
                handler.postDelayed(this, delay);
            }
        }, delay);

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

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                btn_next.performClick();
            }
        });

        int audiosessionId = mediaPlayer.getAudioSessionId();
        if(audiosessionId != -1) {
            visualizer.setAudioSessionId(audiosessionId);
        }

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();

                if(rbtn_single.isChecked()) {
                    single();
                }
                else if(rbtn_seq.isChecked()) {
                    sequence();
                }
                else if(rbtn_random.isChecked()) {
                    random();
                }

            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
                if(rbtn_single.isChecked()) {
                    single();
                }
                else if(rbtn_seq.isChecked()) {
                    sequence();
                }
                else if(rbtn_random.isChecked()) {
                    random();
                }
            }
        });
    }

    public void single() {
        Uri uri1 = Uri.parse(mySong.get(pos).toString());
        mediaPlayer = MediaPlayer.create(getApplicationContext(), uri1);
        song_name = mySong.get(pos).getName();
        txt_songName.setText(song_name);
        mediaPlayer.start();
        btn_play.setBackgroundResource(R.drawable.ic_baseline_pause_24);
        Toast.makeText(PlayerActivity.this, "单曲循环", Toast.LENGTH_SHORT).show();
        startAnimation(imageView);
        int audiosessionId = mediaPlayer.getAudioSessionId();
        if(audiosessionId != -1) {
            visualizer.setAudioSessionId(audiosessionId);
        }
    }

    public void sequence() {
        pos = ((pos+1)%mySong.size());
        Uri uri1 = Uri.parse(mySong.get(pos).toString());
        mediaPlayer = MediaPlayer.create(getApplicationContext(), uri1);
        song_name = mySong.get(pos).getName();
        txt_songName.setText(song_name);
        mediaPlayer.start();
        btn_play.setBackgroundResource(R.drawable.ic_baseline_pause_24);
        Toast.makeText(PlayerActivity.this, "顺序播放", Toast.LENGTH_SHORT).show();
        startAnimation(imageView);
        int audiosessionId = mediaPlayer.getAudioSessionId();
        if(audiosessionId != -1) {
            visualizer.setAudioSessionId(audiosessionId);
        }
    }

    public void random() {
        pos = new Random().nextInt(mySong.size());
        Uri uri1 = Uri.parse(mySong.get(pos).toString());
        mediaPlayer = MediaPlayer.create(getApplicationContext(), uri1);
        song_name = mySong.get(pos).getName();
        txt_songName.setText(song_name);
        mediaPlayer.start();
        btn_play.setBackgroundResource(R.drawable.ic_baseline_pause_24);
        Toast.makeText(PlayerActivity.this, "随机播放", Toast.LENGTH_SHORT).show();
        startAnimation(imageView);
        int audiosessionId = mediaPlayer.getAudioSessionId();
        if(audiosessionId != -1) {
            visualizer.setAudioSessionId(audiosessionId);
        }
    }

    public void startAnimation(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 359f);
        animator.setDuration(500);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator);
        animatorSet.start();
    }

    public String createTime(int duration) {
        String time = "";
        int min = duration/1000/60;
        int sec = duration/1000%60;
        time += min + ":";

        if(sec < 10) {
            time += "0";
        }
        time += sec;

        return time;
    }
}