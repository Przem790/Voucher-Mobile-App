package com.example.przemo.voucherapp.ListViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.przemo.voucherapp.ListViewInterface;
import com.example.przemo.voucherapp.Models.Company;
import com.example.przemo.voucherapp.Models.Survey_List;
import com.example.przemo.voucherapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PrzemO on 04.12.2017.
 */

public class SurveyListViewAdapter extends ArrayAdapter<Survey_List> {

    private LayoutInflater inflater;
    private List<Survey_List> items;
    private ListViewInterface listener;
    private String companyId;

    public SurveyListViewAdapter(Context context, int my_custom_lv_element_survey, List<Survey_List> survey_lists, ListViewInterface listener,String CompanyID) {
    super(context,my_custom_lv_element_survey,survey_lists);
        this.items=survey_lists;
        inflater=LayoutInflater.from(context);
        this.listener=listener;
        companyId=CompanyID;
    }

    @Override
    public View getView(final int index, View row, ViewGroup parent){
        if(row==null){
            row=inflater.inflate(R.layout.my_custom_lv_element_survey,parent,false);
        }
        TextView tv1=(TextView)row.findViewById(R.id.SurvName);
        TextView tv2=(TextView)row.findViewById(R.id.discount);
        TextView tv3=(TextView)row.findViewById(R.id.VoucherDescr);

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnSurveySelect(items.get(index).getId(),companyId);
            }
        });
        tv1.setText(items.get(index).getSurveyName());
        String str = "Rodzaj promocji: "+String.valueOf(items.get(index).getDiscountAmmount());
        if(items.get(index).getDiscountType().equals("PERCENTEGE"))
            str+="%";
        else {
            str+="$";
        }
        tv2.setText(str);
        tv3.setText("Opis: "+items.get(index).getVoucherDescription());
        return row;


    }

}
