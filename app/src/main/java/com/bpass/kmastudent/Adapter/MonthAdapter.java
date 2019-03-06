package com.bpass.kmastudent.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.bpass.kmastudent.DAO.LichHocDAO;
import com.bpass.kmastudent.DTO.DayModel;
import com.bpass.kmastudent.DTO.KhoangThoiGianDTO;
import com.bpass.kmastudent.R;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Adapter to fill month grid properly based on given collection of GridCellModel.
 */
public class MonthAdapter extends BaseAdapter {

    /**
     * max amount of lines for holiday text view
     */

    private final static int MAX_LINES = 2;

    private final static int DAYS_COUNT = 7;
    private List<KhoangThoiGianDTO> listKhoangThoiGian;


    private List<DayModel> days;
    private LayoutInflater mInflater;
    private Context context;
    LichHocDAO lichHocDAO;
    String masv;


    /**
     * Cells will be of this height by default - to make them square.
     */
    private int expectedMinimumHeight;

    public MonthAdapter(Context context, List<DayModel> daysList) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("thongtinmasv",MODE_PRIVATE);
        masv = sharedPreferences.getString("masv","");
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.days = daysList;
        expectedMinimumHeight = calculateMinimumHeight();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.grid_cell, viewGroup, false);

            holder = new ViewHolder();
            holder.textViewDate = (TextView) convertView.findViewById(R.id.grid_cell_tv0);
            holder.txt1 = (TextView) convertView.findViewById(R.id.grid_cell_tv1);
            holder.txt2 = (TextView) convertView.findViewById(R.id.grid_cell_tv2);
            holder.txt3 = (TextView) convertView.findViewById(R.id.grid_cell_tv3);
            holder.txt4 = (TextView) convertView.findViewById(R.id.grid_cell_tv4);
            holder.txt5 = (TextView) convertView.findViewById(R.id.grid_cell_tv5);

            holder.viewGroup = (ViewGroup) convertView;
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        LichHocDAO lichHocDAO = new LichHocDAO(context);

        DayModel day = getItem(position);
        holder.textViewDate.setText(Integer.toString(day.getDayNumber()));



        listKhoangThoiGian = lichHocDAO.LaydanhsachLichHoc(masv);
        for (KhoangThoiGianDTO item : listKhoangThoiGian) {
            List<Date> dates = getDates(item.getNGAYBATDAU(), item.getNGAYKETTHUC());// nhu the nay la toi lay duoc khoang cach giua 2nay. khong can dung thua toan qq kia nua
            for (Date date : dates) {
                String a = String.valueOf(date);
                String[] items1 = a.split("-");
                int year1 = Integer.parseInt(items1[0]);
                int month1 = Integer.parseInt(items1[1]);
                int day1 = Integer.parseInt(items1[2]);
                String b = String.valueOf(item.getTHU());
                String[] items2 = b.split("-");
                int first = Integer.parseInt(items2[0]);
                int second = Integer.parseInt(items2[1]);
                int thirst = Integer.parseInt(items2[2]);

                if (day.getDayNumber() == (day1) && day.getMonthNumber() == (month1) && day.getYearNumber() == year1) {
                    if (day.getDayofWeek() == first || day.getDayofWeek() == second || day.getDayofWeek() == thirst) {
                        if (holder.txt1.getText() == null || holder.txt1.getText().equals("") ) {
                            holder.txt1.setText(item.getIDLOPHOC());
                            switch (item.getCAHOC()) {
                                case 1:
                                    holder.txt1.setBackgroundColor(Color.rgb(204, 255, 255));
                                    break;
                                case 2:
                                    holder.txt1.setBackgroundColor(Color.rgb(153, 255, 153));
                                    break;
                                case 3:
                                    holder.txt1.setBackgroundColor(Color.rgb(255,255,153));
                                    break;
                                case 4:
                                    holder.txt1.setBackgroundColor(Color.rgb(255, 204, 153));
                                    break;

                                case 5:
                                    holder.txt1.setBackgroundColor(Color.rgb(238, 204, 255));
                                    break;
                                case 6:
                                    holder.txt1.setBackgroundColor(Color.rgb(191, 202, 230));
                                    break;

                            }
                        }
                        else {
                            if (holder.txt2.getText() == null || holder.txt2.getText().equals("") && !holder.txt1.getText().equals( item.getIDLOPHOC())) {
                                holder.txt2.setText(item.getIDLOPHOC());
                                switch (item.getCAHOC()) {
                                    case 1:
                                        holder.txt2.setBackgroundColor(Color.rgb(204, 255, 255));
                                        break;
                                    case 2:
                                        holder.txt2.setBackgroundColor(Color.rgb(153, 255, 153));
                                        break;
                                    case 3:
                                        holder.txt2.setBackgroundColor(Color.rgb(255,255,153));
                                        break;
                                    case 4:
                                        holder.txt2.setBackgroundColor(Color.rgb(255, 204, 153));
                                        break;
                                    case 5:
                                        holder.txt2.setBackgroundColor(Color.rgb(238, 204, 255));
                                        break;
                                    case 6:
                                        holder.txt2.setBackgroundColor(Color.rgb(191, 202, 230));
                                        break;
                                }}
                            else {
                                if (holder.txt3.getText() == null || holder.txt3.getText().equals("") && !holder.txt1.getText().equals( item.getIDLOPHOC()) &&
                                        !holder.txt2.getText().equals( item.getIDLOPHOC())) {
                                    holder.txt3.setText(item.getIDLOPHOC());
                                    switch (item.getCAHOC()) {
                                        case 1:
                                            holder.txt3.setBackgroundColor(Color.rgb(204, 255, 255));
                                            break;
                                        case 2:
                                            holder.txt3.setBackgroundColor(Color.rgb(153, 255, 153));
                                            break;
                                        case 3:
                                            holder.txt3.setBackgroundColor(Color.rgb(255,255,153));
                                            break;
                                        case 4:
                                            holder.txt3.setBackgroundColor(Color.rgb(255, 204, 153));
                                            break;

                                        case 5:
                                            holder.txt3.setBackgroundColor(Color.rgb(238, 204, 255));
                                            break;
                                        case 6:
                                            holder.txt3.setBackgroundColor(Color.rgb(191, 202, 230));
                                            break;
                                    }
                                }
                                else {
                                    if (holder.txt4.getText() == null || holder.txt4.getText().equals("") && !holder.txt1.getText().equals( item.getIDLOPHOC()) &&
                                            !holder.txt2.getText().equals( item.getIDLOPHOC()) && !holder.txt3.getText().equals( item.getIDLOPHOC())) {
                                        holder.txt4.setText(item.getIDLOPHOC());
                                        switch (item.getCAHOC()) {
                                            case 1:
                                                holder.txt4.setBackgroundColor(Color.rgb(204, 255, 255));
                                                break;
                                            case 2:
                                                holder.txt4.setBackgroundColor(Color.rgb(153, 255, 153));
                                                break;

                                            case 3:
                                                holder.txt4.setBackgroundColor(Color.rgb(255,255,153));
                                                break;
                                            case 4:
                                                holder.txt4.setBackgroundColor(Color.rgb(255, 204, 153));
                                                break;

                                            case 5:
                                                holder.txt4.setBackgroundColor(Color.rgb(238, 204, 255));
                                                break;
                                            case 6:
                                                holder.txt4.setBackgroundColor(Color.rgb(191, 202, 230));
                                                break;
                                        }
                                    }
                                    else {
                                        if (holder.txt5.getText() == null || holder.txt5.getText().equals("") && !holder.txt1.getText().equals( item.getIDLOPHOC()) &&
                                                !holder.txt2.getText().equals( item.getIDLOPHOC()) && !holder.txt3.getText().equals( item.getIDLOPHOC())
                                                && !holder.txt4.getText().equals( item.getIDLOPHOC())) {
                                            holder.txt5.setText(item.getIDLOPHOC());
                                            switch (item.getCAHOC()) {
                                                case 1:
                                                    holder.txt5.setBackgroundColor(Color.rgb(204, 255, 255));
                                                    break;
                                                case 2:
                                                    holder.txt5.setBackgroundColor(Color.rgb(153, 255, 153));
                                                    break;

                                                case 3:
                                                    holder.txt5.setBackgroundColor(Color.rgb(255,255,153));
                                                    break;
                                                case 4:
                                                    holder.txt5.setBackgroundColor(Color.rgb(255, 204, 153));
                                                    break;

                                                case 5:
                                                    holder.txt5.setBackgroundColor(Color.rgb(238, 204, 255));
                                                    break;
                                                case 6:
                                                    holder.txt5.setBackgroundColor(Color.rgb(191, 202, 230));
                                                    break;
                                            }
                                        }


                                    }

                                }

                            }
                        }
                    }


                }
            }

        }


