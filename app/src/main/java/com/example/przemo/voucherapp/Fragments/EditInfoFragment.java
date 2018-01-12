package com.example.przemo.voucherapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.example.przemo.voucherapp.LocalSql;
import com.example.przemo.voucherapp.R;
import com.example.przemo.voucherapp.infocontainer;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * Created by PrzemO on 13.11.2017.
 */

public class EditInfoFragment extends Fragment {
    String e_mail;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle bundle){

        View v = inflater.inflate(R.layout.edit_info_fragment,vg,false);
        final LocalSql LSL = new LocalSql(getActivity().getApplicationContext());
        final EditText myEditText = (EditText) v.findViewById(R.id.ClickedEditTwojeImie);
        final EditText myEditText1 = (EditText) v.findViewById(R.id.ClickedEditTwojEmail);
        final EditText myEditText2 = (EditText) v.findViewById(R.id.ClickedEditTwojWiek);
        final EditText myEditText3 = (EditText) v.findViewById(R.id.ClickedEditTwojKraj);
        infocontainer iff = LSL.GetWholeData();
        myEditText.setText(iff.imie);
        myEditText1.setText(iff.e_mail);
        String ww=""+iff.wiek;
        myEditText2.setText(ww);
        myEditText3.setText(iff.kraj);

        BootstrapButton myButton = (BootstrapButton) v.findViewById(R.id.AcceptFirstDataRun);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = String.valueOf(myEditText2.getText());
                e_mail = String.valueOf(myEditText1.getText());
                if(validateMyData(String.valueOf(myEditText.getText()),e_mail,str,String.valueOf(myEditText3.getText()))) {
                    LSL.updateData(String.valueOf(myEditText.getText()), e_mail
                            , Integer.parseInt(str), String.valueOf(myEditText3.getText()));
                    LSL.notfirstrun();

                    CompanyListFragment mfr = new CompanyListFragment();
                    FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                    fc.replaceFramgnet(mfr);
                }
            }
        });
        return v;
    }

    public void onActivityCreated(Bundle savedState){
        super.onActivityCreated(savedState);
    }

    private boolean validateMyData(String s, String s1, String str, String s2) {
        if(s.length()<2){
            Toast.makeText(getContext(),"Podaj poprawne imię",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!s1.matches(".+@.+\\..+")){
            if(!(s1.equals("")||s1.equals("np. Tomek@gmail.com"))){
                Toast.makeText(getContext(),"Podaj poprawny Adres E-mail",Toast.LENGTH_SHORT).show();
                return false;
            }else{
                e_mail="";
            }
        }

        if(NumberUtils.isNumber(str)) {
            if (Integer.parseInt(str) > 100) {
                Toast.makeText(getContext(), "Podaj poprawny wiek", Toast.LENGTH_SHORT).show();
                return false;
            }} else {
                Toast.makeText(getContext(), "Podaj poprawny wiek", Toast.LENGTH_SHORT).show();
                return false;
            }

        if(s2.length()>3||s2.length()<2){
            Toast.makeText(getContext(), "Podaj poprawny Tag kraju", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }

}