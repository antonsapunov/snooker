package com.study.android.snooker.dagger;


import com.study.android.snooker.view.PlayerActivity;
import com.study.android.snooker.view.TopPlayersActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {TopPlayersModule.class})
@Singleton
public interface AppComponent {
    void inject(TopPlayersActivity topPlayersActivity);
    void inject(PlayerActivity topPlayersActivity);
}
