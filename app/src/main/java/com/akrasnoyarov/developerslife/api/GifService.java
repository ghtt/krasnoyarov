package com.akrasnoyarov.developerslife.api;

import android.util.Log;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GifService {
    private static final String TAG = GifService.class.getSimpleName();
    private static final String BASE_URL = "https://developerslife.ru/";
    private static GifService sInstance;
    private final DeveloperLifesService mService;
    private ServiceResponseListener mResponseListener;

    public static GifService getInstance() {
        if (sInstance == null) {
            synchronized (GifService.class) {
                sInstance = new GifService();
            }
        }
        return sInstance;
    }

    public interface ServiceResponseListener {
        void onResponse(GifImage image);
        void onResponse(List<GifImage> images);
        void onFailure();
    }

    public void setResponseListener(ServiceResponseListener listener) {
        mResponseListener = listener;
    }

    private GifService() {
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        mService = retrofit.create(DeveloperLifesService.class);
    }

    public void loadRandomImage() {
        Call<GifImage> call = mService.loadRandomImage();
        Log.d(TAG, call.request().toString());
        call.enqueue(new Callback<GifImage>() {
            @Override
            public void onResponse(Call<GifImage> call, Response<GifImage> response) {
                if (response.isSuccessful()) {
                    GifImage gifImage = response.body();
                    mResponseListener.onResponse(gifImage);
                } else {
                    mResponseListener.onFailure();
                }
            }

            @Override
            public void onFailure(Call<GifImage> call, Throwable t) {
                mResponseListener.onFailure();
            }
        });
    }

    public void loadImages(String section, int page) {
//        Log.i(TAG, "loadRandomImage");
        Call<Result> call = mService.loadImage(section, String.valueOf(page));
        Log.d(TAG, call.request().toString());
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
//                    Log.i(TAG, "onResponse is successful");
                    Result result = response.body();
                    List<GifImage> gifImages = result.getResult();

                    mResponseListener.onResponse(gifImages);
                } else {
//                    Log.i(TAG, "loadImages onResponse is not successful");
                    mResponseListener.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
//                Log.i(TAG, "loadImages onFailure");
                mResponseListener.onFailure();
            }
        });
    }

}
