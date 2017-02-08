package com.example.lukasz.tumblrbrowser.viewmodels;

import com.example.lukasz.tumblrbrowser.activities.PostDetailsActivity;
import com.example.lukasz.tumblrbrowser.viewmodels.interfaces.IPostDetailsActivityAccess;

/**
 * Created by lukasz on 05.02.17.
 */

public class PostDetailsActivityViewModel {

    private IPostDetailsActivityAccess mActivityAccess;

    public PostDetailsActivityViewModel(IPostDetailsActivityAccess access) {
        mActivityAccess = access;
    }

}
