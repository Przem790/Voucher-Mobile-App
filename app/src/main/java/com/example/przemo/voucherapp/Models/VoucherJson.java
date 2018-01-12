package com.example.przemo.voucherapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by PrzemO on 05.12.2017.
 */

public class VoucherJson {
    @SerializedName("voucherCode")
    @Expose
    String voucherCode;
    @SerializedName("startDate")
    @Expose
    String startDate;
    @SerializedName("endDate")
    @Expose
    String endDate;
    @SerializedName("discountType")
    @Expose
    String discountType;
    @SerializedName("discountAmount")
    @Expose
    String discountAmount;
    @SerializedName("voucherDescription")
    @Expose
    String voucherDescription;
    @SerializedName("errors")
    @Expose
    HashMap<Integer,String> errors;

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getVoucherDescription() {
        return voucherDescription;
    }

    public void setVoucherDescription(String voucherDescription) {
        this.voucherDescription = voucherDescription;
    }

    public HashMap<Integer, String> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<Integer, String> errors) {
        this.errors = errors;
    }

    public VoucherJson(String voucherCode, String startDate, String endDate, String discountType, String discountAmount, String voucherDescription, HashMap<Integer, String> errors) {

        this.voucherCode = voucherCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountType = discountType;
        this.discountAmount = discountAmount;
        this.voucherDescription = voucherDescription;
        this.errors = errors;
    }
}
