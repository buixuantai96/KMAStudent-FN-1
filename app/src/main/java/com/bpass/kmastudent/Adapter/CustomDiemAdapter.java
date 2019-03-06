package com.bpass.kmastudent.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.icu.lang.UCharacter;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.bpass.kmastudent.DAO.DiemDAO;
import com.bpass.kmastudent.DTO.DiemDTO;
import com.bpass.kmastudent.Extension.CustomRangeInputFilter;
import com.bpass.kmastudent.R;

import java.text.NumberFormat;
import java.util.ArrayList;


public class CustomDiemAdapter extends ArrayAdapter<DiemDTO> {

    Context context;
    ArrayList<DiemDTO> listDiemDTO;
    int layout;

    TextView txtTenMon, txtSoTin, txtDiemTB;
    EditText edtDiemCC, edtDiemGK, edtDiemKTHP;



    public CustomDiemAdapter(Context context, int layout, ArrayList<DiemDTO> listDiemDTO) {
        super(context, layout, listDiemDTO);
        this.context = context;
        this.layout = layout;
        this.listDiemDTO = listDiemDTO;
        //diemDAO = new DiemDAO(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        DiemDTO diemDTO = this.listDiemDTO.get(position);

            convertView = inflater.inflate(this.layout, parent, false);
           // viewHolderDiem = new ViewHolderDiem();



            txtTenMon = (TextView) convertView.findViewById(R.id.txtTM);
            txtSoTin = (TextView) convertView.findViewById(R.id.txtST);

            edtDiemCC = (EditText) convertView.findViewById(R.id.edtCC);
            edtDiemCC.addTextChangedListener(new MyTextWatcher(edtDiemCC, diemDTO, convertView));

            edtDiemGK = (EditText) convertView.findViewById(R.id.edtGK);
            edtDiemGK.addTextChangedListener(new MyTextWatcher(edtDiemGK, diemDTO, convertView));

            edtDiemKTHP = (EditText) convertView.findViewById(R.id.edtKTHP);
            edtDiemKTHP.addTextChangedListener(new MyTextWatcher(edtDiemKTHP, diemDTO, convertView));

            txtDiemTB = (TextView) convertView.findViewById(R.id.txtDiemTB);


        if (position % 2 == 0) {
            convertView.setBackgroundColor(Color.rgb(238, 233, 233));
        }

        txtTenMon.setText(diemDTO.getTENMH());
        txtSoTin.setText(String.valueOf(diemDTO.getSoTin()));


        if (String.valueOf(diemDTO.getDIEMCC()).equalsIgnoreCase("0.0")){
            edtDiemCC.setText("");
            edtDiemCC.addTextChangedListener(new MyTextWatcher(edtDiemCC, diemDTO, convertView));
            edtDiemCC.setFilters(new InputFilter[]{new CustomRangeInputFilter(0f, 10.0f)});
        }else{

            edtDiemCC.setText(String.valueOf(diemDTO.getDIEMCC()));
            edtDiemCC.addTextChangedListener(new MyTextWatcher(edtDiemCC, diemDTO, convertView));
            edtDiemCC.setFilters(new InputFilter[]{new CustomRangeInputFilter(0f, 10.0f)});
        }


        if (String.valueOf(diemDTO.getDIEMGK()).equalsIgnoreCase("0.0")){
            edtDiemGK.setText("");
            edtDiemGK.addTextChangedListener(new MyTextWatcher(edtDiemGK,diemDTO,convertView));
            edtDiemGK.setFilters(new InputFilter[]{new CustomRangeInputFilter(0f, 10.0f)});
        }
        else {
            edtDiemGK.setText(String.valueOf(diemDTO.getDIEMGK()));
            edtDiemGK.addTextChangedListener(new MyTextWatcher(edtDiemGK,diemDTO,convertView));
            edtDiemGK.setFilters(new InputFilter[]{new CustomRangeInputFilter(0f, 10.0f)});
        }



        if (String.valueOf(diemDTO.getDIEMKTHP()).equalsIgnoreCase("0.0")){
            edtDiemKTHP.setText("");
            edtDiemKTHP.addTextChangedListener(new MyTextWatcher(edtDiemKTHP,diemDTO,convertView));
            edtDiemKTHP.setFilters(new InputFilter[]{new CustomRangeInputFilter(0f, 10.0f)});
        }
        else {
            edtDiemKTHP.setText(String.valueOf(diemDTO.getDIEMKTHP()));
            edtDiemKTHP.addTextChangedListener(new MyTextWatcher(edtDiemKTHP,diemDTO,convertView));
            edtDiemKTHP.setFilters(new InputFilter[]{new CustomRangeInputFilter(0f, 10.0f)});
        }


        txtDiemTB.setText(String.valueOf(diemDTO.getDIEMTB()));

        return convertView;
    }

