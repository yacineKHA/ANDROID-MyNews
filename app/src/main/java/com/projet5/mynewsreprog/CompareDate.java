package com.projet5.mynewsreprog;

import java.util.Date;

public class CompareDate {

    protected Boolean compareDateWithBefore(Date date1, Date date2) {
        return date1.before(date2);
    }

    protected Boolean compareDateWithAfter(Date date1, Date date2) {
        return date1.after(date2);
    }
}
