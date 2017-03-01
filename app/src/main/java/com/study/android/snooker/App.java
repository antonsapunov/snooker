package com.study.android.snooker;

import android.app.Application;

import com.study.android.snooker.dagger.AppComponent;
import com.study.android.snooker.dagger.DaggerAppComponent;
import com.study.android.snooker.dagger.TopPlayersModule;

import io.realm.Realm;


public class App extends Application {
    private static AppComponent mComponent;
    public static AppComponent getComponent(){
        return mComponent;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
        mComponent = buildComponent();
    }

    AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .topPlayersModule(new TopPlayersModule())
                .build();
    }

    void initRealm() {
        Realm.init(this);
    }
}
