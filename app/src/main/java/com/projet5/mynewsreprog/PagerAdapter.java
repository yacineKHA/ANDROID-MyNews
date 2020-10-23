package com.projet5.mynewsreprog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.projet5.mynewsreprog.ApiBusiness.Fragment3;
import com.projet5.mynewsreprog.ApiMostViewed.Fragment1;
import com.projet5.mynewsreprog.ApiTopStories.Fragment2;

public class PagerAdapter extends FragmentPagerAdapter {

    private Fragment[] fragments = {new Fragment1(), new Fragment2(), new Fragment3()};
    private String [] titles ={"Most viewed", "Top stories", "Business"};

    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
