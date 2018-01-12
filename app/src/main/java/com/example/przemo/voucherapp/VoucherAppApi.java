package com.example.przemo.voucherapp;

import com.example.przemo.voucherapp.Models.Company;
import com.example.przemo.voucherapp.Models.Post;
import com.example.przemo.voucherapp.Models.Survey;
import com.example.przemo.voucherapp.Models.Survey_List;
import com.example.przemo.voucherapp.Models.VoucherJson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by PrzemO on 03.12.2017.
 */

public interface VoucherAppApi {

    @GET("companies/{compId}/survey/{survId}")
    Call<Survey> GetSurvey(@Path("compId") String Compid, @Path("survId") String survid);

    @GET("companies/{Id}")
    Call<List<Survey_List>> GetCompanySurveys(@Path("Id") String Id);

    @GET("companies")
    Call<List<Company>> GetCompanyList();

    @POST("companies/{compId}/survey/{survId}")
    Call<VoucherJson> PostSurvey(@Body Post post, @Path("compId") String Compid, @Path("survId") String survid);

    @POST("voucher/unblock")
    Call<Void> UnblockVoucher();


}
