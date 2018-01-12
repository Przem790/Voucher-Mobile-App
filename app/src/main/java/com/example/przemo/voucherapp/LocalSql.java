package com.example.przemo.voucherapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.przemo.voucherapp.Models.VoucherJson;

import java.util.ArrayList;

/**
 * Created by PrzemO on 13.11.2017.
 */

public class LocalSql extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "VoucherApp.db";

    private java.lang.String GenerateTable = "CREATE TABLE " + MyPersonalData.TABLE_NAME + " (" +
    MyPersonalData.COLUMN_IMIE + " TEXT," +
    MyPersonalData.COLUMN_EMAIL + " TEXT," +
    MyPersonalData.COLUMN_WIEK + " INTEGER," +
    MyPersonalData.COLUMN_KRAJ + " TEXT," +
    MyPersonalData.COLUMN_IFFIRST + " INTEGER)";

    private java.lang.String GenerateVOUCHERSTable = "CREATE TABLE " + Vouchers.TABLE_NAME + " (" +
            Vouchers.COLUMN_KOD + " TEXT," +
            Vouchers.COLUMN_STARTDATE + " TEXT," +
            Vouchers.COLUMN_ENDDATE + " TEXT," +
            Vouchers.COLUMN_DISCOUNT_TYPE + " TEXT," +
            Vouchers.COLUMN_DISCOUNT_AMOUNT + " TEXT," +
            Vouchers.COLUMN_VOUCHER_DESC + " TEXT)";

    private SQLiteDatabase MyDataBase;

    public LocalSql(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    public void DeleteVoucher(String code){
        MyDataBase = this.getWritableDatabase();
        String sql = "DELETE FROM "+ Vouchers.TABLE_NAME+ " WHERE "+Vouchers.COLUMN_KOD+" = '"+code+"'";
        MyDataBase.execSQL(sql);
    }

    public ArrayList<VoucherJson> GetVouchers(){
        MyDataBase = this.getReadableDatabase();
        String[] projection = {
                Vouchers.COLUMN_KOD,
                Vouchers.COLUMN_STARTDATE,
                Vouchers.COLUMN_ENDDATE,
                Vouchers.COLUMN_DISCOUNT_TYPE,
                Vouchers.COLUMN_DISCOUNT_AMOUNT,
                Vouchers.COLUMN_VOUCHER_DESC
        };
        Cursor cursor = MyDataBase.query(Vouchers.TABLE_NAME, projection, null, null, null, null, null);
    ArrayList<VoucherJson> myarr = new ArrayList<>();
        while(cursor.moveToNext()) {

            String KOD = cursor.getString(cursor.getColumnIndexOrThrow(Vouchers.COLUMN_KOD));
            String SDate = cursor.getString(cursor.getColumnIndexOrThrow(Vouchers.COLUMN_STARTDATE));
            String EDate = cursor.getString(cursor.getColumnIndexOrThrow(Vouchers.COLUMN_ENDDATE));
            String Type = cursor.getString(cursor.getColumnIndexOrThrow(Vouchers.COLUMN_DISCOUNT_TYPE));
            String Amount = cursor.getString(cursor.getColumnIndexOrThrow(Vouchers.COLUMN_DISCOUNT_AMOUNT));
            String Desc = cursor.getString(cursor.getColumnIndexOrThrow(Vouchers.COLUMN_VOUCHER_DESC));
            myarr.add(new VoucherJson(KOD,SDate,EDate,Type,Amount,Desc,null));
        }

        cursor.close();
        return myarr;

    }

    public void addVoucher(VoucherJson vj){

        MyDataBase = this.getWritableDatabase();
        String SQL ="INSERT INTO " + Vouchers.TABLE_NAME + "(" +
                Vouchers.COLUMN_KOD + "," +
                Vouchers.COLUMN_STARTDATE + "," +
                Vouchers.COLUMN_ENDDATE + " ," +
                Vouchers.COLUMN_DISCOUNT_TYPE + " ," +
                Vouchers.COLUMN_DISCOUNT_AMOUNT + " ," +
                Vouchers.COLUMN_VOUCHER_DESC + ") VALUES('"+vj.getVoucherCode()+"','"
                +vj.getStartDate()+"','"+vj.getEndDate()+"','"+vj.getDiscountType()
                +"','"+vj.getDiscountAmount()+"','"+vj.getVoucherDescription()+"')";
        MyDataBase.execSQL(SQL);
    }

    public infocontainer GetWholeData(){

        MyDataBase = this.getReadableDatabase();
        String[] projection = {
                MyPersonalData.COLUMN_IMIE,
                MyPersonalData.COLUMN_EMAIL,
                MyPersonalData.COLUMN_KRAJ,
                MyPersonalData.COLUMN_WIEK,
                MyPersonalData.COLUMN_IFFIRST
        };
        Cursor cursor = MyDataBase.query(MyPersonalData.TABLE_NAME, projection, null, null, null, null, null);

        cursor.moveToNext();

            String Imie=cursor.getString(cursor.getColumnIndexOrThrow(MyPersonalData.COLUMN_IMIE));
            String Email=cursor.getString(cursor.getColumnIndexOrThrow(MyPersonalData.COLUMN_EMAIL));
            String Kraj=cursor.getString(cursor.getColumnIndexOrThrow(MyPersonalData.COLUMN_KRAJ));
            int wiek=cursor.getInt(cursor.getColumnIndexOrThrow(MyPersonalData.COLUMN_WIEK));
            int Iff=cursor.getInt(cursor.getColumnIndexOrThrow(MyPersonalData.COLUMN_IFFIRST));


        cursor.close();
        return new infocontainer(Imie,Email,Kraj,wiek,Iff);

    }

    public String insertData(String a, String b, int w, String c, int e){

        //MyDataBase = this.getWritableDatabase();
    String SQL ="INSERT INTO " + MyPersonalData.TABLE_NAME + "(" +
            MyPersonalData.COLUMN_IMIE + "," +
            MyPersonalData.COLUMN_EMAIL + "," +
            MyPersonalData.COLUMN_WIEK + " ," +
            MyPersonalData.COLUMN_KRAJ + " ," +
            MyPersonalData.COLUMN_IFFIRST + ") VALUES('"+a+"','"+b+"','"+w+"','"+c+"','"+e+"')";
        return SQL;
        //MyDataBase.execSQL(SQL);
}

    public void updateData(String a, String b, int w, String c){
        MyDataBase = this.getWritableDatabase();
        String SQL1,SQL2,SQL3,SQL4;
        SQL1= "UPDATE " + MyPersonalData.TABLE_NAME + " SET " +
                MyPersonalData.COLUMN_IMIE + " = '"+a+"'";
        SQL2= "UPDATE " + MyPersonalData.TABLE_NAME + " SET " +
                MyPersonalData.COLUMN_EMAIL + " = '"+b+"'";
        SQL3= "UPDATE " + MyPersonalData.TABLE_NAME + " SET " +
                MyPersonalData.COLUMN_WIEK + " = '"+w+"'";
        SQL4= "UPDATE " + MyPersonalData.TABLE_NAME + " SET " +
                MyPersonalData.COLUMN_KRAJ + " = '"+c+"'";


        MyDataBase.execSQL(SQL1);
        MyDataBase.execSQL(SQL2);
        MyDataBase.execSQL(SQL3);
        MyDataBase.execSQL(SQL4);
    }
    public void notfirstrun(){
        MyDataBase = this.getWritableDatabase();
        String SQL= "UPDATE " + MyPersonalData.TABLE_NAME + " SET " +
                MyPersonalData.COLUMN_IFFIRST + "= '2'";
        MyDataBase.execSQL(SQL);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(GenerateTable);
        db.execSQL(GenerateVOUCHERSTable);
        db.execSQL(insertData("dummy","dummy",0,"dummy",0));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static class MyPersonalData implements BaseColumns {
        public static final String TABLE_NAME = "Dane";
        public static final String COLUMN_IMIE = "Imie";
        public static final String COLUMN_EMAIL = "Email";
        public static final String COLUMN_WIEK = "Wiek";
        public static final String COLUMN_KRAJ = "Kraj";
        public static final String COLUMN_IFFIRST="IfFirst";
    }

    public static class Vouchers implements BaseColumns {
        public static final String TABLE_NAME = "Vouchery";
        public static final String COLUMN_KOD = "Kod";
        public static final String COLUMN_STARTDATE = "SDate";
        public static final String COLUMN_ENDDATE = "EDate";
        public static final String COLUMN_DISCOUNT_TYPE = "DTYPE";
        public static final String COLUMN_DISCOUNT_AMOUNT="Damount";
        public static final String COLUMN_VOUCHER_DESC="Desc";
    }



    public void delete(Context context){
        context.deleteDatabase(DATABASE_NAME);
    }

}
