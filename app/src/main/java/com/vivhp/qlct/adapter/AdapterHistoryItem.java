package com.vivhp.qlct.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vivhp.qlct.Model.ModelHistory;
import com.vivhp.qlct.R;

import java.util.ArrayList;

/**
 * Created by vivhp on 10/29/2016.
 */

public class AdapterHistoryItem extends ArrayAdapter<ModelHistory> {

    Activity context = null;
    int LayoutId;
    ArrayList<ModelHistory> arrayList;
    TextView txtTenKhoan, txtSotien, txtLydo, txtTenTK, txtTenNhom;
    public AdapterHistoryItem(Activity context, int resource, ArrayList<ModelHistory> arrayList) {
        super(context, resource, arrayList);
        this.context = context;
        this.LayoutId = resource;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Check convert view == null
        if (convertView == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            convertView = layoutInflater.inflate(LayoutId, null);
            if (arrayList.size() > 0) {
                //Lay vi tri hien tai cua item
                ModelHistory history = arrayList.get(position);

                txtTenNhom = (TextView) convertView.findViewById(R.id.txtTennhom);
                txtTenKhoan = (TextView) convertView.findViewById(R.id.txtTenKhoan);
                txtSotien = (TextView) convertView.findViewById(R.id.txtMoney);
                txtLydo = (TextView) convertView.findViewById(R.id.txtLydo);
                txtTenTK = (TextView) convertView.findViewById(R.id.txtTenTK);

                //Gán Giá trị cho các View
                txtTenNhom.setText(history.getTenkhoan());
                txtTenKhoan.setText(history.getTennhom());
                txtSotien.setText(String.valueOf(history.getSotien()));
                txtLydo.setText(history.getLydo());
                txtTenTK.setText(history.getTentk());
                /**
                 * Check xem trạng thái đã trả hay còn nợ để đổi màu cho textView
                 * Đã trả: màu xanh
                 * Còn nợ: màu đỏ
                 **/

            } else {
            }
        }
        return convertView;
    }
}
