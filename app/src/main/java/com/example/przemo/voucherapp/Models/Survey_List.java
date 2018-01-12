package com.example.przemo.voucherapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by PrzemO on 04.12.2017.
 */

public class Survey_List {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("surveyName")
    @Expose
    private String surveyName;
    @SerializedName("discountType")
    @Expose
    private String discountType;
    @SerializedName("discountAmmount")
    @Expose
    private int discountAmmount;
    @SerializedName("voucherDescription")
    @Expose
    private String voucherDescription;

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

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public int getDiscountAmmount() {
        return discountAmmount;
    }

    public void setDiscountAmmount(int discountAmmount) {
        this.discountAmmount = discountAmmount;
    }

    public String getVoucherDescription() {
        return voucherDescription;
    }

    public void setVoucherDescription(String voucherDescription) {
        this.voucherDescription = voucherDescription;
    }

    public Survey_List(int id, String surveyName, String discountType, int discountAmmount, String voucherDescription) {

        this.id = id;
        this.surveyName = surveyName;
        this.discountType = discountType;
        this.discountAmmount = discountAmmount;
        this.voucherDescription = voucherDescription;
    }
}
