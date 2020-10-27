package com.projet5.mynewsreprog;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.projet5.mynewsreprog.ApiSearch.SearchListActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Class to create a search activity. To allow you to search for articles according to dates and themes.
 */
public class SearchActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView begin_date_select, end_date_select;
    private Button button;
    private Calendar cal;
    private String BEGIN_DATE;
    private String END_DATE;
    private static Date date1, date2;
    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private CompareDate compareDate = new CompareDate();
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;
    private DatePickerDialog datePickerDialog;
    private CheckBox checkBoxArts, checkBoxBusiness, checkBoxPolitics, checkBoxSports, checkBoxEntrepreneurs, checkBoxTravel;
    private String[] checkBoxString = {"arts", "business", "politics", "sports", "entrepreneurs", "travel"};
    private String[] checkboxList = new String[6];
    private String monthS, dayOfMonthS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        checkBoxArts = findViewById(R.id.checkbox_arts);
        checkBoxTravel = findViewById(R.id.checkbox_travel);
        checkBoxEntrepreneurs = findViewById(R.id.checkbox_Entrepreneurs);
        checkBoxSports = findViewById(R.id.checkbox_sports);
        checkBoxPolitics = findViewById(R.id.checkbox_politics);
        checkBoxBusiness = findViewById(R.id.checkbox_business);
        button = findViewById(R.id.button);
        setToolbar();
        getSearchTextAndDate();


        begin_date_select = findViewById(R.id.begin_date_select);
        end_date_select = findViewById(R.id.end_date_select);
        dateSelect();
    }

    /**
     * Method to set the toolbar.
     */
    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Method to select and get a date (begin date and end date) with the calendar.
     */
    public void dateSelect() {
        cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        begin_date_select.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(SearchActivity.this, R.style.Theme_MaterialComponents_Dialog_Alert, mOnDateSetListener, year, month, day);
                Objects.requireNonNull(datePickerDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                datePickerDialog.show();

                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        //Month = month +1 because of the month January that it begins at 0;
                        month = month + 1;

                        if (month < 10) {
                            monthS = "0" + month;
                        } else {
                            monthS = String.valueOf(month);
                        }
                        if (dayOfMonth < 10) {
                            dayOfMonthS = "0" + dayOfMonth;
                        } else {
                            dayOfMonthS = String.valueOf(dayOfMonth);
                        }

                        begin_date_select.setText(dayOfMonthS + "/" + monthS + "/" + year);
                        BEGIN_DATE = year + monthS + dayOfMonthS;
                        Log.i("begindate", BEGIN_DATE);
                    }
                });
            }
        });

        end_date_select.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(SearchActivity.this, R.style.Theme_MaterialComponents_Dialog_Alert, mOnDateSetListener, year, month, day);
                Objects.requireNonNull(datePickerDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                datePickerDialog.show();

                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        //Month = month +1 because of the month January that it begins at 0;
                        month = month + 1;

                        if (month < 10) {
                            monthS = "0" + month;
                        } else {
                            monthS = String.valueOf(month);
                        }
                        if (dayOfMonth < 10) {
                            dayOfMonthS = "0" + dayOfMonth;
                        } else {
                            dayOfMonthS = String.valueOf(dayOfMonth);
                        }

                        end_date_select.setText(dayOfMonthS + "/" + monthS + "/" + year);
                        END_DATE = year + monthS + dayOfMonthS;
                        Log.i("enddate", END_DATE);
                    }
                });
            }
        });
    }

    /**
     * Method to get the text, dates and save them into a bundle.
     * The bundle will be intent into another activity (SearchListActivity.class).
     */
    private void getSearchTextAndDate() {
        EditText search_text = findViewById(R.id.search_text);

        button.setOnClickListener(v -> {
            final String text;
            String check;
            Bundle bundle = new Bundle();

            if (search_text.getText() != null) {
                text = search_text.getText().toString();
                bundle.putString("stringKey", text);
            }
            if (checkboxList.length > 0) {
                StringBuilder sb = new StringBuilder();
                for (String s : checkboxList) {
                    sb.append(s).append(" ");
                }
                if (sb.toString().contains("null")) {
                    check = sb.toString().replace("null", "");
                } else {
                    check = sb.toString();
                }

                bundle.putString("boxKey", check);
                Log.i("check", check);
            }

            if (BEGIN_DATE != null) {
                bundle.putString("begin_date", BEGIN_DATE);
                try {
                    date1 = simpleDateFormat.parse(BEGIN_DATE);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if (END_DATE != null) {
                bundle.putString("end_date", END_DATE);
                try {
                    date2 = simpleDateFormat.parse(END_DATE);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (bundle.getString("stringKey") != null && bundle.get("boxKey") != null && bundle.getString("begin_date") != null && bundle.getString("end_date") != null && compareDate.compareDate(date1, date2)) {
                Intent intent = new Intent(SearchActivity.this, SearchListActivity.class).putExtra("name", bundle);
                startActivity(intent);
            } else if(bundle.getString("begin_date") != null && bundle.getString("end_date") != null && !compareDate.compareDate(date1, date2)) {
                Toast.makeText(SearchActivity.this, "Begin date doit être inférieure à End date.", Toast.LENGTH_LONG).show();
            } else{
                Toast.makeText(SearchActivity.this, "Veuillez remplir tous les champs.", Toast.LENGTH_LONG).show();
            }
        });
    }


    /**
     * Method to get the checkboxes that are checked to finally be able to retrieve
     * the corresponding Strings.
     *
     * @param view View to get ID's of the activity
     */
    public void setCheckBoxes(View view) {

        Log.i("tag", "mess");
        switch (view.getId()) {
            case R.id.checkbox_arts:
                if (checkBoxArts.isChecked()) {
                    checkboxList[0] = checkBoxString[0];
                } else checkboxList[0] = " ";
                Log.i("tag", "boxart");
                break;
            case R.id.checkbox_business:
                if (checkBoxBusiness.isChecked()) {
                    checkboxList[1] = checkBoxString[1];
                } else checkboxList[1] = " ";
                break;
            case R.id.checkbox_politics:
                if (checkBoxPolitics.isChecked()) {
                    checkboxList[2] = checkBoxString[2];
                } else checkboxList[2] = " ";
                break;
            case R.id.checkbox_sports:
                if (checkBoxSports.isChecked()) {
                    checkboxList[3] = checkBoxString[3];
                } else checkboxList[3] = " ";
                break;
            case R.id.checkbox_Entrepreneurs:
                if (checkBoxEntrepreneurs.isChecked()) {
                    checkboxList[4] = checkBoxString[4];
                } else checkboxList[4] = " ";
                break;
            case R.id.checkbox_travel:
                if (checkBoxTravel.isChecked()) {
                    checkboxList[5] = checkBoxString[5];
                } else checkboxList[5] = " ";
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
