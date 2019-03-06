package com.bpass.kmastudent.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bpass.kmastudent.DTO.MonHocDTO;
import com.bpass.kmastudent.R;
import com.bpass.kmastudent.SessionManager.SessionManager;
import com.bpass.kmastudent.SharedPreferences.ListMonHocSharedPreferenceHelper;

import java.util.ArrayList;

public class Splash extends AppCompatActivity {
    SessionManager sessionManager;
    ListMonHocSharedPreferenceHelper listMonHocSharedPreferenceHelper;
    ArrayList<MonHocDTO> listMonHoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);
        sessionManager = new SessionManager();

        // METHOD 1

        /****** Create Thread that will sleep for 3 seconds *************/
        Thread background = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 3 seconds
                    sleep(2500);

                    // After 5 seconds redirect to another intent
                    String status=sessionManager.getPreferences(Splash.this,"status");
                    Log.d("status",status);
                    if (status.equals("1")){
//                        Intent i=new Intent(Splash.this,TrangChu_Activity.class);
//                        startActivity(i);
                        SharedPreferences sharedPreferences = getSharedPreferences("thongtinmasv",MODE_PRIVATE);
                        String masv = sharedPreferences.getString("masv","");

                        /* Lấy listMonHoc có chứa Lớp có ràng buộc với cái masv đã được điền ở ô UserName ở
                        màn hình đăng nhập  */
                        listMonHocSharedPreferenceHelper = new ListMonHocSharedPreferenceHelper();
                        listMonHoc = listMonHocSharedPreferenceHelper.getListMonHoc(Splash.this,masv);

                        Intent intent = new Intent(Splash.this,TrangChu_Activity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("listMonHocCoChuaLop",listMonHoc);
                        intent.putExtra("bundleListMonHocCoChuaLop",bundle);
                        startActivity(intent);

                    }else{
                        Intent i=new Intent(Splash.this,DangNhap_Activity.class);
                        startActivity(i);
                    }


                    //Remove activity
                    finish();

                } catch (Exception e) {

                }
            }
        };

        // start thread
        background.start();
    }
}
