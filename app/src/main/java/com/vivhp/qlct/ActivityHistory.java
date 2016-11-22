package com.vivhp.qlct;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.vivhp.qlct.DBHelper.DataBaseHelper;
import com.vivhp.qlct.Model.ModelHistory;
import com.vivhp.qlct.adapter.AdapterHistory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ActivityHistory extends AppCompatActivity {

    ImageButton btnThem;
    ViewPager viewPager;
    ArrayList<String> arr;
    AdapterHistory adapterHistory;

    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dataBaseHelper = new DataBaseHelper(this);

        setViewPager();

        //Tao Image Button them tren ToolBar
        btnThem = (ImageButton) findViewById(R.id.btnThongtinThem);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_arrow_back_white_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityHistory.super.onBackPressed();
            }
        });


    }

    //Set sự kiện vuốt ngày trên ViewPager
    /**
     * get ngày giao dịch trong db ra 1 arrayList
     * Sắp xếp lại ngày theo thứ tự a => b
     * set vào adapter
     **/
    public void setViewPager() {
        arr = new ArrayList<String>();
        arr = dataBaseHelper.getNgay();

        //Sap xep ngay theo thu tu
        Collections.sort(arr, new Comparator<String>() {
            DateFormat format = new SimpleDateFormat("yyyy/MM/dd");

            @Override
            public int compare(String o1, String o2) {
                try {
                    return format.parse(o1).compareTo(format.parse(o2));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });

        //Khai bao va set Adapter cho Viewpager
        viewPager = (ViewPager) findViewById(R.id.viewpagerThongtin);
        adapterHistory = new AdapterHistory(getSupportFragmentManager(), arr);
        viewPager.setAdapter(adapterHistory);
        viewPager.setCurrentItem(arr.size() - 1);
        adapterHistory.notifyDataSetChanged();
    }

}
