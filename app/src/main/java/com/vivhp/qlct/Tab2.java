package com.vivhp.qlct;

import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vivhp.qlct.DBHelper.DataBaseHelper;
import com.vivhp.qlct.Model.Model_Phannhom;
import com.vivhp.qlct.Model.Model_Thongke;
import com.vivhp.qlct.adapter.ExpandableListViewAdapter;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;
import static com.vivhp.qlct.R.id.DateTime;

/**
 * Created by vivhp on 10/16/2016.
 */

public class Tab2 extends android.support.v4.app.Fragment implements AdapterView.OnItemSelectedListener {

    View rootView;

    BroadcastReceiver broadcastReceiver;
    String TAG = "Tab 2";
    ExpandableListView ex_menu;
    HashMap<String, ArrayList<Model_Thongke>> mData;
    Spinner spinner_date;
    private String[] arr_date = {"Ngày", "Tháng", "Năm"};
    DataBaseHelper helper;
    String DateTime;
    TextView tvSetDateT2, DateTimeT2, tvType;
    ArrayAdapter<String> sp_adapter;
    String Dateqr, time_arg;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab2_statics, container, false);

        ex_menu = (ExpandableListView) rootView.findViewById(R.id.ex_menu);

        spinner_date = (Spinner) rootView.findViewById(R.id.spinner_date);

        helper = new DataBaseHelper(getActivity());

        initView();
        broadcast();
//        getTimeHienTai();

        return rootView;
    }

    public void initView(){
        tvType = (TextView) rootView.findViewById(R.id.tvType);
        tvSetDateT2 = (TextView) rootView.findViewById(R.id.SetDateT2);
        DateTimeT2 = (TextView) rootView.findViewById(R.id.DateTimeT2);
        initSpinner();
    }

    public void initSpinner(){
        sp_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, arr_date);
        sp_adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_date.setAdapter(sp_adapter);
        spinner_date.getSelectedItemPosition();
        spinner_date.setOnItemSelectedListener(this);
    }

    public void getTimeHienTai() {
        //Get current Date Time
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        DateTime = dateFormat.format(date);

        DateTimeT2.setText(DateTime);
        tvSetDateT2.setTextColor(rootView.getResources().getColor(R.color.white));
        tvSetDateT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime();
            }
        });
//        Toast.makeText(getActivity(), DateTime, Toast.LENGTH_SHORT).show();
    }

    private void setTime() {
        final Calendar calendar = Calendar.getInstance();

        tvSetDateT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog pickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String resultDate = String.valueOf(year + "/" + (month + 1) + "/" + dayOfMonth);
                        DateTimeT2.setText(resultDate);
                        initDataDate();
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                pickerDialog.show();
            }
        });
    }


    public void getMonth(){
    //Get current Date Time
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM");
        Date date = new Date();
        DateTime = dateFormat.format(date);
        DateTimeT2.setText(DateTime);
        tvSetDateT2.setTextColor(rootView.getResources().getColor(R.color.white));
        tvSetDateT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMonth();
            }
        });
//        Toast.makeText(getActivity(), DateTime, Toast.LENGTH_SHORT).show();
    }

    public void setMonth(){
        final Calendar calendar = Calendar.getInstance();

        tvSetDateT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog pickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String resultDate = year + "/" + (month+1);
                        DateTimeT2.setText(resultDate);
                        initData();
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                pickerDialog.show();
            }
        });
    }

    public void getYear(){
        //Get current Date Time
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date date = new Date();
        DateTime = dateFormat.format(date);
        DateTimeT2.setText(DateTime);
        tvSetDateT2.setTextColor(rootView.getResources().getColor(R.color.white));
        tvSetDateT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setYear();
            }
        });
