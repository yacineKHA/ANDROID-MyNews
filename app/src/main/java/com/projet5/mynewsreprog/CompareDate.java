package com.projet5.mynewsreprog;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CompareDate {

    protected Boolean compareDate(Date date1, Date date2) {
        return date1.before(date2);
    }

//    protected Boolean compareDate(String date1, String date2) throws ParseException {
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//
//        Date date3 = sdf.parse(date1);
//        Date date4 = sdf.parse(date2);
//        assert date3 != null;
//        assert date4 != null;
//        return date3.before(date4);
//    }
}
