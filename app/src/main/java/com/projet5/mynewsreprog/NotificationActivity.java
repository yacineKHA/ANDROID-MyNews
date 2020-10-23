package com.projet5.mynewsreprog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.projet5.mynewsreprog.ApiSearch.Docs;

/**
 * @author Yacine
 * @since 2020
 * The class is to create the notifications'activity.
 */
public class NotificationActivity extends AppCompatActivity {

    private CheckBox checkBoxArts, checkBoxBusiness, checkBoxPolitics, checkBoxSports, checkBoxEntrepreneurs, checkBoxTravel;
    private String[] checkBoxString = {"arts", "business", "politics", "sports", "entrepreneurs", "travel"};
    private String[] checkboxList = new String[6];
    private Preferences preferences;
    private Toolbar toolbar;
    private EditText searchText;
    private SwitchCompat switchCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        checkBoxArts = findViewById(R.id.checkbox_arts);
        checkBoxTravel = findViewById(R.id.checkbox_travel);
        checkBoxEntrepreneurs = findViewById(R.id.checkbox_Entrepreneurs);
        checkBoxSports = findViewById(R.id.checkbox_sports);
        checkBoxPolitics = findViewById(R.id.checkbox_politics);
        checkBoxBusiness = findViewById(R.id.checkbox_business);
        preferences = new Preferences(getApplicationContext());

        searchText = findViewById(R.id.search_textN);

        setToolbar();
        setSwitchButton();
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
     * Method to get the checkboxes that are checked to finally be able to retrieve
     * the corresponding Strings.
     * @param view View to switch with the IDs of the view.
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

    /**
     * Method to set the switchButton and check if it's checked or not. If it's checked, the method
     * "saveData()" will be launch and the boolean of the button will be save.
     * If it's not checked, the preferences saved before will be clear.
     */
    private void setSwitchButton() {
        switchCompat = findViewById(R.id.switchButton);
        if (preferences.loadBoolean()) {
            switchCompat.setChecked(true);
        }
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    saveData();
                    preferences.saveBoolean(isChecked);
                    Toast.makeText(NotificationActivity.this, "Notifications On", Toast.LENGTH_SHORT).show();
                } else {
                    if (!buttonView.isChecked()) {
                        preferences.saveBoolean(false);
                        preferences.clearPreferences();
                        Toast.makeText(NotificationActivity.this, "Notifications Off", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    /**
     * Method to save the checked boxes and text into a preferences file.
     */
    public void saveData() {
        String check;
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

            //store data in SharedPreferences
            preferences.savePreferences("NOTIFICATION_SEARCH", check);
            Log.i("checkbox", "il y a :" + check);
        }

        if (searchText.getText() != null) {
            String search_text = searchText.getText().toString();
            preferences.savePreferences("NOTIFICATION_TEXT", search_text);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
