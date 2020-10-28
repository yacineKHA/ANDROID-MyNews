package com.projet5.mynewsreprog;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * @author Yacine
 * @since 2020
 * Class for accessing and modifying preferences data with the SharedPreferences class.
 */
public class Preferences {

    protected SharedPreferences mPref;

    public Preferences(Context context) {
        mPref = context.getSharedPreferences("preferences", MODE_PRIVATE);
    }

    /**
     * Method to save a SharedPreferences.
     * @param name Name of SharedPreferences.
     * @param string String that you want to save.
     */
    protected void savePreferences(String name, String string) {
        SharedPreferences.Editor mEdit = mPref.edit();
        mEdit.putString(name, string);
        mEdit.apply();
    }

    /**
     * Method to load a String as saved in SharedPreferences.
     * @param name Name of the SharedPreferences that you want to load.
     * @return Return the String that is referenced with the name entered as a parameter.
     */
    protected String loadPreferences(String name) {

        return mPref.getString(name, "");
    }

    /**
     * Method to clear SharedPreference's file as saved.
     */
    protected void clearPreferences(){
        SharedPreferences.Editor mEdit = mPref.edit();
        mEdit.clear();
        mEdit.apply();
    }

    /**
     * Method to save a Boolean in SharedPreferences.
     * @param bool Boolean that you want to save in SharedPreference's file.
     */
    protected void saveBoolean(Boolean bool){
        SharedPreferences.Editor mEdit = mPref.edit();
        mEdit.putBoolean("name", bool);
        mEdit.apply();
    }


    /**
     * Method to load a Boolean saved in SharedPreferences.
     * @return Return a Boolean saved.
     */
    protected Boolean loadBoolean (){
        return mPref.getBoolean("name", false);
    }
}
