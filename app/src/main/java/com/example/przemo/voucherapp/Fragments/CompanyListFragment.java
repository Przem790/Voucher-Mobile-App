package com.example.przemo.voucherapp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.przemo.voucherapp.ApiConnection;
import com.example.przemo.voucherapp.ListViewAdapters.CompaniesListViewAdapter;
import com.example.przemo.voucherapp.ListViewInterface;
import com.example.przemo.voucherapp.Models.Company;
import com.example.przemo.voucherapp.R;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by PrzemO on 13.11.2017.
 */

public class CompanyListFragment extends Fragment {

    private ListViewInterface listener;
    CompaniesListViewAdapter MyAdapter;
    Activity myActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle bundle){

        myActivity=this.getActivity();
        final View view = inflater.inflate(R.layout.surveyfragment,vg,false);
        MyAdapter = new CompaniesListViewAdapter(view.getContext(),R.layout.my_custom_lv_element,listener.getMyObjects(),listener);
        listener.setAdapter(MyAdapter);
        final ListView lv = (ListView) view.findViewById(R.id.surveyListView);
        lv.setAdapter(listener.getAdapter());
        lv.setClickable(true);
        lv.setFocusable(false);
        lv.setFocusableInTouchMode(false);
        final TextView tv = (TextView) view.findViewById(R.id.JustText);
        final ProgressBar br = (ProgressBar) view.findViewById(R.id.circle);
        br.setVisibility(View.VISIBLE);
        lv.setVisibility(View.INVISIBLE);
        tv.setVisibility(View.INVISIBLE);

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                ApiConnection MyConnection;
                if(listener.getConnection()==null) {
                    MyConnection = new ApiConnection();
                    listener.setConnection(MyConnection);
                }else MyConnection=listener.getConnection();

                final Call<List<Company>> clist = MyConnection.service.GetCompanyList();

                myActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        clist.enqueue(new Callback<List<Company>>() {
                            @Override
                            public void onResponse(Call<List<Company>> call, Response<List<Company>> response) {
                                List<Company> AllCompanys = response.body();
                                    listener.setAdapter(new CompaniesListViewAdapter(view.getContext(), R.layout.my_custom_lv_element, (ArrayList<Company>) AllCompanys, listener));
                                    listener.setMyObjects((ArrayList<Company>) AllCompanys);
                                    lv.setAdapter(listener.getAdapter());
                                    lv.invalidate();
                                    if(listener.getMyObjects().size()!=0){
                                        tv.setText("");
                                    }else
                                        tv.setText("Brak Firm Do Wyświetlenia");
                                    br.setVisibility(View.INVISIBLE);
                                    lv.setVisibility(View.VISIBLE);
                                    tv.setVisibility(View.VISIBLE);

                            }

                            @Override
                            public void onFailure(Call<List<Company>> call, Throwable t) {
                                System.out.println(t.getLocalizedMessage());
                                br.setVisibility(View.INVISIBLE);
                                lv.setVisibility(View.VISIBLE);
                                tv.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                });

            }
        });
        thread.run();
        if(listener.getMyObjects().size()!=0){
            tv.setText("");
        }else
            tv.setText("Brak Firm Do Wyświetlenia");
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
