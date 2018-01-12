package com.example.przemo.voucherapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by PrzemO on 04.12.2017.
 */

public class Question {
    public Question(String questionBody, String questionType, Possible_Answers possibleAnswers, int id) {
        this.questionBody = questionBody;
        this.questionType = questionType;
        this.possibleAnswers = possibleAnswers;
        this.id = id;
    }

    public String getQuestionBody() {
        return questionBody;
    }

    public void setQuestionBody(String questionBody) {
        this.questionBody = questionBody;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public Possible_Answers getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(Possible_Answers possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @SerializedName("questionBody")
    @Expose
    private String questionBody;

    @SerializedName("questionType")
    @Expose
    private String questionType;

    @SerializedName("possibleAnswers")
    @Expose
    private Possible_Answers possibleAnswers;

    @SerializedName("id")
    @Expose
    private int id;

}
