package com.example.lukasz.tumblrbrowser.di;

import com.example.lukasz.tumblrbrowser.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lukasz on 13.03.2017.
 */
@Singleton
@Component(modules={NetworkingModule.class})
public interface NetworkingComponent {
    void inject(MainActivity activity);
}
