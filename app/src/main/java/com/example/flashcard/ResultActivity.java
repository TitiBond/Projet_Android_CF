package com.example.flashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        TextView levelTextView = findViewById(R.id.levelTextView);
        TextView goodAnswersTextView = findViewById(R.id.goodAnswersCountTextView);
        Button backToMenubutton = findViewById(R.id.backToMenuButton);
        TextView percentageTextView = findViewById(R.id.percentageTextView);
        Intent srcIntent = getIntent();
        int goodAnswerNb = srcIntent.getIntExtra("goodAnswers",0);
        int questionNb = srcIntent.getIntExtra("questionsNumber", 0);
        double percentageResult = (double) goodAnswerNb/ (double) questionNb*100;
        levelTextView.setText("Difficulté : " + srcIntent.getStringExtra("selectedLevel"));
        goodAnswersTextView.setText("SCORE : " + goodAnswerNb + "/" + questionNb);
        percentageTextView.setText(String.format("%.2f",percentageResult) + " %");

        backToMenubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}