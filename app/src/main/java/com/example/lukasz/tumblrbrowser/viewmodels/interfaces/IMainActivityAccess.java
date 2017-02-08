package com.example.lukasz.tumblrbrowser.viewmodels.interfaces;

/**
 * Created by lukasz on 05.02.17.
 */

public interface IMainActivityAccess {

    void openPost(String id, String photoCaption, String photoUrl);
    void loadPosts();

}
