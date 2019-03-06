package com.bpass.kmastudent.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bpass.kmastudent.DTO.MonHocDTO;
import com.bpass.kmastudent.R;

import java.util.ArrayList;




/**
 * Created by VietDung-KMA-AT11D on 11/17/16.
 */

public class SubmitChonLopDialogAdapter extends ArrayAdapter<MonHocDTO> {
    Context context;
    int layout;
    ArrayList<MonHocDTO> listMonHoc;

    public SubmitChonLopDialogAdapter(Context context, int resource, ArrayList<MonHocDTO> listMonHoc) {
        super(context, resource, listMonHoc);
        this.context = context;
        this.layout = layout;
        this.listMonHoc = listMonHoc;
    }

    public class ViewHolder{
        TextView txtTenMonHocSubmitted;
        TextView txtTenLopHocSubmitted;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_dialog_row,parent,false);
            holder = new ViewHolder();
            holder.txtTenMonHocSubmitted = (TextView) convertView.findViewById(R.id.txtTenMonHocSubmitted);
            holder.txtTenLopHocSubmitted = (TextView) convertView.findViewById(R.id.txtTenLopHocSubmitted);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtTenMonHocSubmitted.setText(listMonHoc.get(position).getTenMonHoc());
        holder.txtTenLopHocSubmitted.setText(listMonHoc.get(position).getTenLopHoc());

        return  convertView;
    }
}
