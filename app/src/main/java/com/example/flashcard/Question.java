package com.example.flashcard;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;

public class Question implements Parcelable {

    private String question;
    private String goodAnswer;
    private ArrayList<String> Answers;
    private int pictureId;
    private int soundId;
    private String theme;


    public Question(int choiceCount, String question, String goodAnswer, ArrayList<String> answers, int pictureId, int soundId, String theme) {
        this.question = question;
        this.goodAnswer = goodAnswer;
        Answers = answers;
        this.pictureId = pictureId;
        this.soundId = soundId;
        this.theme = theme;

        Collections.shuffle(Answers);
        Answers = new ArrayList<>(Answers.subList(0,choiceCount-1));
        Answers.add(goodAnswer);
        Collections.shuffle(Answers);
    }

    protected Question(Parcel in) {
        question = in.readString();
        goodAnswer = in.readString();
        Answers = in.createStringArrayList();
        pictureId = in.readInt();
        soundId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(goodAnswer);
        dest.writeStringList(Answers);
        dest.writeInt(pictureId);
        dest.writeInt(soundId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getQuestion() {
        return question;
    }

    public String getGoodAnswer() {
        return goodAnswer;
    }

    public ArrayList<String> getAnswers() {
        return Answers;
    }

    public int getPictureId() {
        return pictureId;
    }

    public int getSoundId() {
        return soundId;
    }

    public String getTheme() {
        return theme;
    }
}

