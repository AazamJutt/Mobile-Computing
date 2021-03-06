package com.example.makharijulhuruf;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Report implements Parcelable {
    ArrayList<String> question;
    ArrayList<String> answers;
    ArrayList<String> chosen;
    int total;
    int correct;
    int inCorrect;

    public Report() {
        question = new ArrayList<>();
        answers = new ArrayList<>();
        chosen = new ArrayList<>();
        total = 0;
        correct = 0;
        inCorrect = 0;
    }

    protected Report(Parcel in) {
        question = in.createStringArrayList();
        answers = in.createStringArrayList();
        chosen = in.createStringArrayList();
        total = in.readInt();
        correct = in.readInt();
        inCorrect = in.readInt();
    }

    public static final Creator<Report> CREATOR = new Creator<Report>() {
        @Override
        public Report createFromParcel(Parcel in) {
            return new Report(in);
        }

        @Override
        public Report[] newArray(int size) {
            return new Report[size];
        }
    };

    public void addRecord(String ques, String ans, String choice, boolean result){
        question.add(ques);
        answers.add(ans);
        chosen.add(choice);
        if(result){
            correct++;
        }
        else
            inCorrect++;
        total++;
    }

    public ArrayList<String> getQuestion() {
        return question;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public ArrayList<String> getChosen() {
        return chosen;
    }

    public int getTotal() {
        return total;
    }

    public int getCorrect() {
        return correct;
    }

    public int getInCorrect() {
        return inCorrect;
    }

    @NonNull
    @Override
    public String toString() {
        return question.toString()+answers.toString()+total;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(question);
        dest.writeStringList(answers);
        dest.writeStringList(chosen);
        dest.writeInt(total);
        dest.writeInt(correct);
        dest.writeInt(inCorrect);
    }
}
