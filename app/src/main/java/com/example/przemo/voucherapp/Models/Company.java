package com.example.przemo.voucherapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by PrzemO on 14.11.2017.
 */
public class Company {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("companyName")
    @Expose
    private String Company_name;
    @SerializedName("addressDetails")
    @Expose
    private String AdressDetails;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("postalCode")
    @Expose
    private String postal_code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany_name() {
        return Company_name;
    }

    public void setCompany_name(String company_name) {
        Company_name = company_name;
    }

    public String getAdressDetails() {
        return AdressDetails;
    }

    public void setAdressDetails(String adressDetails) {
        AdressDetails = adressDetails;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public Company(int id, String company_name, String adressDetails, String city, String postal_code) {
        this.id = id;
        Company_name = company_name;
        AdressDetails = adressDetails;
        this.city = city;
        this.postal_code = postal_code;
    }
}
