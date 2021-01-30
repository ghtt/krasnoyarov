package com.akrasnoyarov.developerslife.presenter;

import android.util.Log;
import android.view.View;

import com.akrasnoyarov.developerslife.api.GifImage;
import com.akrasnoyarov.developerslife.api.GifService;
import com.akrasnoyarov.developerslife.view.GifFragmentView;

import java.util.ArrayList;
import java.util.List;

public class GifPresenter implements GifService.ServiceResponseListener {
    private GifService mService;
    private GifFragmentView mGifFragmentView;
    private boolean isPrevButtonVisible = false;
    private List<GifImage> mAllImages = new ArrayList<>();
    private int mCurrentImageIndex = 0;

    public GifPresenter(GifFragmentView view) {
        mGifFragmentView = view;
        mService = GifService.getInstance();
        mService.setResponseListener(this);
    }

    public void onNextButtonClicked() {
        Log.i("myLogs", "mAllImages.size = " + mAllImages.size() + ", current = " + mCurrentImageIndex);
        // if no images were loaded OR requested image was loaded before => reload from cache
        if (mAllImages.size() != 0 && mCurrentImageIndex != mAllImages.size() - 1) {
            mCurrentImageIndex++;
            mGifFragmentView.setImage(mAllImages.get(mCurrentImageIndex));
        } else {
            mService.loadImage();
        }
        checkPrevButtonVisibility();
    }

    public void onPrevButtonClicked() {
        if (mCurrentImageIndex != 0) {
            mCurrentImageIndex--;
            mGifFragmentView.setImage(mAllImages.get(mCurrentImageIndex));
        }
        checkPrevButtonVisibility();
    }

    public void onReloadButtonClicked() {
    }


    @Override
    public void onResponse(GifImage image) {
        mAllImages.add(image);
        mGifFragmentView.setImage(image);
        if (mAllImages.size() > 1) {
            mCurrentImageIndex++;
        }
        checkPrevButtonVisibility();
    }

    @Override
    public void onFailure() {
        Log.i("myLogs", "FAILURE");
    }

    private void checkPrevButtonVisibility() {
        if (mCurrentImageIndex == 0) {
            mGifFragmentView.setPrevButtonVisibility(View.GONE);
        } else if (mAllImages.size() > 1) {
            mGifFragmentView.setPrevButtonVisibility(View.VISIBLE);
        }

    }
}
