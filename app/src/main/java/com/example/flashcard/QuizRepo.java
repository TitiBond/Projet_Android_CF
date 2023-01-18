package com.example.flashcard;

import java.util.ArrayList;
import java.util.Collections;

public class QuizRepo {

    public static Quiz generateQuiz(String level) {

        int choiceCount = 0;
        switch (level){
            case "Facile" :
                choiceCount = 3;
                break;
            case "Moyen":
                choiceCount = 4;
                break;
            case "Difficile":
                choiceCount = 5;
                break;
        }

        //on genère toutes les questions
        Question quest1 = new Question(choiceCount,"Quel est le pays représenté par ce drapeau ?", "France",ArrayHelper.getStringArrayList("Italie","Listenbourg","Allemagne","Croatie") ,R.drawable.drapeau_fr,0,"Géographie");
        Question quest2 = new Question(choiceCount,"Quel est le nom du premier president des Etas-unis d'Amérique?", "George Washington",ArrayHelper.getStringArrayList("Donald Trump","Thomas Jefferson","James K. Polk","Abraham Lincoln)") ,R.drawable.obama_q2,0,"Politique");
        Question quest3 = new Question(choiceCount,"Quelle est la longueur approximative totale des égouts de Paris?", "2600 km",ArrayHelper.getStringArrayList("560km","1200km","1800km","3700km") ,R.drawable.rat_q3,0,"Paris");
        Question quest4 = new Question(choiceCount,"Je roule a 130km:h pendant 1h puis a 110km/h durant 30 minutes. Quelle est la distance parcourue?", "185Km",ArrayHelper.getStringArrayList("190km","195km","200km","220km") ,0,R.raw.voiture_q4,"Calcul mental");
        Question quest5 = new Question(choiceCount,"Lorsqu’un pancake tombe dans la neige avant le 31 décembre, on dit qu’il est :", "Une Kipa surgelée ",ArrayHelper.getStringArrayList("Tombé dans la neige avant le 31 décembre ","Un frizby comestible","On ne dit rien","La réponse D") ,R.drawable.gad_q5,0,"Humour");
        Question quest6 = new Question(choiceCount,"Quelle mer borde à la fois la Russie, la Turquie et la Roumanie ?", "C'est la mer Noire",ArrayHelper.getStringArrayList("C'est la mer d'Arafura","C'est la mer Morte","C'est la mère de quelqu'un d'autre !","C'est la mer de Banda") ,0,R.raw.mer_q6,"Humour");
        Question quest7 = new Question(choiceCount,"Combien y a t-il de 7 entre 0 et 100 ?", "20",ArrayHelper.getStringArrayList("007","Sept","10","12") ,R.drawable.sept,0,"Enigme");
        Question quest8 = new Question(choiceCount,"Quelle est la taille de la Tour Eiffel", "324 m",ArrayHelper.getStringArrayList("300 m","320 m","330 m","317 m") ,R.drawable.tour_effeil,0,"Paris");
        Question quest9 = new Question(choiceCount,"Lequel de ces aliments n'est pas une ville", "Goyave",ArrayHelper.getStringArrayList("Cassis","Tabasco","Grenade","Gruyere") ,R.drawable.burger,0,"Culture G");
        Question quest10 = new Question(choiceCount,"Ce chant apartient à quel oiseau ?", "Corneille",ArrayHelper.getStringArrayList("Pie","Mésange","Vautour","Pigeon") ,0,R.raw.corneille,"Animaux");








        //on créer une List dans laquelle on stoque les questions
        ArrayList<Question> questionsList = new ArrayList<>();
        questionsList.add(quest1);
        questionsList.add(quest2);
        questionsList.add(quest3);
        questionsList.add(quest4);
        questionsList.add(quest5);
        questionsList.add(quest6);
        questionsList.add(quest7);
        questionsList.add(quest8);
        questionsList.add(quest9);
        questionsList.add(quest10);

        //création d'un quiz
        Quiz quiz = new Quiz(questionsList,0,0);
        Collections.shuffle(quiz.getQuestionsList()); //mélange des questions

        return quiz;


    }
}
