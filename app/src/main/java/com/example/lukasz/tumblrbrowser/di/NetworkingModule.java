package com.example.lukasz.tumblrbrowser.di;

import com.example.lukasz.tumblrbrowser.network.ApiClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lukasz on 13.03.2017.
 */

@Module
public class NetworkingModule {

    @Provides
    @Singleton
    ApiClient provideApiClient() {
        return new ApiClient();
    }

}
