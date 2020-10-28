package com.projet5.mynewsreprog;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class CompareDateTest {
    //
    private CompareDate compareDate = new CompareDate();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Test
    public void compareDateWithBefore() throws ParseException {

        Date date1 = sdf.parse("20200102");
        Date date2 = sdf.parse("20200202");
        boolean result = compareDate.compareDateWithBefore(date1,date2);
        assertTrue(result);
    }

    @Test
    public void compareDateWithAfter() throws ParseException {

        Date date1 = sdf.parse("20200202");
        Date date2 = sdf.parse("20200102");
        boolean result = compareDate.compareDateWithAfter(date1,date2);
        assertTrue(result);
    }
}