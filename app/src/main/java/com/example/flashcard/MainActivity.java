package com.example.flashcard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    String selectedLevel;
    final String teamMember = "ASMA, JAMES, JULIEN, THIBAULT";
    final String applicationName = "QUIZ GAME";
    final String versionNumber = "Version 2.0.2.3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button showQuestionButton = findViewById(R.id.showQuestionButton);
        Button playButton = findViewById(R.id.playButton);
        Button aboutButton = findViewById(R.id.aboutButton);

        // S'abonner aux boutons du menu
        showQuestionButton.setOnClickListener(this);
        playButton.setOnClickListener(this);
        aboutButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.showQuestionButton:
                Intent intent2 = new Intent(MainActivity.this, QuestionListActivity.class);
                startActivity(intent2);
                break;
            case R.id.playButton:
                selectedLevel = "Facile";
                final String[] levels = {"Facile", "Moyen", "Difficile"};
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Choisissez le niveau de difficulté");
                alert.setSingleChoiceItems(levels, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        selectedLevel = levels[i];
                    }
                });
                alert.setPositiveButton("Jouer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MainActivity.this,QuestionActivity.class);
                        intent.putExtra("quiz", QuizRepo.generateQuiz(selectedLevel));
                        intent.putExtra("selectedLevel", selectedLevel);
                        startActivity(intent);
                    }
                });
                alert.show();
                break;
            case R.id.aboutButton:
                Intent intent = new Intent(MainActivity.this,AboutActivity.class);
                intent.putExtra("teamMember", teamMember);
                intent.putExtra("applicationName",  applicationName);
                intent.putExtra("versionNumber",  versionNumber);
                startActivity(intent);
                break;

        }
    }
}