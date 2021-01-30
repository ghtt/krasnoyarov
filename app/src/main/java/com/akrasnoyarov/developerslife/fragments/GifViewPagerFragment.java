package com.akrasnoyarov.developerslife.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.akrasnoyarov.developerslife.R;
import com.akrasnoyarov.developerslife.adapters.GifViewPagerAdapter;

public class GifViewPagerFragment extends Fragment {
    private static final String SECTION_RANDOM = "random";
    private ViewPager mViewPager;

    public static GifViewPagerFragment newInstance() {
        return new GifViewPagerFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);

        FragmentManager fm = getChildFragmentManager();
        mViewPager = view.findViewById(R.id.gif_view_pager);
        mViewPager.setAdapter(new GifViewPagerAdapter(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));

        return view;
    }
}
