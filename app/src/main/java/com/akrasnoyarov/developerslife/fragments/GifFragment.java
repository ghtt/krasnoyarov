package com.akrasnoyarov.developerslife.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.akrasnoyarov.developerslife.R;

public class GifFragment extends Fragment{
    private static final String TAG = GifFragment.class.getSimpleName();

    /*
    Create a fragment by this static method. Could be used for adding arguments
     */
    public static GifFragment newInstance() {
        return new GifFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gif, container, false);

        return view;
    }
}
