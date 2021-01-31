package com.akrasnoyarov.developerslife.view;

import android.graphics.drawable.Drawable;

import com.akrasnoyarov.developerslife.api.GifImage;

public interface GifFragmentView {
    void setImage(GifImage image);

    void setPrevButtonVisibility(int visibility);

    void enableReloadButton(boolean isEnabled);

    void showProgressBar(int isVisible);

    void showErrorImage();

    void showNoDataImage();
}
