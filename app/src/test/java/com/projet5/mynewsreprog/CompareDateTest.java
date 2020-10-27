package com.projet5.mynewsreprog;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class CompareDateTest {

    @Test
    public void compareDate() throws ParseException {
        CompareDate compareDate = new CompareDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date1 = sdf.parse("20200102");
        Date date2 = sdf.parse("20200202");
        boolean result = compareDate.compareDate(date1,date2);
        assertTrue(result);
    }
}