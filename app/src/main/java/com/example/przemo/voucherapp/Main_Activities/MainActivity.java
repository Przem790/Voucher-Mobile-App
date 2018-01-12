package com.example.przemo.voucherapp.Main_Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.example.przemo.voucherapp.LocalSql;
import com.example.przemo.voucherapp.R;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * Created by PrzemO on 13.11.2017.
 */

public class MainActivity extends AppCompatActivity {
    LocalSql LSL;
    String e_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceProvider.registerDefaultIconSets();
        LSL = new LocalSql(getApplicationContext());
        //LSL.delete(getApplicationContext());
        if(LSL.GetWholeData().iff==2){
            Intent intent = new Intent(MainActivity.this, AnActivity.class);
            intent.putExtra("GoToVouchers",false);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        setContentView(R.layout.second_main_activity);

        final EditText myEditText = (EditText) findViewById(R.id.ClickedEditTwojeImie);
        final EditText myEditText1 = (EditText) findViewById(R.id.ClickedEditTwojEmail);
        final EditText myEditText2 = (EditText) findViewById(R.id.ClickedEditTwojWiek);
        final EditText myEditText3 = (EditText) findViewById(R.id.ClickedEditTwojKraj);

        BootstrapButton myButton = (BootstrapButton) findViewById(R.id.AcceptFirstDataRun);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = String.valueOf(myEditText2.getText());
                e_mail = String.valueOf(myEditText1.getText());
                if(validateMyData(String.valueOf(myEditText.getText()),e_mail,str,String.valueOf(myEditText3.getText()))) {

                    LSL.updateData(String.valueOf(myEditText.getText()), e_mail
                            , Integer.parseInt(str), String.valueOf(myEditText3.getText()));
                    LSL.notfirstrun();
                    Intent intent = new Intent(MainActivity.this, AnActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("GoToVouchers", false);
                    startActivity(intent);
                }
            }
        });

        myEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                myEditText.setText("");
                myEditText.setTextColor(Color.BLACK);
                }
            }
        });

        myEditText1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                myEditText1.setText("");
                myEditText1.setTextColor(Color.BLACK);
                }
            }
        });
        myEditText2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                myEditText2.setText("");
                myEditText2.setTextColor(Color.BLACK);
                }
            }
        });
        myEditText3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                myEditText3.setText("");
                myEditText3.setTextColor(Color.BLACK);
                }
            }
        });
    }

    private boolean validateMyData(String s, String s1, String str, String s2) {
        if(s.length()<2){
            Toast.makeText(this,"Podaj poprawne imię",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!s1.matches(".+@.+\\..+")){
            if(!(s1.equals("")||s1.equals("np. Tomek@gmail.com"))){
            Toast.makeText(this,"Podaj poprawny Adres E-mail",Toast.LENGTH_SHORT).show();
            return false;
            }else {
                e_mail="";
            }
        }

        if(NumberUtils.isNumber(str)) {
            if (Integer.parseInt(str) > 100) {
                Toast.makeText(this, "Podaj poprawny wiek", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
                Toast.makeText(this, "Podaj poprawny wiek", Toast.LENGTH_SHORT).show();
                return false;
            }

        if(s2.length()>3||s2.length()<2){
            Toast.makeText(this, "Podaj poprawny Tag kraju", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }


}
