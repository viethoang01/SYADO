package com.example.project4;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeDateCurrenrt {
    // Tạo đối tượng SimpleDateFormat với định dạng mong muốn
    SimpleDateFormat dateFormat = new SimpleDateFormat("E dd/MM/yyyy", Locale.getDefault());

    // Lấy thời gian hiện tại
    Date date = new Date();

    // Định dạng lại ngày giờ theo định dạng mong muốn và in ra màn hình
    String formattedDate = dateFormat.format(date);

    Date getDate(){
        try {
            return  date = dateFormat.parse(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    ///

    //hiện tại dưới định dạng hh:mm:

    public String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String currentTime = dateFormat.format(new Date());
        return currentTime;
    }
//
    public TimeDateCurrenrt( ) {
    }

    public String convertVnd (int money){
        Locale locale = new Locale("vi", "VN"); // set locale for Vietnamese Vietnam
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale); // get currency formatter
        return formatter.format(money); // format amount
    }
}
