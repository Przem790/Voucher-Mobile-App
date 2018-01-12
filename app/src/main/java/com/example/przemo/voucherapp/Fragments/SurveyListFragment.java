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
import com.example.przemo.voucherapp.ListViewAdapters.SurveyListViewAdapter;
import com.example.przemo.voucherapp.ListViewInterface;
import com.example.przemo.voucherapp.Models.Company;
import com.example.przemo.voucherapp.Models.Survey_List;
import com.example.przemo.voucherapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PrzemO on 04.12.2017.
 */

public class SurveyListFragment extends Fragment {
        private ListViewInterface listener;
        SurveyListViewAdapter MyAdapter;
        Activity myActivity;
        String CId;


    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle bundle){

        CId=String.valueOf(getArguments().getInt("Id"));
        myActivity=this.getActivity();
        final View view = inflater.inflate(R.layout.surveyfragment,vg,false);
        MyAdapter = new SurveyListViewAdapter(view.getContext(),R.layout.my_custom_lv_element_survey,new ArrayList<Survey_List>(),listener,CId);
        listener.setSurveyAdapter(MyAdapter);
        final ListView lv = (ListView) view.findViewById(R.id.surveyListView);
        lv.setAdapter(listener.getSurverAdapter());
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

                final Call<List<Survey_List>> clist = MyConnection.service.GetCompanySurveys(CId);

                myActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        clist.enqueue(new Callback<List<Survey_List>>() {
                            @Override
                            public void onResponse(Call<List<Survey_List>> call, Response<List<Survey_List>> response) {
                                List<Survey_List> AllSurveys = response.body();

                                listener.setSurveyAdapter(new SurveyListViewAdapter(view.getContext(),R.layout.my_custom_lv_element_survey,AllSurveys,listener,CId));
                                lv.setAdapter(listener.getSurverAdapter());
                                lv.invalidate();
                                if(AllSurveys.size()!=0){
                                        tv.setText("");
                                    }else
                                        tv.setText("Brak Ankiet Do Wyświetlenia");

                                br.setVisibility(View.INVISIBLE);
                                lv.setVisibility(View.VISIBLE);
                                tv.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onFailure(Call<List<Survey_List>> call, Throwable t) {
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
