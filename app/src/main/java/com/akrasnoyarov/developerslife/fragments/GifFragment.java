package com.akrasnoyarov.developerslife.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.akrasnoyarov.developerslife.R;
import com.akrasnoyarov.developerslife.api.GifImage;
import com.akrasnoyarov.developerslife.presenter.GifPresenter;
import com.akrasnoyarov.developerslife.view.GifFragmentView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class GifFragment extends Fragment implements GifFragmentView {
    private static final String TAG = GifFragment.class.getSimpleName();
    private ImageView mGifImageView;
    private ImageButton mNextImageButton, mPrevImageButton, mReloadImageButton;
    private GifPresenter mPresenter;

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
        mPresenter = new GifPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gif, container, false);
        initViews(view);

        return view;
    }

    private void initViews(View view) {
        mGifImageView = view.findViewById(R.id.gif_image_view);
        mNextImageButton = view.findViewById(R.id.next_button);
        mPrevImageButton = view.findViewById(R.id.prev_button);
        mReloadImageButton = view.findViewById(R.id.reload_button);
        mPrevImageButton.setVisibility(View.GONE);

        mNextImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onNextButtonClicked();
            }
        });

        mPrevImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onPrevButtonClicked();
            }
        });

        mReloadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onReloadButtonClicked();
            }
        });
    }

    @Override
    public void setImage(GifImage image) {
        Glide.with(getActivity())
                .load(image.getGifURL())
                .transition(DrawableTransitionOptions.withCrossFade())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(mGifImageView);
    }
}
