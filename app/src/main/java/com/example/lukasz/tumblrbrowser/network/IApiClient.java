package com.example.lukasz.tumblrbrowser.network;

import com.example.lukasz.tumblrbrowser.adapters.IPostProvider;

/**
 * Created by lukasz on 07.02.17.
 */

public interface IApiClient {

    void readPosts(String user, IPostProvider postProvider);

}
