package com.example.lmpandroid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


final class Period {
    private Date bTime;
    private Date eTime;

    Period(String begin, String end) {
        SimpleDateFormat format;
        String pattern;
        format = new SimpleDateFormat("dd-MM-yyyy ", Locale.UK);
        pattern=format.format(new Date());
        format.applyPattern("dd-MM-yyyy HH:mm");
        try {
            this.bTime = format.parse(pattern+begin);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            this.eTime = format.parse(pattern+end);
        } catch (ParseException e) {
            e.printStackTrace();
       }
    }

    Boolean inComing(Date date){
        return (bTime.before(date)&&eTime.after(date));
    }
}
