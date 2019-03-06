package com.bpass.kmastudent.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bpass.kmastudent.Adapter.AdapterMonHoc;
import com.bpass.kmastudent.DAO.MonHocDAO;
import com.bpass.kmastudent.DTO.MonHocDTO;
import com.bpass.kmastudent.R;

import java.util.ArrayList;



public class MonHocActivity extends AppCompatActivity {
    AdapterMonHoc adapterMonHoc;
    ArrayList<MonHocDTO> listMonHoc;
    ListView lvMonhoc;
    MonHocDAO monHocDAO;
    Toolbar toolbarMon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mon_hoc);
        addControls();
        toolbarMon = (Toolbar) findViewById(R.id.toolbar_monhoc);
        setSupportActionBar(toolbarMon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarMon.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void addControls() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundleListMonHocCoChuaLop");
        listMonHoc = new ArrayList<MonHocDTO>();
        listMonHoc = (ArrayList<MonHocDTO>) bundle.getSerializable("listMonHocCoChuaLop");

        monHocDAO = new MonHocDAO(MonHocActivity.this);
        lvMonhoc = (ListView) findViewById(R.id.lvMonHoc);

        //listMonHoc = monHocDAO.layListMonHoc("NAM31");
        adapterMonHoc = new AdapterMonHoc(MonHocActivity.this,R.layout.row_monhoc,listMonHoc);
        adapterMonHoc.notifyDataSetChanged();
        lvMonhoc.setAdapter(adapterMonHoc);


        lvMonhoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MonHocDTO monHocDTO = listMonHoc.get(i);
                Bundle bundleMonHoc = new Bundle();
                // Truyền cái bundle chứa tên LỚP
                bundleMonHoc.putSerializable("monHocDTOCoChuaLop",monHocDTO);
                Intent intent = new Intent(MonHocActivity.this,GioiThieuMonHocActivity.class);
                intent.putExtra("bundleMonHocDTOCoChuaLop",bundleMonHoc);
                startActivity(intent);
            }
        });
    }


}
