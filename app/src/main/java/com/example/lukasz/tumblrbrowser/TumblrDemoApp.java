package com.example.lukasz.tumblrbrowser;

import android.app.Application;

import com.example.lukasz.tumblrbrowser.di.DaggerNetworkingComponent;
import com.example.lukasz.tumblrbrowser.di.NetworkingComponent;
import com.example.lukasz.tumblrbrowser.di.NetworkingModule;

/**
 * Created by lukasz on 13.03.2017.
 */

public class TumblrDemoApp extends Application {

    private NetworkingComponent mNetworkingComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetworkingComponent = DaggerNetworkingComponent.builder().networkingModule(new
                NetworkingModule()).build();

    }

    public NetworkingComponent getNetworkingComponent() {
        return mNetworkingComponent;
    }

}