//        Toast.makeText(getActivity(), DateTime, Toast.LENGTH_SHORT).show();
    }

    public void setYear(){
        final Calendar calendar = Calendar.getInstance();

        tvSetDateT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog pickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String resultDate = String.valueOf(year);
                        DateTimeT2.setText(resultDate);
                        initData();
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                pickerDialog.show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinner_date){
            Dateqr = sp_adapter.getItem(position).toString();
            if (Dateqr == "Ngày"){
                tvType.setText("Ngày: ");
                tvSetDateT2.setText(rootView.getResources().getString(R.string.set_date));
                getTimeHienTai();
                time_arg = DateTimeT2.getText().toString();
                Toast.makeText(getActivity(),"Date: " + time_arg, Toast.LENGTH_SHORT).show();
                initDataDate();
            } if (Dateqr == "Tháng"){
                tvType.setText("Tháng: ");
                tvSetDateT2.setText(rootView.getResources().getString(R.string.set_month));
                getMonth();
                time_arg = DateTimeT2.getText().toString();
                Toast.makeText(getActivity(),"Month: " + time_arg, Toast.LENGTH_SHORT).show();
                initData();
            } if (Dateqr == "Năm"){
                tvType.setText("Năm: ");
                tvSetDateT2.setText(rootView.getResources().getString(R.string.set_year));
                getYear();
                time_arg = DateTimeT2.getText().toString();
                Toast.makeText(getActivity(),"Year: " + time_arg, Toast.LENGTH_SHORT).show();
                initData();
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void initDataDate(){
        //Data for Group
        final List<String> lisHeader = new ArrayList<>();
        lisHeader.add("Tổng Thu");
        lisHeader.add("Tổng Chi");

        //Data for child
        mData = new HashMap<>();
        ArrayList<Model_Thongke> list_Thu = new ArrayList<>();
        list_Thu = helper.getGroupThuNgay(DateTimeT2.getText().toString());

        ArrayList<Model_Thongke> list_Chi = new ArrayList<>();
        list_Chi = helper.getGroupChiNgay(DateTimeT2.getText().toString());
        Log.e("List Chi size: ", String.valueOf(list_Chi.size()));

        //Put child data to list Group
        mData.put(lisHeader.get(0), list_Thu);
        mData.put(lisHeader.get(1), list_Chi);

        ExpandableListViewAdapter adapter = new ExpandableListViewAdapter(getActivity(), lisHeader, mData);
        ex_menu.setAdapter(adapter);

        ex_menu.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                Log.e(TAG, "onGroupClick: " + groupPosition);
//                Toast.makeText(getActivity(), "Group click: " + lisHeader.get(groupPosition), LENGTH_LONG).show();

                return false;
            }
        });

        ex_menu.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getActivity(), "Group Collapse: " + lisHeader.get(groupPosition), LENGTH_LONG).show();
            }
        });

        ex_menu.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getActivity(), "Group Expandable: " + lisHeader.get(groupPosition), LENGTH_LONG).show();
            }
        });

        ex_menu.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                Toast.makeText(getActivity(), "Item click: " + mData.get(lisHeader.get(groupPosition)).get(childPosition).toString(), LENGTH_LONG).show();
                return true;
            }
        });
    }

    public void initData(){
        //Data for Group
        final List<String> lisHeader = new ArrayList<>();
        lisHeader.add("Tổng Thu");
        lisHeader.add("Tổng Chi");

        //Data for child
        mData = new HashMap<>();
        ArrayList<Model_Thongke> list_Thu = new ArrayList<>();
        list_Thu = helper.getGroupThu(DateTimeT2.getText().toString());

        ArrayList<Model_Thongke> list_Chi = new ArrayList<>();
        list_Chi = helper.getGroupChi(DateTimeT2.getText().toString());
        Log.e("List Chi size: ", String.valueOf(list_Chi.size()));

        //Put child data to list Group
        mData.put(lisHeader.get(0), list_Thu);
        mData.put(lisHeader.get(1), list_Chi);

        ExpandableListViewAdapter adapter = new ExpandableListViewAdapter(getActivity(), lisHeader, mData);
        ex_menu.setAdapter(adapter);

        ex_menu.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                Log.e(TAG, "onGroupClick: " + groupPosition);
//                Toast.makeText(getActivity(), "Group click: " + lisHeader.get(groupPosition), LENGTH_LONG).show();

                return false;
            }
        });

        ex_menu.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getActivity(), "Group Collapse: " + lisHeader.get(groupPosition), LENGTH_LONG).show();
            }
        });

        ex_menu.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getActivity(), "Group Expandable: " + lisHeader.get(groupPosition), LENGTH_LONG).show();
            }
        });

        ex_menu.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                Toast.makeText(getActivity(), "Item click: " + mData.get(lisHeader.get(groupPosition)).get(childPosition).toString(), LENGTH_LONG).show();
                return true;
            }
        });
    }

    private void broadcast(){
        //Khai bao ve thuc hien lang nghe
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getExtras().getInt("a") == 2){
                   initData();
                }
            }
        };
        // đăng ký
        getActivity().registerReceiver(broadcastReceiver, new IntentFilter("2"));
    }
}
