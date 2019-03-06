package com.bpass.kmastudent.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.bpass.kmastudent.Adapter.GiangVienAdapter;
import com.bpass.kmastudent.DTO.GiangVienDTO;
import com.bpass.kmastudent.R;

import java.util.ArrayList;


public class DanhSachGiangVienActivity extends AppCompatActivity {
    ListView lvGiangVien;
    GiangVienAdapter giangVienAdapter;
    ArrayList<GiangVienDTO> listGiangVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhsachgiangvien);

        final Toolbar toolbarDanhSachGV = (Toolbar) findViewById(R.id.toolbar_danhsachgv);
        setSupportActionBar(toolbarDanhSachGV);
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        addControls();
    }

    private void addControls() {
        Intent intent = getIntent();
        lvGiangVien = (ListView) findViewById(R.id.lvGiangVien);



        listGiangVien = new ArrayList<>();
        listGiangVien = (ArrayList<GiangVienDTO>) intent.getSerializableExtra("giangVienDTO");
        giangVienAdapter = new GiangVienAdapter(DanhSachGiangVienActivity.this,R.layout.chi_tiet_giao_vien_row,listGiangVien);
        giangVienAdapter.notifyDataSetChanged();
        lvGiangVien.setAdapter(giangVienAdapter);
    }


}
