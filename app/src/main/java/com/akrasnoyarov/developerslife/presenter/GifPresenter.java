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
        onLoadFailed();
    }

    private void checkPrevButtonVisibility() {
        if (mCurrentImageIndex == 0) {
            mGifFragmentView.setPrevButtonVisibility(View.GONE);
        } else if (mAllImages.size() > 1) {
            mGifFragmentView.setPrevButtonVisibility(View.VISIBLE);
        }
    }

    private void loadImageFromCache() {
        mGifFragmentView.setImage(mAllImages.get(mCurrentImageIndex));
    }

    public void onNextButtonClicked() {
        // if no images were loaded OR requested image was loaded before => reload from cache
        mGifFragmentView.showProgressBar(View.VISIBLE);
        if (mAllImages.size() != 0 && mCurrentImageIndex != mAllImages.size() - 1) {
            mCurrentImageIndex++;
            loadImageFromCache();
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
        mService.loadImage();
    }

    public void onLoadFailed() {
        mGifFragmentView.showProgressBar(View.GONE);
        mGifFragmentView.enableReloadButton(true);
        mGifFragmentView.showErrorImage();
    }

    public void onConfigurationChange() {
        checkPrevButtonVisibility();
        loadImageFromCache();
    }
}
