package com.projet5.mynewsreprog;

import java.util.Date;

public class CompareDate {

    private Date date1;
    private Date date2;

    protected Boolean comparaison(Date date1, Date date2){
        final boolean result;
        result = date1.before(date2);
        return result ;

    }
}
