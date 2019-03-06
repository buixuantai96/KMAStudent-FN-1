package com.bpass.kmastudent.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.bpass.kmastudent.Adapter.CustomDiemAdapter;
import com.bpass.kmastudent.DAO.DiemDAO;
import com.bpass.kmastudent.DTO.DiemDTO;
import com.bpass.kmastudent.R;

import java.util.ArrayList;




public class DiemActivity extends AppCompatActivity implements View.OnClickListener {
    TableLayout TBDiem;
    //TextView txtDiemTK;

    GridView gvDiem;
    CustomDiemAdapter customDiemAdapter;
    ArrayList<DiemDTO> listDiemDTO;
    //DiemDTO diemDTO;
    String masv;
    TextView tvmasv;

    DiemDAO diemDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_diem);

        SharedPreferences sharedPreferences = getSharedPreferences("thongtinmasv",MODE_PRIVATE);
        masv = sharedPreferences.getString("masv","");

        listDiemDTO = new ArrayList<>();
        diemDAO = new DiemDAO(DiemActivity.this);
        listDiemDTO = diemDAO.LayDanhSachDIEM(masv);

        //diemDTO = new DiemDTO();

        gvDiem = (GridView) findViewById(R.id.gvDiem);
        //txtDiemTK =(TextView) findViewById(R.id.txtDiemTK);
        customDiemAdapter = new CustomDiemAdapter(DiemActivity.this, R.layout.layout_gridview_custom, listDiemDTO);
        gvDiem.deferNotifyDataSetChanged();
        gvDiem.setAdapter(customDiemAdapter);
        tvmasv = (TextView) findViewById(R.id.txtmasv);
        tvmasv.setText(masv);

    }


    @Override
    public void onClick(View v) {


    }

    @Override
    public void onBackPressed() {
        finish();
    }
}


