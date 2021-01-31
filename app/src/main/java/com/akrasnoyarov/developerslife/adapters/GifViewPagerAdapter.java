package com.akrasnoyarov.developerslife.adapters;

import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.akrasnoyarov.developerslife.fragments.GifFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class GifViewPagerAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = GifViewPagerAdapter.class.getSimpleName();
    private final String[] mSections = {"random", "top", "latest", "hot"};
    private GifFragment[] mFragments = new GifFragment[mSections.length];


    public GifViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        String section = mSections[position];
        if (mFragments[position] == null) {
            mFragments[position] = GifFragment.newInstance(section);
        }
        return mFragments[position];
    }

    @Override
    public int getCount() {
        return mSections.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mSections[position].toUpperCase(Locale.getDefault());
    }
}
