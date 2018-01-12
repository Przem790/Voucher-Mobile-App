package com.example.przemo.voucherapp.Main_Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalSeekbar;
import com.example.przemo.voucherapp.ApiConnection;
import com.example.przemo.voucherapp.ListViewAdapters.SurveyActivityAdapter;
import com.example.przemo.voucherapp.LocalSql;
import com.example.przemo.voucherapp.Models.Answers;
import com.example.przemo.voucherapp.Models.Post;
import com.example.przemo.voucherapp.Models.Question;
import com.example.przemo.voucherapp.Models.Survey;
import com.example.przemo.voucherapp.Models.VoucherJson;
import com.example.przemo.voucherapp.R;
import com.example.przemo.voucherapp.infocontainer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PrzemO on 04.12.2017.
 */

public class CompleteSurvey extends AppCompatActivity {
    ApiConnection myApiConnection;
    SurveyActivityAdapter MyAdapter;
    Survey survey;
    public final String MULTIPLE_CHOICE="MULTIPLE_CHOICE";
    public final String SINGLE_CHOICE="SINGLE_CHOICE";
    public final String RANGED="RANGED";
    public final String TEXT="OPEN";


    @Override
    protected void onCreate(Bundle savedInstanceState){
        TypefaceProvider.registerDefaultIconSets();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_activity);
        final ListView lv = (ListView) findViewById(R.id.Survey_Question_ListView);
        final TextView tv = (TextView) findViewById(R.id.Survey_Name);
        final ProgressBar br = (ProgressBar) findViewById(R.id.circle);
        final BootstrapButton btt = (BootstrapButton) findViewById(R.id.acceptsurveybutton);
        btt.setVisibility(View.INVISIBLE);
        br.setVisibility(View.VISIBLE);
        lv.setVisibility(View.INVISIBLE);
        tv.setVisibility(View.INVISIBLE);
        myApiConnection=new ApiConnection();
        Call<Survey> CSurvey = myApiConnection.service.GetSurvey(getIntent().getExtras().getString("companyId"),getIntent().getExtras().getString("surveyid"));
        CSurvey.enqueue(new Callback<Survey>() {
            @Override
            public void onResponse(Call<Survey> call, Response<Survey> response) {
                    survey = response.body();
                    createAdapterAndAdapt(survey);
                br.setVisibility(View.INVISIBLE);
                lv.setVisibility(View.VISIBLE);
                tv.setVisibility(View.VISIBLE);
                btt.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<Survey> call, Throwable t) {
                br.setVisibility(View.INVISIBLE);
                lv.setVisibility(View.VISIBLE);
                tv.setVisibility(View.VISIBLE);
                btt.setVisibility(View.VISIBLE);
            }
        });
    }

    private void createAdapterAndAdapt(Survey survey) {
        ArrayList<Question> myquestions = (ArrayList<Question>) survey.getQuestionsDto();
        MyAdapter = new SurveyActivityAdapter(myquestions,this);
        ListView lv = (ListView) findViewById(R.id.Survey_Question_ListView);
        lv.setAdapter(MyAdapter);
        TextView title = (TextView) findViewById(R.id.Survey_Name);
        title.setText(survey.getSurveyName());

    }

    public void onClickAccept(View view){

        ArrayList<Question> myarray = MyAdapter.getMyQuestions();

        final LocalSql LSL = new LocalSql(getApplicationContext());
        infocontainer iff = LSL.GetWholeData();


        final ListView lv = (ListView)findViewById(R.id.Survey_Question_ListView);
        Post to_send_json = new Post(iff.e_mail,iff.kraj,iff.wiek,new TreeMap<Integer, String>());
        for(int i=0;i< MyAdapter.answers.size();i++) {
            String type = MyAdapter.answers.get(i).getType();
            switch (type) {
                case SINGLE_CHOICE:
                    String checkboxsingle = MyAdapter.answers.get(i).getCheckBoxSingleSelected();
                    if(checkboxsingle!=null)
                        to_send_json.TreeMapAnswers.put(i,checkboxsingle);
                    else{
                        Toast.makeText(getApplicationContext(),"Nie podano odpowiedzi dla "+(i+1)+" pytania",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    break;
                case MULTIPLE_CHOICE:
                    String checkboxmultiple = MyAdapter.answers.get(i).getCheckBoxMultipleSelected();
                    if(checkboxmultiple!=null)
                        to_send_json.TreeMapAnswers.put(i,checkboxmultiple);
                    else{
                        Toast.makeText(getApplicationContext(),"Nie podano odpowiedzi dla "+(i+1)+" pytania",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    break;
                case RANGED:
                    to_send_json.TreeMapAnswers.put(i, MyAdapter.answers.get(i).getValue());
                    break;
                case TEXT:
                    if(!MyAdapter.answers.get(i).getValue().equals(""))
                        to_send_json.TreeMapAnswers.put(i,MyAdapter.answers.get(i).getValue());
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Nie podano odpowiedzi dla "+(i+1)+" pytania",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    break;
            }
        }
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        Call<VoucherJson> response = myApiConnection.service.PostSurvey(to_send_json,getIntent().getExtras().getString("companyId"),getIntent().getExtras().getString("surveyid"));
        final TextView tv = (TextView) findViewById(R.id.Survey_Name);
        final ProgressBar br = (ProgressBar) findViewById(R.id.circle);
        final Button btt = (Button) findViewById(R.id.acceptsurveybutton);
        br.setVisibility(View.VISIBLE);
        lv.setVisibility(View.INVISIBLE);
        tv.setVisibility(View.INVISIBLE);
        btt.setVisibility(View.INVISIBLE);

        response.enqueue(new Callback<VoucherJson>() {
            @Override
            public void onResponse(Call<VoucherJson> call, Response<VoucherJson> response) {
                VoucherJson vj = response.body();
                if(vj!=null) {
                    saveVoucherInDataBase(LSL,vj);
                    Toast.makeText(getApplicationContext(),"Otrzymałeś kupon!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplication(),AnActivity.class);
                    intent.putExtra("GoToVouchers",true);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    br.setVisibility(View.VISIBLE);
                    lv.setVisibility(View.INVISIBLE);
                    tv.setVisibility(View.INVISIBLE);
                    btt.setVisibility(View.INVISIBLE);
                    startActivity(intent);

                }else {
                    Toast.makeText(getApplicationContext(), "Błąd!", Toast.LENGTH_SHORT).show();
                    br.setVisibility(View.INVISIBLE);
                    lv.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                    btt.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<VoucherJson> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Bład podczas przesyłania ankiety",Toast.LENGTH_SHORT).show();
                br.setVisibility(View.INVISIBLE);
                lv.setVisibility(View.VISIBLE);
                tv.setVisibility(View.VISIBLE);
                btt.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void onDestroy(){
        myApiConnection.service.UnblockVoucher().enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

        super.onDestroy();


    }

    @Override
    public void onBackPressed() {


        myApiConnection.service.UnblockVoucher().enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });


        super.onBackPressed();

    }


    public void saveVoucherInDataBase(LocalSql LSL, VoucherJson vj){
        LSL.addVoucher(vj);
    }

}
