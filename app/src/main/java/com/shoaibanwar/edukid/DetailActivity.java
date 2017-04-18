package com.shoaibanwar.edukid;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.transition.Visibility;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;

import com.shoaibanwar.edukid.model.Lesson;
import com.shoaibanwar.edukid.model.LessonDataStore;

public class DetailActivity extends AppCompatActivity {

    private CustomVideoView videoView;
    private ImageButton playPauseButton;
    private ImageButton quizButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int height = displayMetrics.heightPixels;
        final int width = displayMetrics.widthPixels;

        videoView = (CustomVideoView) findViewById(R.id.videoView);
        playPauseButton = (ImageButton) findViewById(R.id.playpauseButton);
        quizButton = (ImageButton) findViewById(R.id.quizButton);

        int lessonNum = getIntent().getIntExtra("LessonPosition",0);
        final Lesson lesson = new LessonDataStore(DetailActivity.this).getLessonList().get(lessonNum);

        videoView.setVideoURI(Uri.parse(lesson.getAnimation_Uri()));
        videoView.resizeVideo(width,height);

        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this,QuizActivity.class);
                intent.putExtra("LessonPosition",getIntent().getIntExtra("LessonPosition",0));
                startActivity(intent);
            }
        });

        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playPauseButton.setVisibility(View.GONE);
                videoView.start();
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                playPauseButton.setVisibility(View.VISIBLE);
            }
        });
    }
}
