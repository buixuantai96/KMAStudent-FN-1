package com.bpass.kmastudent.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bpass.kmastudent.DAO.GiangVienDAO;
import com.bpass.kmastudent.DAO.MonHocDAO;
import com.bpass.kmastudent.DTO.GiangVienDTO;
import com.bpass.kmastudent.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;



public class ChonNamKyActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{
    ImageView imageLogo;
    TextView chonNam, chonKi;
    Spinner spnNam, spnKi;

    Button btnXacNhanNamKy;
    String nam;
    String ky;

    MonHocDAO monHocDAO;
    GiangVienDAO giangvienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chon_nam_ky);
        giangvienDAO = new GiangVienDAO(ChonNamKyActivity.this);
        insertGiangVienToDB();

        imageLogo = (ImageView) this.findViewById(R.id.logo);
        chonNam = (TextView) this.findViewById(R.id.text_chonnam);
        chonKi = (TextView) this.findViewById(R.id.text_chonki);

        btnXacNhanNamKy = (Button) findViewById(R.id.btnXacNhanNamKy);

        spnNam = (Spinner) this.findViewById(R.id.spn_nam);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.chonnam, R.layout.support_simple_spinner_dropdown_item);
        adapter1.notifyDataSetChanged();
        spnNam.setAdapter(adapter1);
        spnNam.setOnItemSelectedListener(this);

        spnKi = (Spinner) this.findViewById(R.id.spn_ki);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.chonki, R.layout.support_simple_spinner_dropdown_item);
        adapter2.notifyDataSetChanged();
        spnKi.setAdapter(adapter2);
        spnKi.setOnItemSelectedListener(this);



        btnXacNhanNamKy.setOnClickListener(ChonNamKyActivity.this);
    }

    public void insertGiangVienToDB(){
        giangvienDAO.deleteAll();
        // get image from drawable
        Bitmap image1 = BitmapFactory.decodeResource(getResources(),R.drawable.avatar_male);
        byte imageInByte1[] = getBytes(image1);

        Bitmap image2 = BitmapFactory.decodeResource(getResources(),R.drawable.avartar_female);
        byte imageInByte2[] = getBytes(image2);

//    /* MỚI */
//        //Năm 3 -ky1
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NDT","Nguyễn Đào Trường","Công nghệ thông tin","daoTruongnetwork@gmal.com","thầy Rất đẹp trai",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NTPA","Nguyễn Thị Phương Anh","Công nghệ thông tin","PhuongAnh@gmail.com","cô hiền",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NVP","Nguyễn Văn Phác","Công nghệ thông tin","vanPhacOtomat@gmail.com","otomat,rất khó",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("VXD","Vũ Xuân Đoàn","Điện tử viễn thông","doan@gmail.com","thầy nghiên cứu về kinh dịch",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NTN","Nguyễn Thanh Ngọc","Điện tử viễn thông","ngoc@gmail.com","unknown",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("LHN","Lại Hồng Nhung","Điện tử viễn thông","hongNhung@gmail.com","cô giáo nghiêm khắc nhưng nhiệt tình",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("DVH","Đặng Văn Hải","Điện tử viễn thông","haiDangVan@gmail.com","môn học rất khó",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("DVH1","Đỗ Văn Hiếu","Giáo dục thể chất","hieutc@gmail.com","unknown",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("HMH","Hà Mai Hoa","Giáo dục thể chất","maihoaGDTC@gmail.com","...",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("DHV","Đặng Hùng Việt","Điện tử viễn thông","hungbietdtvt@gmail.com","...",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("CTNQ","Chu Thị Ngọc Quỳnh","Điện tử viễn thông","QuynhProteus@gmail.com","....",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("LBC","Lê Bá Cường","Công nghệ thông tin","cuongOOP@gmail.com","thầy giáo với tư tưởng luôn đổi mới",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("VTD","Vũ Thị Đào","Công nghệ thông tin","daoJavaC@gmail.com","...",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NHV","Nguyễn Hồng Việt","Công nghệ thông tin","vietNetwork@gmail.com","...",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NTT","Nguyễn Thị Tý","Ngoại ngữ","tyThiNguyen@gmail.com","cô dạy rất cẩn thận và kỹ",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("MTH","Mai Thị Hảo","Ngoại ngữ","haoToeic@gmail.com","@@",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NTHH","Nguyễn Thị Hải Hà","Ngoại ngữ","haiHaEnglish@gmail.com","...",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("PBT","Phan Bích Thuận","Ngoại ngữ","thuanPhan@gmail.com","...",imageInByte2));
//
//        //Năm 3 - kỳ 2
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("none","No Name","Unknown Location","???@gmail.com","Hiện lớp này không có tên giảng viên trên trang đăng ký tín chỉ",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("LDT","Lê Đức Thuận","Công nghệ thông tin","???@gmal.com","giảng dạy nhiệt tình, ví dụ thực tế dễ hiểu, bậc thầy về quản lý danh sách người yêu",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("TTTN","Tô Thị Tuyết Nhung","Điện tử viễn thông","???@gmal.com","...",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("DTD","Dương Tuấn Đạt","Điện tử viễn thông","???@gmal.com","...",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("LBC","Lê Bá Cường","Công nghệ thông tin","???@gmal.com","...",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("PVH","Phạm Văn Hưởng","Công nghệ thông tin","???@gmal.com","...",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NTMT","Nguyễn Thị Minh Thu","Ngoại ngữ","???@gmail.com","...",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("TVC","Trần Văn Cường","Điện tử viễn thông","???@gmal.com","...",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("CTNQ","Chu Thị Ngọc Quỳnh","Điện tử viễn thông","???@gmal.com","...",imageInByte2));
//
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NVC","Nguyễn Việt Cường","Lý luận chính trị","???@gmal.com","...",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("TTT","Trần Thị Thuyết","Lý luận chính trị","???@gmal.com","...",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("CTT","Cù Thị Tặng","Lý luận chính trị","???@gmal.com","...",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("LTP","Lê Tiến Phương","Lý luận chính trị","???@gmal.com","...",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NTHP","Nguyễn Thị Hồng Phương","Lý luận chính trị","???@gmal.com","...",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NAT","Nguyễn Anh Thắng","Lý luận chính trị","???@gmal.com","...",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("DTTH","Đặng Thị Thu Hiền","Lý luận chính trị","???@gmal.com","...",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NVS","Nguyễn Văn Sơn","Cơ bản","???@gmal.com","...",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("TTKD","Thái Thị Kim Dung","Cơ bản","???@gmal.com","...",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("TTPT","Trần Thị Phương Thảo","Cơ bản","???@gmal.com","...",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("DXD","Đào Xuân Dương","Cơ bản","???@gmal.com","...",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("DTHG","Dương Thị Hồng Gấm","Cơ bản","???@gmal.com","...",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("DMN","Đỗ Minh Nam","Cơ bản","???@gmal.com","...",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NDP","Nguyễn Duy Phương","Cơ bản","???@gmal.com","...",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NTTV","Nguyễn Thị Thanh Vân","Cơ bản","???@gmal.com","...",imageInByte2));
//
//
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("TAT","Trần Anh Tú","An toàn thông tin","???@gmal.com","...",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("PMT","Phạm Minh Thuấn","An toàn thông tin","???@gmal.com","...",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("CMT","Cao Minh Tuấn","An toàn thông tin","???@gmal.com","...",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("HTN","Hoàng Thanh Nam","An toàn thông tin","???@gmal.com","...",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NTA","Nguyễn Tuấn Anh","An toàn thông tin","???@gmal.com","...",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("TTL","Trần Thị Lượng","An toàn thông tin","???@gmal.com","...",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("TTX","Trần Thị Xuyên","An toàn thông tin","???@gmal.com","...",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("HTP","Hoàng Thu Phương","An toàn thông tin","???@gmal.com","...",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NMT","Nguyễn Mạnh Thắng","An toàn thông tin","???@gmal.com","...",imageInByte1));
//


         /* MỚI */
//        //Năm 3 -ky1
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NDT","Nguyễn Đào Trường","Công nghệ thông tin","daoTruongnetwork@gmail.com","Thầy Rất đẹp trai",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NTPA","Nguyễn Thị Phương Anh","Công nghệ thông tin","PhuongAnh@gmail.com","Cô hiền",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NVP","Nguyễn Văn Phác","Công nghệ thông tin","vanPhacOtomat@gmail.com","Otomat,rất khó",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("VXD","Vũ Xuân Đoàn","Điện tử viễn thông","doan@gmail.com","Thầy nghiên cứu về kinh dịch",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NTN","Nguyễn Thanh Ngọc","Điện tử viễn thông","ngoc@gmail.com","unknown",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("LHN","Lại Hồng Nhung","Điện tử viễn thông","hongNhung@gmail.com","Cô giáo nghiêm khắc nhưng nhiệt tình",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("DVH","Đặng Văn Hải","Điện tử viễn thông","haiDangVan@gmail.com","Môn học rất khó",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("DVH1","Đỗ Văn Hiếu","Giáo dục thể chất","hieutc@gmail.com","unknown",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("HMH","Hà Mai Hoa","Giáo dục thể chất","maihoaGDTC@gmail.com","...",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("DHV","Đặng Hùng Việt","Điện tử viễn thông","hungbietdtvt@gmail.com","...",imageInByte1));
////        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("CTNQ","Chu Thị Ngọc Quỳnh","Điện tử viễn thông","QuynhProteus@gmail.com","....",imageInByte2));
////        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("LBC","Lê Bá Cường","Công nghệ thông tin","cuongOOP@gmail.com","Thầy giáo với tư tưởng luôn đổi mới",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("VTD","Vũ Thị Đào","Công nghệ thông tin","daoJavaC@gmail.com","...",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NHV","Nguyễn Hồng Việt","Công nghệ thông tin","vietNetwork@gmail.com","...",imageInByte1));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NTT","Nguyễn Thị Tý","Ngoại ngữ","tyThiNguyen@gmail.com","Cô dạy rất cẩn thận và kỹ",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("MTH","Mai Thị Hảo","Ngoại ngữ","haoToeic@gmail.com","@@",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NTHH","Nguyễn Thị Hải Hà","Ngoại ngữ","haiHaEnglish@gmail.com","...",imageInByte2));
//        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("PBT","Phan Bích Thuận","Ngoại ngữ","thuanPhan@gmail.com","...",imageInByte2));

        //Năm 3 - kỳ 2
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("none","No Name","Unknown Location","???@gmail.com","Hiện lớp này không có tên giảng viên trên trang đăng ký tín chỉ",imageInByte1));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("LDT","Lê Đức Thuận","Công nghệ thông tin","???@gmail.com","Giảng dạy nhiệt tình, ví dụ thực tế dễ hiểu, bậc thầy về quản lý danh sách người yêu",imageInByte1));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("TTTN","Tô Thị Tuyết Nhung","Điện tử viễn thông","???@gmail.com","...",imageInByte2));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("DTD","Dương Tuấn Đạt","Điện tử viễn thông","???@gmail.com","...",imageInByte1));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("LBC","Lê Bá Cường","Công nghệ thông tin","???@gmail.com","...",imageInByte1));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("PVH","Phạm Văn Hưởng","Công nghệ thông tin","???@gmail.com","...",imageInByte1));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NTMT","Nguyễn Thị Minh Thu","Ngoại ngữ","???@gmail.com","...",imageInByte2));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("TVC","Trần Văn Cường","Điện tử viễn thông","???@gmail.com","...",imageInByte1));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("CTNQ","Chu Thị Ngọc Quỳnh","Điện tử viễn thông","???@gmail.com","...",imageInByte2));

        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NVC","Nguyễn Việt Cường","Lý luận chính trị","???@gmail.com","...",imageInByte1));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("TTT","Trần Thị Thuyết","Lý luận chính trị","???@gmail.com","...",imageInByte2));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("CTT","Cù Thị Tặng","Lý luận chính trị","???@gmail.com","...",imageInByte2));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("LTP","Lê Tiến Phương","Lý luận chính trị","???@gmail.com","...",imageInByte1));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NTHP","Nguyễn Thị Hồng Phương","Lý luận chính trị","???@gmail.com","...",imageInByte2));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NAT","Nguyễn Anh Thắng","Lý luận chính trị","???@gmail.com","...",imageInByte1));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("DTTH","Đặng Thị Thu Hiền","Lý luận chính trị","???@gmail.com","...",imageInByte2));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NVS","Nguyễn Văn Sơn","Cơ bản","???@gmail.com","...",imageInByte1));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("TTKD","Thái Thị Kim Dung","Cơ bản","???@gmail.com","...",imageInByte2));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("TTPT","Trần Thị Phương Thảo","Cơ bản","???@gmail.com","...",imageInByte2));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("DXD","Đào Xuân Dương","Cơ bản","???@gmail.com","...",imageInByte1));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("DTHG","Dương Thị Hồng Gấm","Cơ bản","???@gmail.com","...",imageInByte2));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("DMN","Đỗ Minh Nam","Cơ bản","???@gmail.com","...",imageInByte1));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NDP","Nguyễn Duy Phương","Cơ bản","???@gmail.com","...",imageInByte1));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NTTV","Nguyễn Thị Thanh Vân","Cơ bản","???@gmail.com","...",imageInByte2));


        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("TAT","Trần Anh Tú","An toàn thông tin","???@gmail.com","...",imageInByte1));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("PMT","Phạm Minh Thuấn","An toàn thông tin","???@gmail.com","...",imageInByte1));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("CMT","Cao Minh Tuấn","An toàn thông tin","???@gmail.com","...",imageInByte1));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("HTN","Hoàng Thanh Nam","An toàn thông tin","???@gmail.com","...",imageInByte1));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NTA","Nguyễn Tuấn Anh","An toàn thông tin","???@gmail.com","...",imageInByte1));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("TTL","Trần Thị Lượng","An toàn thông tin","???@gmail.com","...",imageInByte2));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("TTX","Trần Thị Xuyên","An toàn thông tin","???@gmail.com","...",imageInByte2));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("HTP","Hoàng Thu Phương","An toàn thông tin","???@gmail.com","...",imageInByte2));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NMT","Nguyễn Mạnh Thắng","An toàn thông tin","???@gmail.com","...",imageInByte1));

        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("LTHV","Lê Thị Hồng Vân","Công nghệ thông tin","???@gmail.com","...",imageInByte2));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("TTV","Thái Thanh Vân","Công nghệ thông tin","???@gmail.com","...",imageInByte2));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("NTHT","Nguyễn Thị Huyền Trang","Ngoại ngữ","???@gmail.com","...",imageInByte2));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("DHP","Đoàn Hồng Phương","Ngoại ngữ","???@gmail.com","...",imageInByte1));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("BTG","Bùi Thị Giang","Cơ bản","???@gmail.com","...",imageInByte2));
        giangvienDAO.insertGiangVienToDB(new GiangVienDTO("PTPT","Phạm Thị Phương Thảo","Cơ bản","???@gmail.com","...",imageInByte2));


    }


    // convert from bitmap to byte array
    public byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.spn_nam:
                nam = spnNam.getItemAtPosition(spnNam.getSelectedItemPosition()).toString();
                break;
            case R.id.spn_ki:
                ky = spnKi.getItemAtPosition(spnKi.getSelectedItemPosition()).toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnXacNhanNamKy:
                dialogEvent(btnXacNhanNamKy);
                break;
        }
    }

    public void dialogEvent(View view){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ChonNamKyActivity.this);
        alertDialog.setMessage("\t\t\t\tBạn muốn chọn " + nam + " - " + ky + " ?").setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        monHocDAO = new MonHocDAO(ChonNamKyActivity.this);
                        Intent intent = new Intent(ChonNamKyActivity.this,ChonLopActivity.class);
                        ArrayList<String> IDNamKy = monHocDAO.layIDNamKy(nam,ky);
                        //Toast.makeText(ChonNamKyActivity.this,""+IDNamKy.size(),Toast.LENGTH_SHORT).show();
                        intent.putExtra("IDNamKy",IDNamKy.get(0));
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alert = alertDialog.create();
        alert.setTitle("Xác nhận");
        alert.show();

    }
}
