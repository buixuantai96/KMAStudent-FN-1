package com.bpass.kmastudent.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.bpass.kmastudent.DTO.MonHocDTO;
import com.bpass.kmastudent.R;

import java.util.ArrayList;



/**
 * Created by VietDung-KMA-AT11D on 11/15/16.
 */

public class ChonLopTheoMonAdapter extends ArrayAdapter<MonHocDTO> {
    Context context;
    int layout;
    ArrayList<MonHocDTO> listMonHoc;

    ArrayAdapter<String> adapterSpinner;
    String[] listLop;


    public ChonLopTheoMonAdapter(Context context, int layout, ArrayList<MonHocDTO> listMonHoc) {
        super(context, layout, listMonHoc);
        this.context = context;
        this.layout = layout;
        this.listMonHoc = listMonHoc;
    }


    public class ViewHolder {
        TextView txtThuTuMonHoc_ChonMon,txtTenMonHoc_ChonMon;
        Spinner spChonLop;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
            holder = new ViewHolder();
            holder.txtThuTuMonHoc_ChonMon = (TextView) convertView.findViewById(R.id.txtThuTuMonHoc_ChonMon);
            holder.txtTenMonHoc_ChonMon = (TextView) convertView.findViewById(R.id.txtTenMonHoc_ChonMon);
            holder.spChonLop = (Spinner) convertView.findViewById(R.id.spChonLop);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtThuTuMonHoc_ChonMon.setText(String.valueOf(position+1));
        holder.txtTenMonHoc_ChonMon.setText(this.listMonHoc.get(position).getTenMonHoc());

        switch (this.listMonHoc.get(position).getIdMonHoc()) {
            // Ky 2 năm 3 - khoa 10- năm 4
            case "ATMMT":
                listLop = this.context.getResources().getStringArray(R.array.ATMMT);
                break;
            case "DGKDHT":
                listLop = this.context.getResources().getStringArray(R.array.DGKDHT);
                break;
            case "GTATM":
                listLop = this.context.getResources().getStringArray(R.array.GTATM);
                break;
            case "KTGT":
                listLop = this.context.getResources().getStringArray(R.array.KTGT);
                break;
            case "PTTKM":
                listLop = this.context.getResources().getStringArray(R.array.PTTKM);
                break;
            case "TTANM":
                listLop = this.context.getResources().getStringArray(R.array.TTANM);
                break;

//            // ky` 1 - khoa 11- nam 3
//            case "QTM":
//                listLop = this.context.getResources().getStringArray(R.array.QTM);
//                break;
//            case "CTDL":
//                listLop = this.context.getResources().getStringArray(R.array.CTDL);
//                break;
//            case "CSTT":
//                listLop = this.context.getResources().getStringArray(R.array.CSTT);
//                break;
//            case "VXL":
//                listLop = this.context.getResources().getStringArray(R.array.VXL);
//                break;
//            case "DVM":
//                listLop = this.context.getResources().getStringArray(R.array.DVM);
//                break;
//            case "OOP":
//                listLop = this.context.getResources().getStringArray(R.array.OOP);
//                break;
//            case "TD5":
//                listLop = this.context.getResources().getStringArray(R.array.TD5);
//                break;
//            case "TA3":
//                listLop = this.context.getResources().getStringArray(R.array.TA3);
//                break;
//            case "DTTT":
//                listLop = this.context.getResources().getStringArray(R.array.DTTT);
//                break;

            //ky` 2 khoa 11 - nam 3
            case "HTVT":
                listLop = this.context.getResources().getStringArray(R.array.HTVT);
                break;
            case "KTMT":
                listLop = this.context.getResources().getStringArray(R.array.KTMT);
                break;
            case "KTDL":
                listLop = this.context.getResources().getStringArray(R.array.KTDL);
                break;
            case "KTTSL":
                listLop = this.context.getResources().getStringArray(R.array.KTTSL);
                break;
            case "LTM":
                listLop = this.context.getResources().getStringArray(R.array.LTM);
                break;
            case "PTUD":
                listLop = this.context.getResources().getStringArray(R.array.PTUD);
                break;
            case "TKHT":
                listLop = this.context.getResources().getStringArray(R.array.TKHT);
                break;
            case "TACN":
                listLop = this.context.getResources().getStringArray(R.array.TACN);
                break;
            case "THS":
                listLop = this.context.getResources().getStringArray(R.array.THS);
                break;

            //Ky2 - khoa 12 - nam 2

            case "TCPIP":
                listLop = this.context.getResources().getStringArray(R.array.TCPIP);
                break;
            case "DTTT":
                listLop = this.context.getResources().getStringArray(R.array.DTTT);
                break;
            case "TD4":
                listLop = this.context.getResources().getStringArray(R.array.TD4);
                break;
            case "KTLT":
                listLop = this.context.getResources().getStringArray(R.array.KTLT);
                break;
            case "LTCSDL":
                listLop = this.context.getResources().getStringArray(R.array.LTCSDL);
                break;
            case "OTO":
                listLop = this.context.getResources().getStringArray(R.array.OTO);
                break;
            case "QTM":
                listLop = this.context.getResources().getStringArray(R.array.QTM);
                break;
            case "TA2":
                listLop = this.context.getResources().getStringArray(R.array.TA2);
                break;
            case "TRR":
                listLop = this.context.getResources().getStringArray(R.array.TRR);
                break;



            //Ky2 - khoa 13 - nam 1
            case "DCSVN":
                listLop = this.context.getResources().getStringArray(R.array.DCSVN);
                break;
            case "TD2":
                listLop = this.context.getResources().getStringArray(R.array.TD2);
                break;
            case "KNM":
                listLop = this.context.getResources().getStringArray(R.array.KNM);
                break;
            case "LTC":
                listLop = this.context.getResources().getStringArray(R.array.LTC);
                break;
            case "CNMLN2":
                listLop = this.context.getResources().getStringArray(R.array.CNMLN2);
                break;
            case "TOAN2":
                listLop = this.context.getResources().getStringArray(R.array.TOAN2);
                break;
            case "VL1":
                listLop = this.context.getResources().getStringArray(R.array.VL1);
                break;



        }
        adapterSpinner = new ArrayAdapter<String>(this.context, android.R.layout.simple_spinner_item, listLop);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spChonLop.setAdapter(adapterSpinner);

        holder.spChonLop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                listMonHoc.get(position).setTenLopHoc(holder.spChonLop.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return convertView;
    }

}
