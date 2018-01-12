package com.example.przemo.voucherapp.ListViewAdapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalSeekbar;
import com.example.przemo.voucherapp.Models.Answers;
import com.example.przemo.voucherapp.Models.Question;
import com.example.przemo.voucherapp.R;

import java.util.ArrayList;

/**
 * Created by PrzemO on 05.12.2017.
 */

public class SurveyActivityAdapter extends BaseAdapter {
    ArrayList<Question> MyQuestions;
    public final String MULTIPLE_CHOICE="MULTIPLE_CHOICE";
    public final String SINGLE_CHOICE="SINGLE_CHOICE";
    public final String RANGED="RANGED";
    public final String TEXT="OPEN";
    LayoutInflater mInflater;
    public ArrayList<Answers> answers;



    public SurveyActivityAdapter(ArrayList<Question> myquestions, Context context) {
        MyQuestions=myquestions;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        answers=new ArrayList<>();
        for(int i=0;i<MyQuestions.size();i++) {
            String type = getQuestionType(i);
            switch (type) {
                case SINGLE_CHOICE:
                    boolean[] bl1 = {false, false, false, false};
                    answers.add(new Answers(SINGLE_CHOICE, bl1, null));
                    break;
                case MULTIPLE_CHOICE:
                    boolean[] bl2 = {false, false, false, false};
                    answers.add(new Answers(MULTIPLE_CHOICE, bl2, null));
                    break;
                case RANGED:
                    answers.add(new Answers(RANGED,null,"0"));
                    break;
                case TEXT:
                    answers.add(new Answers(TEXT,null,""));
                    break;
            }
        }
    }

    @Override
    public int getCount() {
        return MyQuestions.size();
    }

    @Override
    public Object getItem(int position) {
        return MyQuestions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public ArrayList<Question> getMyQuestions() {
        return MyQuestions;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        String type = getQuestionType(position);

        if(convertView==null){
            switch(type){
                case SINGLE_CHOICE:
                    convertView = mInflater.inflate(R.layout.single_multiple_choice,null);
                    TextView tv = (TextView) convertView.findViewById(R.id.QuestionTask);
                    tv.setText(getQuestionTask(position));
                    setCheckBoxesText(convertView,position,true);
                    break;
                case MULTIPLE_CHOICE:
                    convertView = mInflater.inflate(R.layout.single_multiple_choice,null);
                    TextView tv2 = (TextView) convertView.findViewById(R.id.QuestionTask);
                    tv2.setText(getQuestionTask(position));
                    setCheckBoxesText(convertView,position,false);
                    break;
                case RANGED:
                    convertView = mInflater.inflate(R.layout.ranged,null);
                    final TextView tvMin = (TextView) convertView.findViewById(R.id.textMin1);
                    TextView tv3 = (TextView) convertView.findViewById(R.id.QuestionTask);
                    tv3.setText(getQuestionTask(position));
                    CrystalSeekbar cs = (CrystalSeekbar)convertView.findViewById(R.id.rangeSeekbar);
                    cs.setMaxValue(10);
                    cs.setMinValue(0);
                    cs.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
                        @Override
                        public void valueChanged(Number minValue) {
                            tvMin.setText(String.valueOf(minValue));
                            answers.get(position).setValue(String.valueOf(minValue));
                        }
                    });
                    break;
                case TEXT:
                    convertView = mInflater.inflate(R.layout.text,null);
                    TextView tv4 = (TextView) convertView.findViewById(R.id.QuestionTask);
                    tv4.setText(getQuestionTask(position));
                    final EditText et = (EditText) convertView.findViewById(R.id.AnswerPlace);
                    et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            answers.get(position).setValue(String.valueOf(et.getText()));
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    break;

            }
        }

        return convertView;
    }

    private void setCheckBoxesText(View convertView, final int position , boolean ifsingle) {
        final CheckBox cb1 = (CheckBox) convertView.findViewById(R.id.checkbox_01);
        final CheckBox cb2 = (CheckBox) convertView.findViewById(R.id.checkbox_02);
        final CheckBox cb3 = (CheckBox) convertView.findViewById(R.id.checkbox_03);
        final CheckBox cb4= (CheckBox) convertView.findViewById(R.id.checkbox_04);

        cb1.setText(MyQuestions.get(position).getPossibleAnswers().getPossibleAnswerA());
        cb2.setText(MyQuestions.get(position).getPossibleAnswers().getPossibleAnswerB());
        cb3.setText(MyQuestions.get(position).getPossibleAnswers().getPossibleAnswerC());
        cb4.setText(MyQuestions.get(position).getPossibleAnswers().getPossibleAnswerD());

        if(ifsingle){
            cb1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cb1.isChecked())
                        cb1.setChecked(true);
                    else
                        cb1.setChecked(false);
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                    cb4.setChecked(false);
                    answers.get(position).changeboxesvalues(cb1.isChecked(),cb2.isChecked(),cb3.isChecked(),cb4.isChecked());
                }
            });
            cb2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cb2.isChecked())
                        cb2.setChecked(true);
                    else
                        cb2.setChecked(false);
                    cb1.setChecked(false);
                    cb3.setChecked(false);
                    cb4.setChecked(false);
                    answers.get(position).changeboxesvalues(cb1.isChecked(),cb2.isChecked(),cb3.isChecked(),cb4.isChecked());
                }
            });
            cb3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cb3.isChecked())
                        cb3.setChecked(true);
                    else
                        cb3.setChecked(false);
                    cb2.setChecked(false);
                    cb1.setChecked(false);
                    cb4.setChecked(false);
                    answers.get(position).changeboxesvalues(cb1.isChecked(),cb2.isChecked(),cb3.isChecked(),cb4.isChecked());
                }
            });
            cb4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cb4.isChecked())
                        cb4.setChecked(true);
                    else
                        cb4.setChecked(false);
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                    cb1.setChecked(false);
                    answers.get(position).changeboxesvalues(cb1.isChecked(),cb2.isChecked(),cb3.isChecked(),cb4.isChecked());
                }
            });
        }else {
            cb1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    answers.get(position).changeboxesvalues(cb1.isChecked(),cb2.isChecked(),cb3.isChecked(),cb4.isChecked());
                }
            });
            cb2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    answers.get(position).changeboxesvalues(cb1.isChecked(),cb2.isChecked(),cb3.isChecked(),cb4.isChecked());
                }
            });
            cb3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    answers.get(position).changeboxesvalues(cb1.isChecked(),cb2.isChecked(),cb3.isChecked(),cb4.isChecked());
                }
            });
            cb4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    answers.get(position).changeboxesvalues(cb1.isChecked(),cb2.isChecked(),cb3.isChecked(),cb4.isChecked());
                }
            });
        }

    }

    private String getQuestionTask(int position) {
        return MyQuestions.get(position).getQuestionBody();
    }


    public String getQuestionType(int position) {
        return MyQuestions.get(position).getQuestionType();

    }
}
