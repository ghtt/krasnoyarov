package com.akrasnoyarov.developerslife.presenter;

import android.util.Log;

import com.akrasnoyarov.developerslife.api.GifImage;
import com.akrasnoyarov.developerslife.api.GifService;
import com.akrasnoyarov.developerslife.view.GifFragmentView;

public class GifPresenter implements GifService.ServiceResponseListener {
    private GifService mService;
    private GifFragmentView mGifFragmentView;

    public GifPresenter(GifFragmentView view) {
        mGifFragmentView = view;
        mService = GifService.getInstance();
        mService.setResponseListener(this);
    }

    public void onNextButtonClicked() {
        mService.loadImage();
    }

    public void onPrevButtonClicked() {
    }

    public void onReloadButtonClicked() {
    }


    @Override
    public void onResponse(GifImage image) {
        mGifFragmentView.setImage(image);
    }

    @Override
    public void onFailure() {
        Log.i("myLogs", "FAILURE");
    }
}
