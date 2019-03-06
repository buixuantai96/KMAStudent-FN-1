package com.bpass.kmastudent.SessionManager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by VietDung-KMA-AT11D on 11/26/16.
 */

public class SessionManager {
    /* Hàm set này nhằm đánh dấu việc đăng nhập ở các màn hình nằm ở TRUNG và THƯỢNG, ví dụ ở trong app này của mình
     thì hàm này được áp dụng ở trong 2 màn hình đó là :
        + DangNhap_Activity ở chỗ btn_dang_nhap_dn (set cho biến cờ = 1
         tức là đã đăng nhập, từ lần sau mở app lên, sẽ từ SplashActivity nhảy thẳng vào trong TrangChuActivity mà không
         cần thông qua DangNhap_Activity nữa )
        + TrangChu_Acitivity ở trên chức năng Đăng xuất của Navigation Drawer (set cho biến cờ = 0
         tức là , ở lần mở app tiếp theo, người dùng sẽ phải đăng nhập đã rồi mới vào được TrangChu_Activity)
         **** ĐẾN ĐÂY lại vấp phải vấn đề, khi logout ra rồi lại đăng nhập lần thứ 2 thì người dùng vẫn bị nhảy vào
         2 màn hình là ChonNamKyActivity và ChonLopActivity . Vì vậy phải triển khai thêm 1 chuỗi logic biến cờ nữa
         mới được, lần đánh dấu chuỗi biến cờ thứ 2 này
             + bắt đầu từ màn hình DangKy_Activity, khởi đầu bằng việc set cho trường LOGINSTATUS trong SQLite của
             sinh viên hiện tại là "0"
             + tiếp theo là khi ấn nút Yes trên Dialog hiển thị tại màn hình ChonLopActivity thì sẽ thực hiện câu lệnh
             truy vấn, UPDATE giá trị của trường LOGINSTATUS trong SQLite của sinh viên hiện tại thành "1"
             + cuối cùng,kết thúc chuỗi đánh dấu biến cờ lần thứ 2 này tại màn hình DangNhapActivity , khi người dùng
             bấm vào nút DangNhap thì sẽ thực hiện câu lệnh điều kiện IF với 2 điều kiệu giá trị của cả 2 biến cờ
             là  "1" thì mới cho bay thẳng vào TrangChuActivity */
    public void setPreferences(Context context, String key, String value) {

        SharedPreferences.Editor editor = context.getSharedPreferences("sendPreferences", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();

    }


    public String getPreferences(Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences("sendPreferences", Context.MODE_PRIVATE);
        String position = prefs.getString(key, "");
        return position;
    }
}
