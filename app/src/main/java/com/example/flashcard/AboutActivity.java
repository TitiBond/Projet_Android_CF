package com.example.flashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    String teamMember;
    String applicationName;
    String versionNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Intent srcIntent = getIntent();
        teamMember = srcIntent.getStringExtra("teamMember");
        applicationName = srcIntent.getStringExtra("applicationName");
        versionNumber = srcIntent.getStringExtra("versionNumber");


        //Nom de l'application
        TextView applicationNameTextView = findViewById(R.id.applicationNameTextView);
        applicationNameTextView.setText(applicationName);

        //Les membres de l'équipe
        TextView teamMemberTextView = findViewById(R.id.teamMemberTextView);
        teamMemberTextView.setText(teamMember);

        //Numéro de version Dynamique
        TextView versionTextView = findViewById(R.id.versionTextView);
        versionTextView.setText(BuildConfig.VERSION_NAME);




        // Button back to MainActivity
        Button backToMainButton = findViewById(R.id.backToMainButton);
        backToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }
}