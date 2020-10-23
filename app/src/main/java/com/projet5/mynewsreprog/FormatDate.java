package com.projet5.mynewsreprog;


/**
 * @author Yacine
 * @since 2020
 * Class to change the format of an entered date.
 */
public class FormatDate {

    private String day;
    private String month;
    private String year;

    /**
     * This is a method to format a YYYY/MM/DD date to a DD/MM/YYYY date.
     * @param date The string of the date that you need to format.
     * @return Return the formatted date in a String.
     */
    public String FormatDate( String date) {

        day = date.charAt(8) + Character.toString(date.charAt(9));
        month = date.charAt(5) + Character.toString(date.charAt(6));
        year = Character.toString(date.charAt(0)) + Character.toString(date.charAt(1)) +Character.toString(date.charAt(2)) + Character.toString(date.charAt(3));
        String dateFormat = day + "/" + month +"/" + year;
        return dateFormat;
    }
}
