package com.example.flashcard;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Quiz implements Parcelable {
    private ArrayList<Question> questionsList;
    private int indexQuestion;
    private int goodAnswerCount;

    public Quiz(ArrayList<Question> questionsList, int indexQuestion, int goodAnswerCount) {
        this.questionsList = questionsList;
        this.indexQuestion = indexQuestion;
        this.goodAnswerCount = goodAnswerCount;
    }


    protected Quiz(Parcel in) {
        questionsList = in.createTypedArrayList(Question.CREATOR);
        indexQuestion = in.readInt();
        goodAnswerCount = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(questionsList);
        dest.writeInt(indexQuestion);
        dest.writeInt(goodAnswerCount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Quiz> CREATOR = new Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel in) {
            return new Quiz(in);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };

    public ArrayList<Question> getQuestionsList() {
        return questionsList;
    }

    public int getIndexQuestion() {
        return indexQuestion;
    }

    public int getGoodAnswerCount(){
        return goodAnswerCount;
    }

    public void setIndexQuestion(int i){
        indexQuestion = i;
    }


    public void setGoodAnswerCount(int i){
        goodAnswerCount = i;
    }
}