//
//

//        String a = "2016/10/1";
//        String b = "2016/10/30";
//        String[] items1 = a.split("/");
//        int year1 = Integer.parseInt(items1[0]);
//        int month1 = Integer.parseInt(items1[1]);
//        int day1 = Integer.parseInt(items1[2]);
//        String[] items2 = b.split("/");
//        int year2   = Integer.parseInt(items2[0]);
//        int month2 = Integer.parseInt(items2[1]);
//        int day2 = Integer.parseInt(items2[2]);
        // String[] items1, items2;
//
//
//            for (int y = year1; y <= year2; y++) {
//                for (int z = month1; z <= month2; z++) {
//                    for (int i = day1; i <= day2; i++) {
//                        if (day.getDayNumber() == i && day.getMonthNumber() == z && day.getYearNumber() == y) {
//                            if (holder.txtMonth.getText() == "" || holder.txtMonth.getText() == null) {
//                                holder.txtMonth.setText(item.getIDLOPHOC());
//                            } else {
//                                holder.txtYear.setText(item.getIDLOPHOC());
//                            }
//                        }
//                    }
//
//                }
//
//
//            }
//
//        }


//            if(day.getNUMBER()== day.getDayNumber() && day.getMONTH() == day.getMonthNumber() && day.getYEAR() == day.getYearNumber()){

