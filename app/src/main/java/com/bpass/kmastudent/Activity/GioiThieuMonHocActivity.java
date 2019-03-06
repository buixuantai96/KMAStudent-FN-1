package com.bpass.kmastudent.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bpass.kmastudent.DAO.GiangVienDAO;
import com.bpass.kmastudent.DTO.GiangVienDTO;
import com.bpass.kmastudent.DTO.MonHocDTO;
import com.bpass.kmastudent.R;

import java.util.ArrayList;



public class GioiThieuMonHocActivity extends AppCompatActivity {
    TextView txtTenMon,txtGioiThieuMon;
    Button btnGiangVien,btnTLTK;
    GiangVienDAO giangVienDAO;
    MonHocDTO monHocDTO;
    Toolbar toolbarGioithieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gioi_thieu_mon_hoc);

        toolbarGioithieu = (Toolbar) findViewById(R.id.toolbar_gtmonhoc);
        setSupportActionBar(toolbarGioithieu);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        giangVienDAO = new GiangVienDAO(GioiThieuMonHocActivity.this);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundleMonHocDTOCoChuaLop");
        final MonHocDTO monHocDTO = (MonHocDTO) bundle.getSerializable("monHocDTOCoChuaLop");

        btnGiangVien = (Button) findViewById(R.id.btnGiangVien);
        btnTLTK = (Button) findViewById(R.id.btnTLTK);

        txtTenMon = (TextView) findViewById(R.id.txtTenMon);
        txtGioiThieuMon = (TextView) findViewById(R.id.txtGioiThieuMon);

        txtTenMon.setText(monHocDTO.getTenMonHoc());
        txtGioiThieuMon.setText(monHocDTO.getGioiThieuMonHoc());

        btnGiangVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<GiangVienDTO> listGiangVien = giangVienDAO.layGiangVienTheoTenMonVaTenLop(monHocDTO.getIdMonHoc());
                Intent intent = new Intent(GioiThieuMonHocActivity.this,DanhSachGiangVienActivity.class);
                intent.putExtra("giangVienDTO",listGiangVien);
                startActivity(intent);
            }
        });
    }

}
