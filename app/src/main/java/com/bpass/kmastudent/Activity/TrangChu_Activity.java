package com.bpass.kmastudent.Activity;


import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.bpass.kmastudent.DTO.DayModel;
import com.bpass.kmastudent.DTO.MonHocDTO;
import com.bpass.kmastudent.FragmentApp.CalendarFragment;
import com.bpass.kmastudent.FragmentApp.ThongTinNhaPhatTrien_Fragment;

import com.bpass.kmastudent.Interface.CalendarListener;
import com.bpass.kmastudent.R;
import com.bpass.kmastudent.SessionManager.SessionManager;
import com.bpass.kmastudent.SharedPreferences.ListMonHocSharedPreferenceHelper;

import org.joda.time.DateTime;

import java.util.ArrayList;


/**
 * Created by Hoang Nguyen on 18-Nov-16.
 */

public class TrangChu_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FragmentManager fragmentManager;
    ArrayList<MonHocDTO> listMonHoc;
    SessionManager sessionManager;
    ListMonHocSharedPreferenceHelper listMonHocSharedPreferenceHelper;
    String masv;

    Dialog dialog, dialogtime;
    Button btnOk_chuthich;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu);

        SharedPreferences sharedPreferences = getSharedPreferences("thongtinmasv",MODE_PRIVATE);
        masv = sharedPreferences.getString("masv","");

        pushFragment(masv);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view_trangchu);
        toolbar = (Toolbar) findViewById(R.id.tbtoolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(TrangChu_Activity.this, drawerLayout, toolbar, R.string.mo, R.string.dong){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        //toolbar.setSubtitleTextColor(getResources().getColor(R.color.color_white));
        //toolbar.setTitleTextColor(getResources().getColor(R.color.color_white));

        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(TrangChu_Activity.this);

//        Intent itent = getIntent();
//        String maSV = itent.getStringExtra("masv");

        fragmentManager = getFragmentManager();


    }
    private CalendarListener listener = new CalendarListener() {
        @Override
        public void onDateSelected(DayModel dayModel) {
            showDialogOnCellTouch(dayModel);
        }
    };

    private void showDialogOnCellTouch(DayModel touchedCell) {
        if (!touchedCell.isPreviousOrLast()) {
            DateTime cellDateTime = touchedCell.getDateTime();
            String title, message;
            title = cellDateTime.toString("dd.MM.yyyy");
            message = cellDateTime.toString("dd MMMM yyyy");
            if (touchedCell.isToday()) {
                message += "\nToday";
            }
            if (touchedCell.isHoliday()) {
                message += "\n" + touchedCell.getHoliday().getEnglishName();
            }

            android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(this);
            dialogBuilder.setTitle(title)
                    .setMessage(message)
                    .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create().show();
        } else {
            Toast.makeText(this, "Empty cell", Toast.LENGTH_SHORT).show();
        }
    }

    private void pushFragment(String masv) {
        CalendarFragment calendarFragment = new CalendarFragment();
        calendarFragment.setCalendarListener(listener);
        Bundle args = new Bundle();
        args.putBoolean(CalendarFragment.HOLIDAYS_ENABLED, true);
        calendarFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.container, calendarFragment, CalendarFragment.TAG).commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.nav_home:

                break;
            case R.id.nav_mon_hoc:
                listMonHocSharedPreferenceHelper = new ListMonHocSharedPreferenceHelper();
                listMonHoc = new ArrayList<>();
                listMonHoc = listMonHocSharedPreferenceHelper.getListMonHoc(TrangChu_Activity.this,masv);
                Bundle bundle = new Bundle();
                Intent intent1 = new Intent(TrangChu_Activity.this,MonHocActivity.class);
                bundle.putSerializable("listMonHocCoChuaLop",listMonHoc);
                intent1.putExtra("bundleListMonHocCoChuaLop",bundle);
                startActivity(intent1);

                break;
            case R.id.nav_ql_diem:
                Intent iql_diem = new Intent(TrangChu_Activity.this, DiemActivity.class);
                startActivity(iql_diem);
                break;

            case R.id.nav_nhat_ky:
                Intent inote = new Intent(TrangChu_Activity.this, NoteActivity.class);
                startActivity(inote);
                break;
            case R.id.nav_chia_se:

                break;
            case R.id.nav_thong_tin:
                FragmentTransaction transaction_thong_tin = fragmentManager.beginTransaction();
                ThongTinNhaPhatTrien_Fragment thongTinNhaPhatTrien_fragment = new ThongTinNhaPhatTrien_Fragment();
                transaction_thong_tin.replace(R.id.container, thongTinNhaPhatTrien_fragment);
                transaction_thong_tin.commit();

                break;
            case R.id.nav_cai_dat:

                break;
            case R.id.nav_dang_xuat:
                sessionManager = new SessionManager();
                sessionManager.setPreferences(TrangChu_Activity.this,"status","0");
                Intent idangxuat = new Intent(TrangChu_Activity.this, DangNhap_Activity.class);
                startActivity(idangxuat);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.chu_thich){
            showDialogChuThich();
        }
        if(id == R.id.time_hoc){
            showDialogTimeHoc();
        }

        return super.onOptionsItemSelected(item);
    }

    public void showDialogChuThich() {
        dialog = new Dialog(TrangChu_Activity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setTitle("Chú Thích Ca Học");

        dialog.setContentView(R.layout.layout_chu_thich_ca_hoc);
        dialog.setCancelable(false);
        btnOk_chuthich = (Button) dialog.findViewById(R.id.btn_Ok_chu_thich);

        btnOk_chuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void showDialogTimeHoc() {
        dialogtime = new Dialog(TrangChu_Activity.this);
        dialogtime.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogtime.setContentView(R.layout.layout_dialog_time_hoc);
        dialogtime.setCancelable(false);
        btnOk_chuthich = (Button) dialogtime.findViewById(R.id.btn_Ok_time_hoc);

        btnOk_chuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogtime.dismiss();
            }
        });
        dialogtime.show();
    }

    @Override
    public void onBackPressed() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(TrangChu_Activity.this);
        builder.setTitle(R.string.out).setIcon(R.drawable.finish_96)
                .setMessage("Bạn muốn thoát ứng dụng?");
        builder.setPositiveButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setNegativeButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();     //tắt hẳn ứng dụng, lần sau vào sẽ chạy lại app từ đầu
                //moveTaskToBack(true);  //tắt ứng dụng, lần sau vào sẽ vào trang chủ, vẫn với tài khoản cũ
            }
        });
        builder.show();

//        finish();
    }
}






















