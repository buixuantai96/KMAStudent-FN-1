package com.bpass.kmastudent.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bpass.kmastudent.DAO.SinhVienDAO;
import com.bpass.kmastudent.DTO.SinhVienDTO;
import com.bpass.kmastudent.R;


public class DangKy_Activity extends AppCompatActivity implements View.OnClickListener {

    EditText edtMaSV, edtMatKhau, edtHoTen, edtMail;
    Button btnXacNhan, btnThoat;
    SinhVienDAO sinhVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangky);

//        CreateDatabase createDatabase = new CreateDatabase(this);
//        createDatabase.open();

        edtMaSV = (EditText) findViewById(R.id.edt_masv);
        edtMatKhau = (EditText) findViewById(R.id.edt_mat_khau);
        edtHoTen = (EditText) findViewById(R.id.edt_hoten);
        edtMail = (EditText) findViewById(R.id.edt_mail);
        btnXacNhan = (Button) findViewById(R.id.btn_xac_nhan);
        btnThoat = (Button) findViewById(R.id.btn_thoat);

        sinhVienDAO = new SinhVienDAO(DangKy_Activity.this);
        btnXacNhan.setOnClickListener(DangKy_Activity.this);
        btnThoat.setOnClickListener(DangKy_Activity.this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_xac_nhan :
                String sMaSV = edtMaSV.getText().toString().trim();
                String sMatKhau = edtMatKhau.getText().toString().trim();
                String sHoTen = edtHoTen.getText().toString().trim();
                String sMail = edtMail.getText().toString().trim();


                if(TextUtils.isEmpty(sMaSV) || TextUtils.isEmpty(sMatKhau) || TextUtils.isEmpty(sHoTen) || TextUtils.isEmpty(sMail)){
                    Toast.makeText(DangKy_Activity.this, getResources().getString(R.string.nhapdudangky), Toast.LENGTH_SHORT).show();
                }
                else {
                    SinhVienDTO sv = new SinhVienDTO();
                    sv.setMASV(sMaSV);
                    sv.setMATKHAU(sMatKhau);
                    sv.setTENSV(sHoTen);
                    sv.setMAIL(sMail);
                    /* - Gán cho thuộc tính LOGINSTATUS của object sinh viên hiện tại vừa mới đăng ký = "0", đồng thời set luôn
                     giá trị trường LOGINSTATUS của Sinh viên hiện tại trong sqlite là "0" .
                    tức là đánh dấu " Bước 1 đăng nhập "
                      - khi sang màn hình ChonLopActivity, ở đó sẽ thực hiện câu truy vấn : update lại
                     cái TRƯỜNG LOGINSTATUS của Sinh viên ứng với mã sinh viên được nhập tại màn hình đăng ký
                     update lại giá trị là "1", tức là đánh dấu " Bước 2 đăng nhập "

                     Hội tụ đủ " Bước 1 đăng nhập " và " Bước 2 đăng nhập " thì sinh viên đó, sau khi đăng nhập lần
                     đầu tiên xong , vào được trang chủ, rồi lại ấn logout => Quay về màn hình đăng nhập
                     lúc này sinh viên đó nhập masv và mật khẩu xong, ấn vào nút đăng nhập thì sẽ được nhảy thẳng vào
                     bên trong TrangChuActivity mà không cần phải chọn lại 2 màn hình là :
                     ChonNamKyActivity và ChonLopActivity nữa */
                    sv.setLOGINSTATUS("0");
                    long kiemtra = sinhVienDAO.themSinhVien(sv);
                    if(kiemtra == -1){
                        Toast.makeText(DangKy_Activity.this,"Mã sinh viên " + sMaSV + " đã được đăng ký !",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        finish();
                        Toast.makeText(DangKy_Activity.this, "Đăng Ký Thành Công !", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btn_thoat:
                finish();
                break;
        }
    }
}
