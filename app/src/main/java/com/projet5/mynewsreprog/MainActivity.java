package com.projet5.mynewsreprog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Yacine
 * @since 2020
 * Class to set the main activity with the tabLayout, ViewPager and navigationView..
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private TabLayout tabLayout;
    private PagerAdapter adapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        setTabLayout();
        setViewPager();
        drawerLayout = findViewById(R.id.drawaerLayout);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        setWorker();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu12, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Method to set the toolbar.
     */
    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("My News");
    }

    /**
     * Method to set the Viewpager.
     */
    private void setViewPager() {
        adapter = new PagerAdapter(getSupportFragmentManager(), 0);
        viewPager = findViewById(R.id.ViewPager);
        viewPager.setAdapter(adapter);
    }

    /**
     * Method to set the TabLayout.
     */
    @SuppressLint("ResourceAsColor")
    private void setTabLayout() {
        tabLayout = findViewById(R.id.tablayout);
        tabLayout.setSelectedTabIndicatorColor(R.color.colorBlack);
        tabLayout.getTabAt(2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:

                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.notification:
                Intent intent1 = new Intent(this, NotificationActivity.class);
                startActivity(intent1);
                break;
            case R.id.help:
                break;
            case R.id.about:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Method to set the periodic work request for notifications.
     */
    private void setWorker() {
        PeriodicWorkRequest saveRequest = new PeriodicWorkRequest.Builder(MyPeriodicWork.class, 20, TimeUnit.SECONDS).build();
        WorkManager.getInstance(getApplicationContext()).enqueueUniquePeriodicWork("NOTIF", ExistingPeriodicWorkPolicy.KEEP, saveRequest);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.most_viwed:
                viewPager.setCurrentItem(0);
                break;
            case R.id.top_stories:
                viewPager.setCurrentItem(1);
                break;
            case R.id.business:
                viewPager.setCurrentItem(2);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
