package com.example.project4;

import java.util.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateManager {

    public DateManager() {
    }

    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String TIME_FORMAT_12 = "hh:mm:ss a";
    private static final String TIME_FORMAT_24 = "HH:mm:ss";

    static Date currentTime = (Date) Calendar.getInstance().getTime();
//2. Lấy ngày
    public static String getDateString() {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.format(currentTime);}

//3. Lấy giờ (24 giờ)
    public static String getTime24String(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT_24);
        return format.format(date);}

//4. Lấy giờ (12 giờ)

    public static String getTime12String(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT_12);
        return format.format(date);}
    public static String getDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("E dd/MM/yyyy", Locale.getDefault());
        Date date = new Date();
        String formattedDate = dateFormat.format(date);

        try {
            date = dateFormat.parse(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

// In ra đối tượng Date đã được chuyển đổi
        return date+"";

    }
}
