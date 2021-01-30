package com.akrasnoyarov.developerslife.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

//http://developerslife.ru/<раздел>/<номер страницы>?json=true

public interface DeveloperLifesService {
    @GET("{section}?json=true")
    Call<GifImage> loadImage(@Path("section") String section);
}
