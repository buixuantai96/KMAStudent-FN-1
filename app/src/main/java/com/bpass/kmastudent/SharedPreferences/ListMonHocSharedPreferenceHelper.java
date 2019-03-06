package com.bpass.kmastudent.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.bpass.kmastudent.DTO.MonHocDTO;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by VietDung-KMA-AT11D on 11/26/16.
 */

/* Class này sử dụng thư viện GSON , dùng để lưu Object hoặc lưu ArrayList<Object> vào trong Shared Preference
 */

public class ListMonHocSharedPreferenceHelper {
    //public static final String PREFS_NAME = "thongtinlistmonhoc";

    public static final String LISTMONHOC = "listmonhoc";

    public ListMonHocSharedPreferenceHelper() {
        super();
    }

    /* Lưu listMonHoc có chứa Lớp vào trong Shared Preference với ràng buộc là Mã sinh viên
     tức là, mỗi khi gọi hàm này thì sẽ tạo ra 1 file Shared Preference dạng XML có tên chính là cái đối số
     masv mình truyền vào cho hàm này */
    /* Hàm này sử dụng để lưu lại ArrayList<Kiểu object nào đó> vào trong Shared Preference*/
    public void saveListMonHoc(Context context, ArrayList<MonHocDTO> listMonHoc, String masv) {

        SharedPreferences settings;
        SharedPreferences.Editor editor;

//        settings = context.getSharedPreferences(PREFS_NAME,
//                Context.MODE_PRIVATE);
        settings = context.getSharedPreferences(masv,Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(listMonHoc);

        editor.putString(LISTMONHOC, jsonFavorites);

        editor.commit();
    }

    /* Hàm này sử dụng để thêm 1 cái Object có kiểu nào đó vào bên trong cái ArrayList<Object> hiện tại đang nằm
     trong Shared Preference*/
    public void addMonHocDTO(Context context, MonHocDTO monHocDTO, String masv) {
        ArrayList<MonHocDTO> listMonHoc = getListMonHoc(context,masv);
        if (listMonHoc == null)
            listMonHoc = new ArrayList<MonHocDTO>();
        listMonHoc.add(monHocDTO);
        saveListMonHoc(context, listMonHoc,masv);
    }

    /* Hàm này sử dụng để loại bỏ 1 cái Object có kiểu nào đó bên trong cái ArrayList<Object> hiện tại đang nằm
     trong Shared Preference */
    public void removeMonHocDTO(Context context, MonHocDTO monHocDTO, String masv) {
        ArrayList<MonHocDTO> listMonHoc = getListMonHoc(context,masv);
        if (listMonHoc != null) {
            listMonHoc.remove(monHocDTO);
            saveListMonHoc(context, listMonHoc,masv);
        }
    }

    /* Lấy listMonHoc có chứa Lớp , với ràng buộc là masv
    tức là sẽ lấy thông tin ở trong file Shared Preference dạng XML có tên chính là cái đối số masv mà mình truyền
    vào cho hàm này */
    /* Hàm này sử dụng để lấy cái ArrayList<Object có kiểu nào nó> từ bên trong Shared Preference ra ngoài ^ ^*/
    public ArrayList<MonHocDTO> getListMonHoc(Context context, String masv) {
        SharedPreferences settings;
        List<MonHocDTO> listMonHoc;

//        settings = context.getSharedPreferences(PREFS_NAME,
//                Context.MODE_PRIVATE);
        settings = context.getSharedPreferences(masv,Context.MODE_PRIVATE);

        if (settings.contains(LISTMONHOC)) {
            String jsonFavorites = settings.getString(LISTMONHOC, null);
            Gson gson = new Gson();
            MonHocDTO[] dsMonHoc = gson.fromJson(jsonFavorites,
                    MonHocDTO[].class);

            listMonHoc =  Arrays.asList(dsMonHoc);
            listMonHoc = new ArrayList<MonHocDTO>(listMonHoc);
        } else
            return null;

        return  (ArrayList<MonHocDTO>)listMonHoc;
    }
}
