package com.example.przemo.voucherapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collection;

/**
 * Created by PrzemO on 04.12.2017.
 */

public class Survey {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("surveyName")
    @Expose
    private String surveyName;
    @SerializedName("questionsDto")
    @Expose
    private Collection<Question> questionsDto;

    public Survey(int id, String surveyName, Collection<Question> questionsDto) {
        this.id = id;
        this.surveyName = surveyName;
        this.questionsDto = questionsDto;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public Collection<Question> getQuestionsDto() {
        return questionsDto;
    }

    public void setQuestionsDto(Collection<Question> questionsDto) {
        this.questionsDto = questionsDto;
    }


}
