package com.example.flashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionActivity extends AppCompatActivity {
    boolean userAnswered;
    String selectedLevel;
    Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        userAnswered = false;

        //Récupération de l'intent de MainActivity
        Intent srcIntent = getIntent();
        quiz = srcIntent.getParcelableExtra("quiz");
        selectedLevel = srcIntent.getStringExtra("selectedLevel");
        Question quest = quiz.getQuestionsList().get(quiz.getIndexQuestion());


        //on stock les views dans des variables
        ImageView questionImageView = findViewById(R.id.questionImageView);
        questionImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionActivity.this, ImageActivity.class);
                intent.putExtra("pictureId", quest.getPictureId());
                startActivity(intent);
            }
        });
        Button playSoundButton = findViewById(R.id.playSoundButton);
        TextView questionTextView = findViewById(R.id.questionTextView);
        questionTextView.setText(quest.getQuestion());
        RadioGroup answersRadioGroup = findViewById(R.id.answersRadioGroup);
        Button submitButton = findViewById(R.id.submitButton);
        TextView commentAnswerTextView = findViewById(R.id.commentAnswerTextView);
        TextView goodAnswerTextView = findViewById(R.id.goodAnswerTextView);
        goodAnswerTextView.setText("La bonne réponse est : " + quest.getGoodAnswer());
        TextView currentQuestionTextView = findViewById(R.id.currentQuestionTextView);


        //Mise à jour du bandeau des question en cours
        currentQuestionTextView.setText("Question : " + (quiz.getIndexQuestion()+1) + " / " + quiz.getQuestionsList().size());





        //On rend invisible en fonction des questions, l'image ou le bouton pour jouer le son
        if (quest.getPictureId() != 0){ //Si il y a une photo alors on cache le lecteur de son
            questionImageView.setImageResource(quest.getPictureId());
            playSoundButton.setVisibility(View.GONE);
        }else if(quest.getSoundId() != 0 ){ //si il y a un son alors on cache la photo
            MediaPlayer soundOne = MediaPlayer.create(this,quest.getSoundId());
            playSoundButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    soundOne.start();
                }
            });
            questionImageView.setVisibility(View.GONE);
        }else{ //si il n'y a pas de photo et de son alors on cache les deux
            playSoundButton.setVisibility(View.GONE);
            questionImageView.setVisibility(View.GONE);
        }



        //génération des radioButton pour les choix de réponse
        for (int i = 0; i < quest.getAnswers().size(); i++) {
            RadioButton answer = new RadioButton(this);
            answer.setText(quest.getAnswers().get(i));
            answersRadioGroup.addView(answer);
        }

        //On s'abonne au clique sur le bouton "Valider la réponse"
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //condition qui vérifie si le joueur a déjà répondu, afin d'adapter le contenu du bouton "valider"/"question suivante"²
                if(userAnswered == false){
                    int checkedRadioButtonId = answersRadioGroup.getCheckedRadioButtonId(); //on récupere l'id du radioBouton sélectionné
                    //Si aucun bouton n'est sélectionné alors la valeur récupérée est -1 et donc on prévient l'utilisateur de choisir une réponse.
                    if (checkedRadioButtonId == -1){
                        Toast.makeText(QuestionActivity.this, "Veuillez selectionner une réponse !", Toast.LENGTH_SHORT).show();
                        //Sinon on récupère l'id et on compare sa valeur à celle de la bonne réponse
                    }else{
                        RadioButton radioButtonSelected = answersRadioGroup.findViewById(checkedRadioButtonId);

                        if (radioButtonSelected.getText().equals(quest.getGoodAnswer())){
                            commentAnswerTextView.setText("BONNE REPONSE !!");
                            quiz.setGoodAnswerCount(quiz.getGoodAnswerCount()+1);
                        }else{
                            commentAnswerTextView.setText("MAUVAISE REPONSE !!");
                        }
                        //lorsque le joueur a répondu, on affiche les views indiquant si la réponse est bonne ou pas,avec la bonne réponse
                        commentAnswerTextView.setVisibility(View.VISIBLE);
                        goodAnswerTextView.setVisibility(View.VISIBLE);
                        quiz.setIndexQuestion(quiz.getIndexQuestion()+1);
                        userAnswered = true;

                        //On vérifie si la question est la dernière question.
                        //si c'est la dernière question alors apres que le joueur ai cliqué, on affiche "Voir résultat"
                        //sinon on affiche "Question suivante"
                        if(quiz.getIndexQuestion() == quiz.getQuestionsList().size()){
                            submitButton.setText("Voir résultat");
                        }else{
                            submitButton.setText("Question suivante");
                        }
                    }
                    //On vérifie si c'est la dernère question afin de lancer la bonne activité
                    //si c'est la dernière question, on lance l'écran de résultat
                    //sinon on lance la question suivante
                }else{
                    if(quiz.getIndexQuestion() == quiz.getQuestionsList().size()){
                        Intent intent = new Intent(QuestionActivity.this,ResultActivity.class);
                        intent.putExtra("goodAnswers", quiz.getGoodAnswerCount());
                        intent.putExtra("selectedLevel", selectedLevel);
                        intent.putExtra("questionsNumber", quiz.getQuestionsList().size());
                        startActivity(intent);
                        finish();
                    }else{
                        Intent intent = new Intent(QuestionActivity.this, QuestionActivity.class);
                        intent.putExtra("quiz", quiz);
                        intent.putExtra("selectedLevel", selectedLevel);
                        startActivity(intent);
                        finish();
                    }

                }
            }
        });
    }
}