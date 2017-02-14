package com.study.android.snooker.presenter;

import com.study.android.snooker.view.PlayerView;

public interface PlayerPresenterInterface {
    void getPlayerData(int player_id);
    void getPlayerDataFromRealm(int player_id);
    void setView(PlayerView view);
}
