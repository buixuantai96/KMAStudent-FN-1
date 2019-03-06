package com.bpass.kmastudent.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bpass.kmastudent.DTO.MonHocDTO;
import com.bpass.kmastudent.R;

import java.util.List;


/**
 * Created by VietDung-KMA-AT11D on 11/13/16.
 */

public class AdapterMonHoc extends ArrayAdapter<MonHocDTO> {
    Activity context;
    int resource;
    List<MonHocDTO> objects;

    public AdapterMonHoc(Activity context, int resource, List<MonHocDTO> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    public class ViewHolder{
        TextView txtThuTuMonHoc;
        TextView txtTenMonHoc;
        TextView txtSoTinChi;
        RatingBar rtDoKhoMonHoc;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = inflater.inflate(this.resource,parent,false);
            viewHolder = new ViewHolder();

            viewHolder.txtThuTuMonHoc = (TextView) convertView.findViewById(R.id.txtThuTuMonHoc);
            viewHolder.txtTenMonHoc = (TextView) convertView.findViewById(R.id.txtTenMonHoc);
            viewHolder.txtSoTinChi = (TextView) convertView.findViewById(R.id.txtSoTinChi);
            viewHolder.rtDoKhoMonHoc = (RatingBar) convertView.findViewById(R.id.rtDoKhoMonHoc);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.txtThuTuMonHoc.setText(String.valueOf(position+1));
        viewHolder.txtTenMonHoc.setText(this.objects.get(position).getTenMonHoc());
        viewHolder.txtSoTinChi.setText("Số tín chỉ : " + String.valueOf(this.objects.get(position).getSoTinChi()));
        viewHolder.rtDoKhoMonHoc.setRating(this.objects.get(position).getDoKho());

        return  convertView;
    }
}
