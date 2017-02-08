package com.example.lukasz.tumblrbrowser.adapters;

import com.example.lukasz.tumblrbrowser.network.jackson.PostJson;
import com.example.lukasz.tumblrbrowser.network.jackson.ReadResponseJson;

import java.util.ArrayList;

/**
 * Created by lukasz on 07.02.17.
 */

public interface IPostProvider {

    int getCount();
    void searchUserPosts(String user);
    void loadPosts(ReadResponseJson json);
    ArrayList<PostJson> getPosts();

}
