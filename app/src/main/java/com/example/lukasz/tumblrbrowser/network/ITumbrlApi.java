package com.example.lukasz.tumblrbrowser.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lukasz on 07.02.17.
 */

public interface ITumbrlApi {

    @GET("read/json")
    Call<ResponseBody> readUserPosts(@Query("type") String postType);

}