    class MyTextWatcher implements TextWatcher {
        EditText view;
        DiemDTO diemDTO;
        View convertView;
        DiemDAO diemDAO = new DiemDAO(context);
        public MyTextWatcher(EditText view, DiemDTO diemDTO, View convertView) {
            this.view = view;
            this.diemDTO = diemDTO;
            this.convertView = convertView;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            String text = s.toString();

            // phai Focus vao truoc sau do moi so sanh length
            //diemDAO = new DiemDAO(context);
            TextView txtTB = (TextView) this.convertView.findViewById(R.id.txtDiemTB);
            if (view.getText().toString().length() > 0) {
                switch (view.getId()) {
                    case R.id.edtCC:
                        if (view.isFocused()) {
                            view.setFilters(new InputFilter[]{new CustomRangeInputFilter(0f, 10.0f)});
                            this.diemDTO.setDIEMCC(Float.parseFloat(text));
                            diemDAO.updateDiem(this.diemDTO);
                            if(diemDAO.sosanh1() == diemDAO.sosanh2()){
                                diemDTO.setDIEMTK(diemDAO.TinhDiemTK(diemDTO.getMASV()));
                            }
                            else{
                                diemDTO.setDIEMTK(0.0f);
                            }
//                            diemDTO.setDIEMTK(diemDAO.TinhDiemTK(diemDTO.getMASV()));
                        }
                        break;
                    case R.id.edtGK:
                        if (view.isFocused()) {
                            view.setFilters(new InputFilter[]{new CustomRangeInputFilter(0f, 10.0f)});
                            this.diemDTO.setDIEMGK(Float.parseFloat(text));
                            diemDAO.updateDiem(this.diemDTO);
                            if(diemDAO.sosanh1() == diemDAO.sosanh2()){
                                diemDTO.setDIEMTK(diemDAO.TinhDiemTK(diemDTO.getMASV()));
                            }
                            else{
                                diemDTO.setDIEMTK(0.0f);
                            }
//                            diemDTO.setDIEMTK(diemDAO.TinhDiemTK(diemDTO.getMASV()));
                        }
                        break;
                    case R.id.edtKTHP:
                        if (view.isFocused()) {
                            view.setFilters(new InputFilter[]{new CustomRangeInputFilter(0f, 10.0f)});
                            this.diemDTO.setDIEMKTHP(Float.parseFloat(text));
                            diemDAO.updateDiem(this.diemDTO);
                            if(diemDAO.sosanh1() == diemDAO.sosanh2()){
                                diemDTO.setDIEMTK(diemDAO.TinhDiemTK(diemDTO.getMASV()));
                            }
                            else{
                                diemDTO.setDIEMTK(0.0f);
                            }
//                            diemDTO.setDIEMTK(diemDAO.TinhDiemTK(diemDTO.getMASV()));
                        }
                        break;
                }
            }
            txtTB.setText(String.valueOf(this.diemDTO.getDIEMCC() * 0.1f + this.diemDTO.getDIEMGK() * 0.2f + 0.7f * this.diemDTO.getDIEMKTHP()));
            this.diemDTO.setDIEMTB(this.diemDTO.getDIEMCC() * 0.1f + this.diemDTO.getDIEMGK() * 0.2f + 0.7f * this.diemDTO.getDIEMKTHP());

            View rootView = ((Activity)context).getWindow().getDecorView().findViewById(android.R.id.content);
            TextView txtDiemTK = (TextView) rootView.findViewById(R.id.txtDiemTK);
            diemDAO.updateDiem(this.diemDTO);
            txtDiemTK.setText(String.valueOf(DiemDTO.getDIEMTK()));



        }
    }
}






