package com.example.przemo.voucherapp.Models;

import android.view.View;
import android.widget.CheckBox;

import com.example.przemo.voucherapp.R;

/**
 * Created by PrzemO on 05.12.2017.
 */

public class Answers {
    public final String MULTIPLE_CHOICE="MULTIPLE_CHOICE";
    public final String SINGLE_CHOICE="SINGLE_CHOICE";
    public final String RANGED="RANGED";
    public final String TEXT="OPEN";
    public String type;
    boolean [] checkboxes;
    public String value;

    public String getMULTIPLE_CHOICE() {
        return MULTIPLE_CHOICE;
    }

    public String getSINGLE_CHOICE() {
        return SINGLE_CHOICE;
    }

    public String getRANGED() {
        return RANGED;
    }

    public String getTEXT() {
        return TEXT;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean[] getCheckboxes() {
        return checkboxes;
    }

    public void setCheckboxes(boolean[] checkboxes) {
        this.checkboxes = checkboxes;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCheckBoxSingleSelected(){
        if(checkboxes[0])
            return "A";
        else if(checkboxes[1])
            return "B";
        else if(checkboxes[2])
            return "C";
        else if(checkboxes[3])
            return "D";
        else return null;
    }

    public String getCheckBoxMultipleSelected() {
        String str = null;
        if(checkboxes[0]) {
            if (str == null)
                str = "A";
            else {
                str += ",A";
            }
        }
        if(checkboxes[1]) {
            if (str == null)
                str = "B";
            else {
                str += ",B";
            }
        }
        if(checkboxes[2]) {
            if (str == null)
                str = "C";
            else {
                str += ",C";
            }
        }
        if(checkboxes[3]) {
            if (str == null)
                str = "D";
            else {
                str += ",D";
            }
        }
        return str;
    }


    public void changeboxesvalues(boolean a,boolean b,boolean c,boolean d){
        this.checkboxes[0]=a;
        this.checkboxes[1]=b;
        this.checkboxes[2]=c;
        this.checkboxes[3]=d;
    }

    public Answers(String type, boolean[] checkboxes, String value) {

        this.type = type;
        this.checkboxes = checkboxes;
        this.value = value;
    }
}

