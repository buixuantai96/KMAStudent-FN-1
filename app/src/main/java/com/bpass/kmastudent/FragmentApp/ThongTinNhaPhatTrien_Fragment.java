package com.bpass.kmastudent.FragmentApp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bpass.kmastudent.R;



/**
 * Created by HoangNguyen on 18-Nov-16.
 */

public class ThongTinNhaPhatTrien_Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_thongtin_team, container, false);
        return view;
    }
}
