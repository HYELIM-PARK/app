package com.example.sample;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines")
    Call<ResponseBody> getData(

            @Query("country") String country,
            @Query("apiKey") String apiKey
    );
}
