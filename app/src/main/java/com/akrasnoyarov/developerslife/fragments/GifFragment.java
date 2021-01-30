package com.akrasnoyarov.developerslife.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    public static final String EXTRA_SECTION = "section";
    private String mSection;
    private ImageView mGifImageView;
    private ImageButton mNextImageButton, mPrevImageButton, mReloadImageButton;
    private TextView mDescriptionTextView;
    private ProgressBar mProgressBar;
    private GifPresenter mPresenter;

    /*
    Create a fragment by this static method. Could be used for adding arguments
     */
    public static GifFragment newInstance(String section) {
        Bundle bundle = new Bundle();
        bundle.putString(GifFragment.EXTRA_SECTION, section);

        GifFragment gifFragment = new GifFragment();
        gifFragment.setArguments(bundle);
        return gifFragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Restore fragment state after a configuration change
        if (savedInstanceState != null) {
            mPresenter.onConfigurationChange();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSection = getArguments().getString(EXTRA_SECTION);
        mPresenter = new GifPresenter(this, mSection);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gif, container, false);
        initViews(view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.init();
    }

    private void initViews(View view) {
        mGifImageView = view.findViewById(R.id.gif_image_view);
        mNextImageButton = view.findViewById(R.id.next_button);
        mPrevImageButton = view.findViewById(R.id.prev_button);
        mPrevImageButton.setVisibility(View.GONE);
        mReloadImageButton = view.findViewById(R.id.reload_button);
        enableReloadButton(false);
        mDescriptionTextView = view.findViewById(R.id.description_text_view);
        mProgressBar = view.findViewById(R.id.progress_bar);

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
        String url = image.getGifURL();
        String description = image.getDescription();
        clearImageDescription();

//        Log.i(TAG, "setImage in fragment " + description + " " + url);

        Glide.with(getActivity())
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_baseline_sync_problem_24)
                .listener(new RequestListener<Drawable>() {

                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        Log.i(TAG, "onLoadFailed");
                        showProgressBar(View.GONE);
                        mPresenter.onLoadFailed();
                        return false;
                    }


                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        Log.i(TAG, "onResourceReady");
                        enableReloadButton(false);
                        showProgressBar(View.GONE);
                        mGifImageView.setContentDescription(description);
                        mDescriptionTextView.setText(description);
                        return false;
                    }
                })
                .into(mGifImageView);
    }

    @Override
    public void setPrevButtonVisibility(int isVisible) {
        mPrevImageButton.setVisibility(isVisible);
    }

    @Override
    public void enableReloadButton(boolean isEnabled) {
        mReloadImageButton.setEnabled(isEnabled);
        mNextImageButton.setEnabled(!isEnabled);
        mPrevImageButton.setEnabled(!isEnabled);
    }

    @Override
    public void showProgressBar(int isVisible) {
        mProgressBar.setVisibility(isVisible);
    }

    @Override
    public void showErrorImage() {
//        Log.i(TAG, "showErrorImage");
        mGifImageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_sync_problem_24));

    }

    public void clearImageDescription() {
        mDescriptionTextView.setText("");
    }
}
