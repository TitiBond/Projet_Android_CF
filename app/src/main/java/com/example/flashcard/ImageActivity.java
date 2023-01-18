package com.example.flashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity {
    int pictureId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);


        Intent srcIntent = getIntent();
        pictureId = srcIntent.getIntExtra("pictureId",0);

        ImageView zoomedImageView= findViewById(R.id.zoomedImageView);
        zoomedImageView.setImageResource(pictureId);


    }
}