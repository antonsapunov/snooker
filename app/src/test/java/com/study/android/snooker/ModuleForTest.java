package com.study.android.snooker;

import com.study.android.snooker.dagger.TopPlayersModule;
import com.study.android.snooker.model.DatabaseActionsInterface;
import com.study.android.snooker.model.SnookerService;
import com.study.android.snooker.presenter.PlayerPresenterInterface;
import com.study.android.snooker.presenter.TopPlayersPresenterInterface;
import com.study.android.snooker.view.PlayerActivityTest;
import com.study.android.snooker.view.TopActivityTest;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleForTest extends TopPlayersModule {

    @Provides
    @Singleton
    public TopPlayersPresenterInterface providesTopPresenter(SnookerService service, DatabaseActionsInterface actions) {
        return TopActivityTest.mPresenter;
    }

    @Provides
    @Singleton
    public PlayerPresenterInterface providesPlayerPresenter(SnookerService service, DatabaseActionsInterface actions) {
        return PlayerActivityTest.mPresenter;
    }
}

