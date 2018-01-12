package com.example.przemo.voucherapp.Main_Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.example.przemo.voucherapp.ApiConnection;
import com.example.przemo.voucherapp.DialogActivity;
import com.example.przemo.voucherapp.Fragments.EditInfoFragment;
import com.example.przemo.voucherapp.Fragments.FragmentChangeListener;
import com.example.przemo.voucherapp.Fragments.CompanyListFragment;
import com.example.przemo.voucherapp.Fragments.SurveyListFragment;
import com.example.przemo.voucherapp.Fragments.VoucherListFragment;
import com.example.przemo.voucherapp.ListViewAdapters.CompaniesListViewAdapter;
import com.example.przemo.voucherapp.ListViewAdapters.SurveyListViewAdapter;
import com.example.przemo.voucherapp.ListViewInterface;
import com.example.przemo.voucherapp.LocalSql;
import com.example.przemo.voucherapp.Models.Company;
import com.example.przemo.voucherapp.R;
import java.util.ArrayList;

public class AnActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,FragmentChangeListener,ListViewInterface,DialogActivity.NoticeDialogListener {
    LocalSql LSL;
    ArrayList<Company> AllCompanys;
    ApiConnection myConnection;
    CompaniesListViewAdapter MyAdapter;
    SurveyListViewAdapter SurveyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceProvider.registerDefaultIconSets();
        AllCompanys=new ArrayList<>();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LSL=new LocalSql(this.getApplicationContext());
        boolean whatToDo=getIntent().getBooleanExtra("GoToVouchers",false);
        if(!whatToDo) {
            CompanyListFragment firstFragment = new CompanyListFragment();
            replaceFramgnet(firstFragment);
            Toast.makeText(this, "Witaj "+LSL.GetWholeData().imie+" w VoucherApp",
                    Toast.LENGTH_LONG).show();
        }else {
            VoucherListFragment newfragment = new VoucherListFragment();
            replaceFramgnet(newfragment);
        }





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Surveys) {
            if (findViewById(R.id.MyFragment) != null) {
                CompanyListFragment firstFragment = new CompanyListFragment();
                replaceFramgnet(firstFragment);
            }
        } else if (id == R.id.nav_changeinfo) {
            if (findViewById(R.id.MyFragment) != null) {
                EditInfoFragment firstFragment = new EditInfoFragment();
                replaceFramgnet(firstFragment);
            }

        } else if (id == R.id.nav_exit) {
            LSL.delete(getApplicationContext());
            System.exit(0);
        } else if (id == R.id.nav_vouchers) {
            VoucherListFragment newfragment = new VoucherListFragment();
            replaceFramgnet(newfragment);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void replaceFramgnet(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.MyFragment, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();
    }

    @Override
    public ArrayList<Company> getMyObjects() {
        return AllCompanys;
    }

    @Override
    public void setAdapter(CompaniesListViewAdapter MyAdapter) {
        this.MyAdapter=MyAdapter;
    }

    @Override
    public void setConnection(ApiConnection myConnection) {
     this.myConnection=myConnection;
    }

    @Override
    public CompaniesListViewAdapter getAdapter() {
        return MyAdapter;
    }

    @Override
    public ApiConnection getConnection() {
        return myConnection;
    }

    @Override
    public void setMyObjects(ArrayList<Company> allCompanys) {
        this.AllCompanys=allCompanys;
    }

    @Override
    public void OnCompanySelect(int id){
        SurveyListFragment surveyListFragment = new SurveyListFragment();
        Bundle Argument=new Bundle();
        Argument.putInt("Id",id);
        surveyListFragment.setArguments(Argument);
        replaceFramgnet(surveyListFragment);
    }

    @Override
    public void OnSurveySelect(int id, String companyId) {
        Intent intent = new Intent(AnActivity.this,CompleteSurvey.class);
        intent.putExtra("surveyid",String.valueOf(id));
        intent.putExtra("companyId",companyId);
        startActivity(intent);

    }

    @Override
    public void setSurveyAdapter(SurveyListViewAdapter myAdapter) {
        SurveyAdapter=myAdapter;
    }

    @Override
    public SurveyListViewAdapter getSurverAdapter() {
        return SurveyAdapter;
    }

    @Override
    public void DeleteVoucher(String code) {
        LSL.DeleteVoucher(code);
    }

    @Override
    public void onDialogPositiveClick(String code) {
        this.DeleteVoucher(code);
        VoucherListFragment fragment = new VoucherListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.MyFragment, fragment, fragment.toString());
        fragmentTransaction.commit();
    }


}
