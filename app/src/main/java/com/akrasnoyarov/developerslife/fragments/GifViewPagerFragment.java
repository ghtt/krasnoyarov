package com.akrasnoyarov.developerslife.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.akrasnoyarov.developerslife.R;
import com.akrasnoyarov.developerslife.adapters.GifViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class GifViewPagerFragment extends Fragment {
    private static final String TAG = GifViewPagerFragment.class.getSimpleName();
    private ViewPager mViewPager;
    private GifViewPagerAdapter mAdapter;

    public static GifViewPagerFragment newInstance() {
        return new GifViewPagerFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (mAdapter == null) {
            mAdapter = new GifViewPagerAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);

        mViewPager = view.findViewById(R.id.gif_view_pager);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);

        mViewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(mViewPager);

        return view;
    }
}
