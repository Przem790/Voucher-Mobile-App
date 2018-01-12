package com.example.przemo.voucherapp.ListViewAdapters;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.przemo.voucherapp.DialogActivity;
import com.example.przemo.voucherapp.Fragments.VoucherListFragment;
import com.example.przemo.voucherapp.ListViewInterface;
import com.example.przemo.voucherapp.Models.Company;
import com.example.przemo.voucherapp.Models.VoucherJson;
import com.example.przemo.voucherapp.R;

import java.util.ArrayList;

/**
 * Created by PrzemO on 05.12.2017.
 */

public class VouchersListViewAdapter extends ArrayAdapter<VoucherJson> {
    private LayoutInflater inflater;
    public ArrayList<VoucherJson> items;
    private ListViewInterface listener;
    VoucherListFragment vlf;



    public VouchersListViewAdapter(Context context, int vouchers_listview_custom_element, ArrayList<VoucherJson> myList, ListViewInterface listener, VoucherListFragment voucherListFragment) {
        super(context,vouchers_listview_custom_element,myList);
        this.items=myList;
        inflater=LayoutInflater.from(context);
        this.listener=listener;
        vlf=voucherListFragment;
    }

    @Override
    public View getView(final int index, View row, ViewGroup parent){
        if(row==null){
            row=inflater.inflate(R.layout.vouchers_listview_custom_element,parent,false);
        }
        TextView tv1=(TextView)row.findViewById(R.id.Code);
        TextView tv2=(TextView)row.findViewById(R.id.StartDate);
        TextView tv3=(TextView)row.findViewById(R.id.EndDate);
        TextView tv4=(TextView)row.findViewById(R.id.Value);
        TextView tv5=(TextView)row.findViewById(R.id.Description);

        row.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DialogActivity da = new DialogActivity();
                android.support.v4.app.FragmentManager fm = vlf.getActivity().getSupportFragmentManager();
                da.setCode(items.get(index).getVoucherCode());
                da.show(fm,"aaa");
                return false;
            }
        });
        tv1.setText(items.get(index).getVoucherCode());
        tv2.setText("Date start: "+String.valueOf(items.get(index).getStartDate()));
        tv3.setText("Date end: "+items.get(index).getEndDate());
        String str = "Value: "+String.valueOf(items.get(index).getDiscountAmount());
        if(items.get(index).getDiscountType().equals("PERCENTEGE"))
            str+="%";
        else {
            str+="$";
        }
        tv4.setText(str);
        tv5.setText("Description: "+items.get(index).getVoucherDescription());
        return row;


    }
}
