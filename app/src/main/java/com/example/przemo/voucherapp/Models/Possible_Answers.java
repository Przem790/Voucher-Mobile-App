package com.example.przemo.voucherapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by PrzemO on 04.12.2017.
 */

public class Possible_Answers {

    @SerializedName("possibleAnswerA")
    @Expose
    private String possibleAnswerA;

    @SerializedName("possibleAnswerB")
    @Expose
    private String possibleAnswerB;

    @SerializedName("possibleAnswerC")
    @Expose
    private String possibleAnswerC;

    public Possible_Answers(String possibleAnswerA, String possibleAnswerB, String possibleAnswerC, String possibleAnswerD) {
        this.possibleAnswerA = possibleAnswerA;
        this.possibleAnswerB = possibleAnswerB;
        this.possibleAnswerC = possibleAnswerC;
        this.possibleAnswerD = possibleAnswerD;
    }

    public String getPossibleAnswerA() {

        return possibleAnswerA;
    }

    public void setPossibleAnswerA(String possibleAnswerA) {
        this.possibleAnswerA = possibleAnswerA;
    }

    public String getPossibleAnswerB() {
        return possibleAnswerB;
    }

    public void setPossibleAnswerB(String possibleAnswerB) {
        this.possibleAnswerB = possibleAnswerB;
    }

    public String getPossibleAnswerC() {
        return possibleAnswerC;
    }

    public void setPossibleAnswerC(String possibleAnswerC) {
        this.possibleAnswerC = possibleAnswerC;
    }

    public String getPossibleAnswerD() {
        return possibleAnswerD;
    }

    public void setPossibleAnswerD(String possibleAnswerD) {
        this.possibleAnswerD = possibleAnswerD;
    }

    @SerializedName("possibleAnswerD")
    @Expose
    private String possibleAnswerD;


}
