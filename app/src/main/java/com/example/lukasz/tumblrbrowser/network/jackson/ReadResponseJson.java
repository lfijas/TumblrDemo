package com.example.lukasz.tumblrbrowser.network.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by lukasz on 07.02.17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReadResponseJson {

    ArrayList<PostJson> posts;

    public ArrayList<PostJson> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<PostJson> posts) {
        this.posts = posts;
    }
}
