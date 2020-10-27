package com.projet5.mynewsreprog;

import org.junit.Test;
import static org.junit.Assert.*;

public class FormatDateTest {

    @Test
    public void formatDateIsCorrect() {
        FormatDate formatDate = new FormatDate();
        assertEquals("26/02/2020", formatDate.FormatDate("2020-02-26"));
    }
}