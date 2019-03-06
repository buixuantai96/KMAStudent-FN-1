package com.bpass.kmastudent.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import com.bpass.kmastudent.DAO.SinhVienDAO;
import com.bpass.kmastudent.R;
import com.bpass.kmastudent.SessionManager.SessionManager;

public class DangNhap_Activity extends AppCompatActivity implements View.OnClickListener{
    SinhVienDAO sinhVienDAO;
    EditText edtMaSV, edtMatKhau;
    Button btnDangNhap, btnDangKy;
    SessionManager sessionManager;
    CheckBox chk_nho_mat_khau;
    //đặt tên cho tập tin lưu trạng thái
    String prefthongtinnguoidung = "thongtinnguoidung";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);

        edtMaSV = (EditText) findViewById(R.id.edt_ma_sv_dn);
        edtMatKhau = (EditText) findViewById(R.id.edt_mat_khau_dn);
        btnDangNhap = (Button) findViewById(R.id.btn_dang_nhap_dn);
        btnDangKy = (Button) findViewById(R.id.btn_dang_ky_dn);
        chk_nho_mat_khau = (CheckBox) findViewById(R.id.chk_nho_mat_khau);

        //btnDangKy.setVisibility(View.GONE);       // ẩn btn này đi

        btnDangNhap.setOnClickListener(this);
        btnDangKy.setOnClickListener(this);
        sinhVienDAO = new SinhVienDAO(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_dang_nhap_dn:
               /*  */
                sessionManager = new SessionManager();
                sessionManager.setPreferences(DangNhap_Activity.this,"status","1");
                hamDangNhap();
                break;
            case R.id.btn_dang_ky_dn:
                hamDangKy();
                break;
        }
    }


    private void hamDangNhap(){
        String sMaSV = edtMaSV.getText().toString();
        String sMatKhau = edtMatKhau.getText().toString();
        boolean kiemtra_dn = sinhVienDAO.kiemTraDangNhap(sMaSV, sMatKhau);
        if(kiemtra_dn){
            SharedPreferences sharedPreferences = getSharedPreferences("thongtinmasv",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("masv",sMaSV);
            editor.putString("matkhau",sMatKhau);
            editor.commit();

            String loginStatus = sinhVienDAO.getLoginStatus(sMaSV);

            String checkLogOutThenLogin = sessionManager.getPreferences(DangNhap_Activity.this,"logoutthenlogin");
            if(checkLogOutThenLogin.equals("1") && loginStatus.equals("1")){
                Intent intent = new Intent(DangNhap_Activity.this,TrangChu_Activity.class);
                startActivity(intent);
            }
            else{
                Intent iChonNamKy = new Intent(DangNhap_Activity.this, ChonNamKyActivity.class);
                startActivity(iChonNamKy);
            }

        }
        else {
            Toast.makeText(DangNhap_Activity.this, "Sai tài khoản. Vui lòng nhập lại !", Toast.LENGTH_SHORT).show();
        }
    }

    private void hamDangKy(){
        Intent iDangKy = new Intent(DangNhap_Activity.this, DangKy_Activity.class);
        startActivity(iDangKy);
    }

    //Lưu trạng thái khi đóng app
    @Override
    protected void onPause() {
        super.onPause();
        //gọi hàm lưu trạng thái ở đây
        savingPreferences();
    }
    //Phục hồi khi mở app lại
    @Override
    protected void onResume() {
        super.onResume();
        //gọi hàm đọc trạng thái ở đây
        restoringPreferences();
    }

    //Hàm lưu trạng thái
    public void savingPreferences() {
        //tạo đối tượng getSharedPreferences
        SharedPreferences pre = getSharedPreferences
                (prefthongtinnguoidung, MODE_PRIVATE);
        //tạo đối tượng Editor để lưu thay đổi
        SharedPreferences.Editor editor = pre.edit();
        String user = edtMaSV.getText().toString();
        String pwd = edtMatKhau.getText().toString();
        boolean bchk = chk_nho_mat_khau.isChecked();
        if (!bchk) {
            //xóa mọi lưu trữ trước đó
            editor.clear();
        } else {
            //lưu vào editor
            editor.putString("user", user);
            editor.putString("pwd", pwd);
            editor.putBoolean("checked", bchk);
        }
        //Lưu file trạng thái
        editor.commit();
    }

    //Đọc trạng thái đã lưu
    public void restoringPreferences() {
        SharedPreferences pre = getSharedPreferences(prefthongtinnguoidung, MODE_PRIVATE);
        //lấy giá trị checked ra, nếu không thấy thì giá trị mặc định là false
        boolean bchk = pre.getBoolean("checked", false);
        if (bchk) {
            //lấy user, pwd, nếu không thấy giá trị mặc định là rỗng
            String user = pre.getString("user", "");
            String pwd = pre.getString("pwd", "");
            edtMaSV.setText(user);
            edtMatKhau.setText(pwd);
        }
        chk_nho_mat_khau.setChecked(bchk);
    }

    //public int back = 0;
    @Override
    public void onBackPressed() {
//        back ++;
//        if(back<=1){
//            Toast.makeText(DangNhap_Activity.this, "Nhấn 1 lần nữa để thoát !", Toast.LENGTH_SHORT).show();
//        }
//        else{
//            finishAffinity();
//        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(DangNhap_Activity.this);
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
            }
        });
        builder.show();
    }
}























