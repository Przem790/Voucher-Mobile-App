package com.example.przemo.voucherapp;

import com.example.przemo.voucherapp.ListViewAdapters.CompaniesListViewAdapter;
import com.example.przemo.voucherapp.ListViewAdapters.SurveyListViewAdapter;
import com.example.przemo.voucherapp.Models.Company;

import java.util.ArrayList;

/**
 * Interfejst pozwalający na komunikacje aktywnosci z listView
 */

public interface ListViewInterface {
    ArrayList<Company> MyObjects = null;

    ArrayList<Company> getMyObjects();
    void setAdapter(CompaniesListViewAdapter MyAdapter);

    void setConnection(ApiConnection myConnection);
    CompaniesListViewAdapter getAdapter();

    ApiConnection getConnection();

    void setMyObjects(ArrayList<Company> allCompanys);

    void OnCompanySelect(int id);

    void OnSurveySelect(int id, String companyId);

    void setSurveyAdapter(SurveyListViewAdapter myAdapter);

    SurveyListViewAdapter getSurverAdapter();

    void DeleteVoucher(String code);
}
