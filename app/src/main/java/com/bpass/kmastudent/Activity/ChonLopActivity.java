package com.bpass.kmastudent.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

import com.bpass.kmastudent.Adapter.ChonLopTheoMonAdapter;
import com.bpass.kmastudent.Adapter.SubmitChonLopDialogAdapter;
import com.bpass.kmastudent.DAO.MonHocDAO;
import com.bpass.kmastudent.DAO.SinhVienDAO;
import com.bpass.kmastudent.DTO.MonHocDTO;
import com.bpass.kmastudent.R;
import com.bpass.kmastudent.SessionManager.SessionManager;
import com.bpass.kmastudent.SharedPreferences.ListMonHocSharedPreferenceHelper;

import java.util.ArrayList;
import java.util.PropertyPermission;


public class ChonLopActivity extends AppCompatActivity implements View.OnClickListener {
    ListView lvChonLopTheoMonHoc;
    Button btnXacNhanChonLop;

    MonHocDAO monHocDAO;
    SinhVienDAO sinhVienDAO;

    ArrayList<MonHocDTO> listMonHoc;
    ChonLopTheoMonAdapter chonLopTheoMonAdapter;

    String IDNamKy;

    ListMonHocSharedPreferenceHelper listMonHocSharedPreferenceHelper;

    SessionManager sessionManager;

    String masv;

