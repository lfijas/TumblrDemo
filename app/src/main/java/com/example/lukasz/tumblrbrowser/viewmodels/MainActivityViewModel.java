package com.example.lukasz.tumblrbrowser.viewmodels;

import com.example.lukasz.tumblrbrowser.adapters.IPostClickListener;
import com.example.lukasz.tumblrbrowser.adapters.IPostProvider;
import com.example.lukasz.tumblrbrowser.network.IApiClient;
import com.example.lukasz.tumblrbrowser.network.jackson.PostJson;
import com.example.lukasz.tumblrbrowser.network.jackson.ReadResponseJson;
import com.example.lukasz.tumblrbrowser.viewmodels.interfaces.IMainActivityAccess;

import java.util.ArrayList;

/**
 * Created by lukasz on 05.02.17.
 */

public class MainActivityViewModel implements IPostProvider, IPostClickListener {

    private IMainActivityAccess mActivityAccess;
    private IApiClient mApiClient;

    private ArrayList<PostJson> mData = new ArrayList<>();

    public MainActivityViewModel(IMainActivityAccess activityAccess, IApiClient apiClient) {
        mActivityAccess = activityAccess;
        mApiClient = apiClient;
    }

    @Override
    public void postClicked(String id, String photoCaption, String photoUrl) {
        mActivityAccess.openPost(id, photoCaption, photoUrl);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public void searchUserPosts(String user) {
        mApiClient.readPosts(user, this);

    }

    @Override
    public void loadPosts(ReadResponseJson json) {
        mData = json.getPosts();
        mActivityAccess.loadPosts();
    }

    @Override
    public ArrayList<PostJson> getPosts() {
        return mData;
    }
}
