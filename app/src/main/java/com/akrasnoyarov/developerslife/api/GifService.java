package com.akrasnoyarov.developerslife.api;

import android.util.Log;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GifService {
    private static final String TAG = GifService.class.getSimpleName();
    private static final String BASE_URL = "https://developerslife.ru/";
    private static GifService sInstance;
    private DeveloperLifesService mService;
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

    public void loadImage() {
        Log.i(TAG, "loadImage");
        Call<GifImage> call = mService.loadImage("random");
        Log.d(TAG, call.request().toString());
        call.enqueue(new Callback<GifImage>() {
            @Override
            public void onResponse(Call<GifImage> call, Response<GifImage> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse is successful");
                    GifImage gifImage = response.body();
                    mResponseListener.onResponse(gifImage);
                } else {
                    Log.i(TAG, "onResponse is not successful");
                    mResponseListener.onFailure();
                }
            }

            @Override
            public void onFailure(Call<GifImage> call, Throwable t) {
                Log.i(TAG, "onFailure");
                mResponseListener.onFailure();
            }
        });
    }

}
