package com.akrasnoyarov.developerslife.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

//http://developerslife.ru/<раздел>/<номер страницы>?json=true

public interface DeveloperLifesService {
    @GET("random?json=true")
    Call<GifImage> loadRandomImage();

    @GET("{section}/{page}?json=true")
    Call<Result> loadImage(@Path("section") String section, @Path("page") String page);
}
