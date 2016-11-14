package com.vivhp.qlct.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vivhp.qlct.Model.Model_Thongke;
import com.vivhp.qlct.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by vivhp on 10/20/2016.
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    Context context;

    private List<String> mHeaderGroup;
    private HashMap<String, ArrayList<Model_Thongke>> mDataChild;

    public ExpandableListViewAdapter(Context context, List<String> HeaderGroup, HashMap<String, ArrayList<Model_Thongke>> data) {
        this.context = context;
        mHeaderGroup = HeaderGroup;
        mDataChild = data;
    }

    /**
     * Hàm getGroupCount
     * Chức năng: Thiết lập số lượng nhóm của Expandable List
     * Có bao nhiêu nhóm thì return bấy nhiêu số
     **/
    @Override
    public int getGroupCount() {
        return mHeaderGroup.size();
    }

    /**
     * Hàm getChildrenCount
     * Chức năng: Thiết lập số lượng item trong 1 group tương ứng
     **/
    @Override
    public int getChildrenCount(int groupPosition) {
        return mDataChild.get(mHeaderGroup.get(groupPosition)).size();
    }

    /**
     * Hàm getGroup
     * Chức năng: trả về phần tử tại groupPosition trong danh sách header group.
     **/
    @Override
    public Object getGroup(int groupPosition) {
        return mHeaderGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mDataChild.get(mHeaderGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * Hàm getGroupView
     * Chức năng: Cho phép tùy chỉnh giao diện của nhóm
     **/
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View rootView = convertView;
        /**Check xem view đã tồn tại chưa
         * Nếu chưa thì thực hiện custom layout với layout đã tạo**/
        if (rootView == null) {
            LayoutInflater li = LayoutInflater.from(context);
            rootView = li.inflate(R.layout.expand_layout_group_list, parent, false);
        }

        //Dùng rootView để khởi tạo TextView trong layout đã custom
        TextView tvTitle = (TextView) rootView.findViewById(R.id.tvTitle);

        tvTitle.setText(mHeaderGroup.get(groupPosition));
        tvTitle.setTextColor(rootView.getResources().getColor(R.color.colorAccent));
        /**
         * Set Icon cho Group Expandable khi click
         * Thứ tự sắp xếp icon
         * icon 1: icon khi đã expandable group
         * icon 2: icon khi chưa expandable group**/
//        int ic_arrow_no_expand = isExpanded ?
//                R.mipmap.ic_remove_circle_outline_black_24dp:
//                R.mipmap.ic_add_circle_outline_black_24dp;
//
//        int ic_arrow_is_expand = isExpanded ?
//                R.mipmap.ic_remove_circle_outline_white_24dp:
//                R.mipmap.ic_add_circle_outline_white_24dp;
//        //Dùng rootView để khởi tạo ImageView trong layout đã custom
        ImageView imgArrow = (ImageView) rootView.findViewById(R.id.imgArrow);

        if (isExpanded) {
            /**
             * Khi đã expand
             * Thực thi việc đổi màu nền cho Group View,
             * Đổi màu cho title,
             * đổi icon arrow**/
            rootView.setBackgroundResource(R.color.rectage_btn);
            tvTitle.setTextColor(rootView.getResources().getColor(R.color.colorTabSelected));
            imgArrow.setImageResource(R.mipmap.ic_remove_circle_outline_white_24dp);
        } else {
            /**
             * Khi không expand
             * Hoàn trả lại màu nền cho Group View,
             * màu cho title,
             * icon arrow**/
            rootView.setBackgroundResource(R.color.white);
            tvTitle.setTextColor(rootView.getResources().getColor(R.color.rectage_btn));
            imgArrow.setImageResource(R.mipmap.add_circle_outline_white_24dp);
        }


        return rootView;
    }

    /**
     * Hàm getChildView
     * Chức năng: Cho phép tùy chỉnh giao diện của Item trong Nhóm
     **/
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View rootView = convertView;
        /**Check xem view đã tồn tại chưa
         * Nếu chưa thì thực hiện custom layout với layout đã tạo**/
        if (rootView == null) {
            LayoutInflater li = LayoutInflater.from(context);
            rootView = li.inflate(R.layout.expand_layout_item_list, parent, false);
        }

        Model_Thongke thongke =  mDataChild.get(mHeaderGroup.get(groupPosition)).get(childPosition);

        TextView tvText = (TextView) rootView.findViewById(R.id.tv_tennhom);
        TextView tvSotien = (TextView) rootView.findViewById(R.id.tv_sotien);
        tvText.setText(thongke.getTenNhom());
        tvText.setTextColor(rootView.getResources().getColor(R.color.rectage_btn));

        tvSotien.setText(String.valueOf(thongke.getSoTien()));
        tvSotien.setTextColor(rootView.getResources().getColor(R.color.color_red));
        return rootView;
    }

    /**
     * Hàm isChildSelectable
     * Chức năng: xác định xem child có được chạm hay chọn không.
     * Cần return = true để hàm setOnChildClickListener có thể hoạt đọngo
     **/
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