//            holder.txtDate.setText(String.valueOf(day.getNAME()));
//                holder.txtMonth.setText(String.valueOf(day.getMONTH()));
//                holder.txtYear.setText(String.valueOf(day.getYEAR()));
//            }

        if (day.isPreviousOrLast()) {
            holder.textViewDate.setTextColor(context.getResources().getColor(R.color.newcal_gridcell_stroke));
            holder.viewGroup.setBackgroundResource(R.drawable.gridcell_inactive_selector);
        } else {
            if (day.isWeekend()) {
                holder.textViewDate.setTextColor(context.getResources().getColor(R.color.holo_red_light));
            }

            if (day.isToday()) {
                holder.viewGroup.setBackgroundResource(R.drawable.gridcell_today_selector);
            } else {
                holder.viewGroup.setBackgroundResource(R.drawable.gridcell_selector);
            }

            if (day.isHoliday()) {
                holder.textViewDate.setTextColor(context.getResources().getColor(android.R.color.white));
                holder.viewGroup.setBackgroundResource(R.drawable.gridcell_holiday_selector);
            }
        }

        holder.viewGroup.setMinimumHeight(expectedMinimumHeight);

        return convertView;
    }

    @Override
    public int getCount() {
        return days.size();
    }

    @Override
    public DayModel getItem(int i) {
        return days.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * Calculates minimum cell height - to make them close to square.
     *
     * @return cell's minimum height, pixels.
     */
    private int calculateMinimumHeight() {
        int orientation = context.getResources().getConfiguration().orientation;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int base = orientation == Configuration.ORIENTATION_LANDSCAPE ?
                displayMetrics.heightPixels : displayMetrics.widthPixels;
        return base / DAYS_COUNT;
    }

    private class ViewHolder {
        private TextView textViewDate;
        private TextView txt1;
        private TextView txt2;
        private TextView txt3;
        private TextView txt4;
        private TextView txt5;
        private TextView textViewHoliday;
        private ViewGroup viewGroup;

    }


//    private static List<Date> getDates(String dateString1, String dateString2)
//    {
//        ArrayList<Date> dates = new ArrayList<Date>();
//        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
//
//        Date date1 = null;
//        Date date2 = null;
//
//        try {
//            date1 = df1 .parse(dateString1);
//            date2 = df1 .parse(dateString2);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        Calendar cal1 = Calendar.getInstance();
//        cal1.setTime(date1);
//
//
//        Calendar cal2 = Calendar.getInstance();
//        cal2.setTime(date2);
//
//        while(!cal1.after(cal2))
//        {
//            dates.add(cal1.getTime());
//            cal1.add(Calendar.DATE, 1);
//        }
//        return dates;
//    }

    private static List<Date> getDates(String dateString1, String dateString2) {
        ArrayList<Date> dates = new ArrayList<Date>();
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = null;
        Date date2 = null;


        date1 = Date.valueOf(dateString1);
        date2 = Date.valueOf(dateString2);

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);


        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        while (!cal1.after(cal2)) {
            dates.add(convertFromJAVADateToSQLDate(cal1.getTime()));
            cal1.add(Calendar.DATE, 1);
        }
        return dates;

    }

    public static java.sql.Date convertFromJAVADateToSQLDate(java.util.Date javaDate) {
        java.sql.Date sqlDate = null;
        if (javaDate != null) {
            sqlDate = new Date(javaDate.getTime());
        }
        return sqlDate;
    }

}