    // Dialog hiện lên sau khi chọn xong các lớp
    SubmitChonLopDialogAdapter submitChonLopDialogAdapter;
    private Dialog listDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lop_theo_mon_da_nhap);
        addControls();
    }

    private void addControls() {
        SharedPreferences sharedPreferences = getSharedPreferences("thongtinmasv",MODE_PRIVATE);
        masv = sharedPreferences.getString("masv","");

        sinhVienDAO = new SinhVienDAO(ChonLopActivity.this);
        monHocDAO = new MonHocDAO(ChonLopActivity.this);
        lvChonLopTheoMonHoc = (ListView) findViewById(R.id.lvChonLopTheoMonHoc);
        btnXacNhanChonLop = (Button) findViewById(R.id.btnXacNhanChonLop);

        Intent intent = getIntent();
        IDNamKy = intent.getStringExtra("IDNamKy");
        //listMonHoc = new ArrayList<>();

        /* Lấy listMonHoc để truyền vào cho Adapter của cái ListView mà hiển thị màn hình chọn lớp theo môn học */
        /* lấy listMonHoc từ trong DB, lúc này  chỉ có cái listMonHoc là có cái thuộc tính Lớp vì nó là list các
         đối tượng MonHocDTO
         còn cái list mà được lấy ra dựa trên IDNamKy trong Database thì không hề có cột Lớp*/
        listMonHoc = monHocDAO.layListMonHoc(IDNamKy);

        /* Ở dòng code này, ta truyền cái listMonHoc vừa lấy được từ Database vào trong cho Adapter
          bên trong class ChonLopTheoMonAdapter, ở phần bắt sự kiện cho Spinner, mình đã bắt sự kiện theo cách :
          bắt được từng object MonHocDTO nằm trên mỗi row của listView, sau đó, set lại giá trị tenMonHoc cho
          object đó bằng cái giá trị của Spinner tại position mà người dùng đang chọn.
          **** LƯU Ý :
              - Lúc bắt class là lấy position của hàm getView()
              - Nhưng lúc set lại giá trị tenMonHoc cho object MonHocDTO thì lại sử dụng position của Spinner

          Như vậy, khi ta thay đổi việc chọn lớp học ở trên các Spinner nằm trên listView, thì khi ấn vào nút submit
          , các TextView hiển thị tên môn học trong đó cũng thay đổi theo giá trị mới nhất get được từ cái Spinner
          TỨC LÀ TA THỰC HIỆN SET GIÁ TRỊ tenMonHoc liên tục trong khi người dùng chọn lựa trên Spinner
           NÊN,lúc này trong từng cái object MonHocDTO của cái listMonHoc  (là danh sách môn học vừa lấy được
           từ Database ở trên kia ) , các giá trị tên môn học được set MỚI liên tục và liên tục */
        chonLopTheoMonAdapter = new ChonLopTheoMonAdapter(ChonLopActivity.this, R.layout.row_chon_lop_theo_mon, listMonHoc);
        chonLopTheoMonAdapter.notifyDataSetChanged();
        lvChonLopTheoMonHoc.setAdapter(chonLopTheoMonAdapter);

        btnXacNhanChonLop.setOnClickListener(ChonLopActivity.this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.btnXacNhanChonLop:
//                monHocDAO.insertDuLieu(listMonHoc);
//                break;

            case R.id.btnXacNhanChonLop:
                listDialog = new Dialog(ChonLopActivity.this);
                listDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                listDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                listDialog.setContentView(R.layout.list_dialog);


                ListView lvSubmitted = (ListView) listDialog.findViewById(R.id.lvSubmitted);
                submitChonLopDialogAdapter = new SubmitChonLopDialogAdapter(ChonLopActivity.this, R.layout.list_dialog_row, listMonHoc);
                submitChonLopDialogAdapter.notifyDataSetChanged();
                lvSubmitted.setAdapter(submitChonLopDialogAdapter);

                Button positiveButton = (Button) listDialog.findViewById(R.id.positiveButtonChonLopHoc);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        /*******
                         - thực hiện câu truy vấn update lại trường LOGINSTATUS trong SQLite
                         của đối tượng sinh viên vừa mới đăng ký xong và đăng nhập lần đầu tiên -> màn hình chọn Năm kỳ
                         -> rồi mới tới màn hình Chọn Lớp này
                         khi ấn vào nút Yes ở trên Dialog thì sẽ thực hiện update lại LOGINSTATUS trong SQLite thành "1"
                         tức là hoàn thành " Bước 2 đăng nhập "
                         - như vậy, sau này, Sinh Viên hiện tại muốn đăng xuất ra, sau đó lại đăng nhập vào thì không cần
                         phải thông qua 2 màn hình là Chọn Năm Kỳ và Chọn Lớp nữa.
                         - Tuy nhiên, lúc này lại vấp phải vấn đề là làm thế nào để cho cái listMonHoc có chứa Lớp nó luôn
                         luôn đi liền tương ứng với từng sinh viên khi mà các sinh viên cứ liên tục đăng nhập và đăng xuất
                         => Bây giờ ta phải lưu cái listMonHoc có chứa lớp đó vào bên trong SharedPreference sử dụng thư viện
                         Gson của google . và lưu theo masv . lấy listMonHoc có chứa lớp lên cũng phải lấy theo masv
                         ********/

                        /* Update biến cờ của đợt đánh dấu thứ 2 là LOGINSTATUS là "1" để xác lập hoàn thành xong
                         " BƯỚC 2 đăng nhập "*/
                        sinhVienDAO.updateLoginStatus(masv);

                        /* Đánh dấu biến cờ của đợt đánh dấu thứ 1 là "1" để xác lập hoàn thành
                          " Bước 1 đăng nhập " */
                        sessionManager = new SessionManager();
                        sessionManager.setPreferences(ChonLopActivity.this,"logoutthenlogin","1");

//                        SharedPreferences sharedPreferences = getSharedPreferences("thongtinmasv",MODE_PRIVATE);
//                        String masv = sharedPreferences.getString("masv","");

                        /* Lấy ra list các IDLop dựa vào tên lớp và tên môn
                          VD : L01 , Các dịch vụ mạng => QTM1 */
                        ArrayList<String> listIDLop = new ArrayList<String>();
                        for(MonHocDTO monHocDTO:listMonHoc){
                            listIDLop.add(monHocDAO.layIDLopTheoTenLopVaTenMon(monHocDTO.getTenLopHoc(),monHocDTO.getTenMonHoc()));
                        }
                        //Toast.makeText(ChonLopActivity.this,listIDLop.get(0),Toast.LENGTH_LONG).show();
                        /* Insert du lieu vao bang LichHocVaDiem
                          - Du lieu bao gom :
                            MaSV ,  IDLop , IDMonHoc , IDNamKy */

//                        for(MonHocDTO monHocDTO:listMonHoc){
//                            for(String s:listIDLop){
//                                monHocDAO.insertIntoLichHocVaDiem(masv,IDNamKy,monHocDTO,s);
//                            }
//                        }
                        for(int i=0;i<listMonHoc.size();i++){
                            for(int j=0;j<listIDLop.size();j++){
                                if(i == j) {
                                    monHocDAO.insertIntoLichHocVaDiem(masv, IDNamKy, listMonHoc.get(i), listIDLop.get(j));
                                }
                            }
                        }

                        /* - Lưu listMonHoc có chứa Lớp vào bên trong Shared Preference với RÀNG BUỘC là masv của sinh viên
                        vừa mới đang ký xong,đang đang nhập hiện tại là lần đầu tiên sau khi mở app
                        (hay nói ngắn gọn là, cái masv đã được nhập ở trên cái ô USERNAME tại màn hình DangNhap ),
                        bằng cách sử dụng thư viện Gson của google
                           - Lưu cái list này tại đây vào trong Shared Preference thì tí nữa sẽ lấy lên tại Splash */
                        listMonHocSharedPreferenceHelper = new ListMonHocSharedPreferenceHelper();
                        listMonHocSharedPreferenceHelper.saveListMonHoc(ChonLopActivity.this,listMonHoc,masv);

                        Intent intentCalendarActivity = new Intent(ChonLopActivity.this, CalendarActivity.class);
                        intentCalendarActivity.putExtra(CalendarActivity.CALENDAR_TYPE_PARAM, CalendarActivity.CalendarType.CUSTOM_MOBINDUSTRY);
                        startActivity(intentCalendarActivity);


                        Intent intent = new Intent(ChonLopActivity.this,TrangChu_Activity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("listMonHocCoChuaLop",listMonHoc);
                        intent.putExtra("bundleListMonHocCoChuaLop",bundle);
                        startActivity(intent);


                    }
                });
                Button negativeButton = (Button) listDialog.findViewById(R.id.negativeButtonChonLopHoc);
                negativeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listDialog.cancel();
                    }
                });
                listDialog.show();
                break;
        }
    }


}

