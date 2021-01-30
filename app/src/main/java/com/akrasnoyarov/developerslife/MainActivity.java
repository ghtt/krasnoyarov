package com.akrasnoyarov.developerslife;

import androidx.fragment.app.Fragment;

import com.akrasnoyarov.developerslife.activity.SingleFragmentActivity;
import com.akrasnoyarov.developerslife.fragments.GifFragment;
import com.akrasnoyarov.developerslife.fragments.GifViewPagerFragment;

public class MainActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return GifViewPagerFragment.newInstance();
    }
}