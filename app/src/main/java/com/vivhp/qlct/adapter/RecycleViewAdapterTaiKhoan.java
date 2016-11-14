package com.vivhp.qlct.adapter;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vivhp.qlct.DBHelper.DataBaseHelper;
import com.vivhp.qlct.Model.Model_Taikhoan;
import com.vivhp.qlct.R;

import java.util.ArrayList;

/**
 * Created by vivhp on 10/26/2016.
 */

public class RecycleViewAdapterTaiKhoan extends RecyclerView.Adapter<RecycleViewAdapterTaiKhoan.ViewHolders> {
    ImageView img_tk;
    //Khai báo arrayList Kiểu Model Tài Khoản
    ArrayList<Model_Taikhoan> arrayList;

    /**
     * Bắt buộc phải tạo 1 class kê thừa ViewHolder
     * vì RecyclerView bắt cuộc phải có ViewHolder
     **/
    public class ViewHolders extends RecyclerView.ViewHolder {

        /**
         * Cần khai báo các View Control của item
         * như ListView
         **/
        public TextView name, type, money;
        String txt;
        ImageView img_tk;

        /**
         * Dùng để dịnh nghĩa các ItemView
         **/
        public ViewHolders(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            type = (TextView) itemView.findViewById(R.id.tv_type);
            money = (TextView) itemView.findViewById(R.id.tv_money);
            img_tk = (ImageView) itemView.findViewById(R.id.img_tk);

            /**
             * Set sự kiện khi click các View
             **/

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txt = name.getText().toString();
                    Snackbar snackbar = Snackbar.make(itemView, txt, Snackbar.LENGTH_SHORT);
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    sbView.setBackgroundColor(itemView.getResources().getColor(R.color.rectage_btn));
                    textView.setTextColor(itemView.getResources().getColor(R.color.white));
                    snackbar.show();
                }
            });

            type.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txt = type.getText().toString();
                    Snackbar snackbar = Snackbar.make(itemView, txt, Snackbar.LENGTH_SHORT);
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    sbView.setBackgroundColor(itemView.getResources().getColor(R.color.rectage_btn));
                    textView.setTextColor(itemView.getResources().getColor(R.color.white));
                    snackbar.show();
                }
            });

            money.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txt = money.getText().toString();
                    Snackbar snackbar = Snackbar.make(itemView, txt, Snackbar.LENGTH_SHORT);
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    sbView.setBackgroundColor(itemView.getResources().getColor(R.color.rectage_btn));
                    textView.setTextColor(itemView.getResources().getColor(R.color.white));
                    snackbar.show();
                }
            });
        }
    }

    //Constructor chứa ArrayList của đối tượng
    public RecycleViewAdapterTaiKhoan(ArrayList<Model_Taikhoan> taikhoanArrayList) {
        this.arrayList = taikhoanArrayList;
    }

    //onCreateViewHolder
    @Override
    public ViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate View từ layour custom cho item RecycleView
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_list_acc, parent, false);
        return new ViewHolders(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolders holder, int position) {
        //Gán giá trị cho các widget
        /**
         * Dựa theo kiểu dữ liệu của đối tượng
         * Cần get đúng kiểu dữ liệu và ép kiểu
         * chính xác
         **/
        Model_Taikhoan taikhoan = arrayList.get(position);
        holder.name.setText(taikhoan.getTentk());
        holder.type.setText(taikhoan.getLoaihinh());
        holder.money.setText(String.valueOf(taikhoan.getSotien()));
        holder.img_tk.setImageResource(R.mipmap.ic_t07);

    }


    public void addItem(int position, Model_Taikhoan model_taikhoan) {
        arrayList.add(position, model_taikhoan);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        arrayList.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return arrayList.get(position).get_id();
    }

    public void updateData(ArrayList<Model_Taikhoan> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

//    private View.OnClickListener onClickListener = new
}
