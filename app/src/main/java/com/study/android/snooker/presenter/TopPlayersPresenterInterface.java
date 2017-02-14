package com.study.android.snooker.presenter;

import com.study.android.snooker.view.TopPlayersView;

public interface TopPlayersPresenterInterface {
    void getRankData();
    void getRankDataFromRealm();
    void setView(TopPlayersView view);
}
