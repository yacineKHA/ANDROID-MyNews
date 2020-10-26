package com.projet5.mynewsreprog;


import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class UnitTest {

    @Test
    public void formatDateIsCorrect() {

        FormatDate formatDate = new FormatDate();
        assertEquals("26/02/2020", formatDate.FormatDate("2020-02-26"));
    }
}