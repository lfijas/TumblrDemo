package com.example.lukasz.tumblrbrowser.network;

import com.example.lukasz.tumblrbrowser.adapters.IPostProvider;
import com.example.lukasz.tumblrbrowser.network.jackson.ReadResponseJson;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by lukasz on 07.02.17.
 */

public class ApiClient implements IApiClient {

    private final static String TUMBRL_RESPONSE_VAR_NAME = "var tumblr_api_read = ";

    public void readPosts(String user, final IPostProvider postProvider) {
        try {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://" + user + ".tumblr.com/api/").build();

            ITumbrlApi tumbrlApiClient = retrofit.create(ITumbrlApi.class);
            Call<ResponseBody> result = tumbrlApiClient.readUserPosts("photo");
            result.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        postProvider.loadPosts(getReadResponseJson(response));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    postProvider.reportNetworkError();
                    t.printStackTrace();
                }
            });
        } catch (IllegalArgumentException e) {
            postProvider.reportNetworkError();
            e.printStackTrace();
        }
    }

    private ReadResponseJson getReadResponseJson(Response<ResponseBody> response) throws IOException {
        ReadResponseJson readResponseJson = null;
        ResponseBody responseBody = response.body();
        if (responseBody != null) {
            String responseJsonString = responseBody.string().replace(TUMBRL_RESPONSE_VAR_NAME, "").trim();
            responseJsonString = responseJsonString.substring(0, responseJsonString.length() - 1);
            ObjectMapper objectMapper = new ObjectMapper();
            readResponseJson = objectMapper.readValue(responseJsonString, ReadResponseJson.class);
        }
        return readResponseJson;
    }

}
