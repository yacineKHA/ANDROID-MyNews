package com.projet5.mynewsreprog;

import org.junit.Test;
import static org.junit.Assert.*;

public class FormatDateTest {

    private FormatDate formatDate = new FormatDate();

    @Test
    public void formatDateIsCorrect() {
        assertEquals("26/02/2020", formatDate.formatDate("2020-02-26"));
    }

    @Test
    public void toShowInTextView(){
        assertEquals("02/01/2020", formatDate.toShowInTextView(2020, 0,2));
    }

    @Test
    public void toSaveInString(){
        assertEquals("20200102", formatDate.toSaveInString(2020, 0,2));
    }
}