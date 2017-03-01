package com.study.android.snooker;

import com.study.android.snooker.dagger.AppComponent;
import com.study.android.snooker.dagger.DaggerAppComponent;

public class AppForTest extends App {
    @Override
    public void onCreate() {
        super.onCreate();
        buildComponent();
    }

    AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .topPlayersModule(new ModuleForTest())
                .build();
    }

    @Override
    void initRealm() {
        //nothing
    }
}
