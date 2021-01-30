package com.akrasnoyarov.developerslife.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.akrasnoyarov.developerslife.fragments.GifFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class GifViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<String> mSections = new ArrayList<>();

    public GifViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        mSections.add("random");
        mSections.add("top");
        mSections.add("latest");
        mSections.add("hot");
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        String section = mSections.get(position);

        return GifFragment.newInstance(section);
    }

    @Override
    public int getCount() {
        return mSections.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mSections.get(position).toUpperCase(Locale.getDefault());
    }
}
