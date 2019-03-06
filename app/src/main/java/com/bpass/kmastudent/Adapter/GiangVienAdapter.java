package com.bpass.kmastudent.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bpass.kmastudent.DTO.GiangVienDTO;
import com.bpass.kmastudent.R;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;



/**
 * Created by VietDung-KMA-AT11D on 11/18/16.
 */

public class GiangVienAdapter extends ArrayAdapter<GiangVienDTO> {
    Context context;
    int layout;
    ArrayList<GiangVienDTO> listGiangVien;

    public GiangVienAdapter(Context context, int layout, ArrayList<GiangVienDTO> listGiangVien) {
        super(context, layout, listGiangVien);
        this.context = context;
        this.layout = layout;
        this.listGiangVien = listGiangVien;
    }

    public class ViewHolder{
        ImageView imgGiangVien;
        TextView txtTenGiangVien;
        TextView txtKhoaGiangVien;
        TextView txtEmailGiangVien;
        TextView txtGioiThieuGiangVien;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(this.layout,parent,false);
            holder = new ViewHolder();
            holder.imgGiangVien = (ImageView) convertView.findViewById(R.id.imgGiangVien);
            holder.txtTenGiangVien = (TextView) convertView.findViewById(R.id.txtTenGiangVien);
            holder.txtKhoaGiangVien = (TextView) convertView.findViewById(R.id.txtKhoaGiangVien);
            holder.txtEmailGiangVien = (TextView) convertView.findViewById(R.id.txtEmailGiangVien);
            holder.txtGioiThieuGiangVien = (TextView) convertView.findViewById(R.id.txtGioiThieuGiangVien);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        GiangVienDTO giangVienDTO = this.listGiangVien.get(position);

        /* Do ảnh giảng viên lưu trong Database là dạng Blob nên phải convert từ mảng byte sang bitmap sau đó mới
         set cai bitmap lấy được cho imageView */
        //Bitmap bmAnhGiangVien = BitmapFactory.decodeByteArray(giangVienDTO.getAnhGiangVien(),0,giangVienDTO.getAnhGiangVien().length);
        //holder.imgGiangVien.setImageBitmap(bmAnhGiangVien);

        holder.txtTenGiangVien.setText(giangVienDTO.getTenGiangVien());
        holder.txtKhoaGiangVien.setText(giangVienDTO.getKhoaGiangVien());
        holder.txtEmailGiangVien.setText(giangVienDTO.getEmailGiangVien());
        holder.txtGioiThieuGiangVien.setText(giangVienDTO.getGioiThieuGiangVien());

        //convert byte to bitmap take from contact class
        byte[] outImage=giangVienDTO.getAnhGiangVien();
        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        holder.imgGiangVien.setImageBitmap(theImage);

        return convertView;
    }
}

