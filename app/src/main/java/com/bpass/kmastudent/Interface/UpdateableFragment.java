package com.bpass.kmastudent.Interface;


import com.bpass.kmastudent.DTO.HolidayModel;
import org.joda.time.DateTime;
import java.util.ArrayList;

public interface UpdateableFragment {
    public void update(ArrayList<HolidayModel> holidays);

    public DateTime getDateTime();
}