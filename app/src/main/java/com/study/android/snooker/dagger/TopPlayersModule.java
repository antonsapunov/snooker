package com.study.android.snooker.dagger;

import com.study.android.snooker.model.DatabaseActions;
import com.study.android.snooker.model.DatabaseActionsInterface;
import com.study.android.snooker.model.Snooker;
import com.study.android.snooker.model.SnookerService;
import com.study.android.snooker.presenter.PlayerPresenter;
import com.study.android.snooker.presenter.PlayerPresenterInterface;
import com.study.android.snooker.presenter.TopPlayersPresenter;
import com.study.android.snooker.presenter.TopPlayersPresenterInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TopPlayersModule {

    @Provides
    @Singleton
    public TopPlayersPresenterInterface providesTopPresenter(SnookerService service, DatabaseActionsInterface actions) {
        return new TopPlayersPresenter(service, actions);
    }

    @Provides
    @Singleton
    public PlayerPresenterInterface providesPlayerPresenter(SnookerService service, DatabaseActionsInterface actions) {
        return new PlayerPresenter(service, actions);
    }

    @Provides
    @Singleton
    DatabaseActionsInterface providesDatabaseActions() {
        return new DatabaseActions();
    }

    @Provides
    @Singleton
    SnookerService providesSnooker() {
        return new Snooker();
    }
}
