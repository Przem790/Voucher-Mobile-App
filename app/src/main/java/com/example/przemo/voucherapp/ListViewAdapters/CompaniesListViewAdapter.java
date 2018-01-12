package com.example.przemo.voucherapp.ListViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.przemo.voucherapp.ListViewInterface;
import com.example.przemo.voucherapp.Models.Company;
import com.example.przemo.voucherapp.R;

import java.util.ArrayList;

/**
 * Created by PrzemO on 14.11.2017.
 */

public class CompaniesListViewAdapter extends ArrayAdapter<Company> {
    private LayoutInflater inflater;
    private ArrayList<Company> items;
    private ListViewInterface listener;

    public CompaniesListViewAdapter(Context context, int resource, ArrayList<Company> items, ListViewInterface listener) {
        super(context, resource,items);
        this.items=items;
        inflater=LayoutInflater.from(context);
        this.listener=listener;
    }
    @Override
    public View getView(final int index, View row, ViewGroup parent){
        if(row==null){
            row=inflater.inflate(R.layout.my_custom_lv_element,parent,false);
        }
        TextView tv1=(TextView)row.findViewById(R.id.CompanyName);
        TextView tv2=(TextView)row.findViewById(R.id.City);
        TextView tv3=(TextView)row.findViewById(R.id.AdressDetails);

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnCompanySelect(items.get(index).getId());
            }
        });
        tv1.setText(items.get(index).getCompany_name());
        tv2.setText("Miasto: "+String.valueOf(items.get(index).getCity()));
        tv3.setText("Adress: "+items.get(index).getAdressDetails()+items.get(index).getPostal_code());
        return row;


    }



}
