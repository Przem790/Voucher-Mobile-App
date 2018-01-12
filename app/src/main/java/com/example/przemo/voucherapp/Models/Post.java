package com.example.przemo.voucherapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.TreeMap;

/**
 * Created by PrzemO on 05.12.2017.
 */

public class Post {
    @SerializedName("email")
    @Expose
    public String e_mail;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("age")
    @Expose
    public int age;
    @SerializedName("answersMap")
    @Expose
    public TreeMap<Integer,String> TreeMapAnswers;


    public Post(String e_mail, String country, int age, TreeMap<Integer, String> answers) {
        if(!e_mail.equals(""))
            this.e_mail = e_mail;
        this.country = country;
        this.age = age;
        TreeMapAnswers = answers;
    }

    public String getE_mail() {

        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public TreeMap<Integer, String> getAnswers() {
        return TreeMapAnswers;
    }

    public void setAnswers(TreeMap<Integer, String> answers) {
        TreeMapAnswers = answers;
    }




}
