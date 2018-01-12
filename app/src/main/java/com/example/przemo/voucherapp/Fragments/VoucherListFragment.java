package com.example.przemo.voucherapp.Fragments;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.przemo.voucherapp.DialogActivity;
import com.example.przemo.voucherapp.ListViewAdapters.VouchersListViewAdapter;
import com.example.przemo.voucherapp.ListViewInterface;
import com.example.przemo.voucherapp.LocalSql;
import com.example.przemo.voucherapp.Models.VoucherJson;
import com.example.przemo.voucherapp.R;

import java.util.ArrayList;

/**
 * Created by PrzemO on 05.12.2017.
 */

public class VoucherListFragment extends Fragment {
    private ListViewInterface listener;
    VouchersListViewAdapter MyAdapter;
    Activity myActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle bundle) {
        LocalSql LSL = new LocalSql(getActivity().getApplicationContext());
        ArrayList<VoucherJson> MyList = LSL.GetVouchers();
        myActivity = this.getActivity();
        final View view = inflater.inflate(R.layout.surveyfragment, vg, false);
        MyAdapter = new VouchersListViewAdapter(view.getContext(),R.layout.vouchers_listview_custom_element,MyList,listener,this);
        final ListView lv = (ListView) view.findViewById(R.id.surveyListView);
        lv.setAdapter(MyAdapter);
        lv.setClickable(true);
        lv.setFocusable(false);
        lv.setFocusableInTouchMode(false);
        final TextView tv = (TextView) view.findViewById(R.id.JustText);
        if(MyList.size()!=0){
            tv.setText("");
        }else
            tv.setText("Brak Voucherów Do Wyświetlenia");
        final ProgressBar br = (ProgressBar) view.findViewById(R.id.circle);
        br.setVisibility(View.INVISIBLE);
        return view;
    }

    public void onActivityCreated(Bundle savedState){
        super.onActivityCreated(savedState);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof Activity) {
            this.listener = (ListViewInterface) context;
        }
    }

    @Override
    public void onAttach(Activity context){
        super.onAttach(context);
        if(context instanceof Activity) {
            this.listener = (ListViewInterface) context;
        }
    }




}
