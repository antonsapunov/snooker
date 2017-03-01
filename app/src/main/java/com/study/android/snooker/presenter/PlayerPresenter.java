package com.study.android.snooker.presenter;

import com.study.android.snooker.model.DatabaseActionsInterface;
import com.study.android.snooker.model.SnookerService;
import com.study.android.snooker.view.PlayerView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PlayerPresenter implements PlayerPresenterInterface{
    private static final int INDEX_OF_FIRST_ELEMENT = 0;
    private SnookerService mSnooker;
    private DatabaseActionsInterface mActions;
    private PlayerView mPlayerView;


    public PlayerPresenter (SnookerService snooker, DatabaseActionsInterface actions){
        mSnooker = snooker;
        mActions = actions;
    }

    @Override
    public void setView(PlayerView view) {
        mPlayerView = view;
    }

    @Override
    public void getPlayerData(int player_id) {
        if (mPlayerView.isOnline()) {
            mSnooker.getPlayer(player_id)
                    .doOnNext(mActions::writeToRealm)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(playerInfos -> mPlayerView.setPlayer(playerInfos.get(INDEX_OF_FIRST_ELEMENT)),
                            throwable -> mPlayerView.error());
        } else {
            mPlayerView.noConnection();
            mPlayerView.progressBarDisable();
        }
        mPlayerView.swipeBarDisable();
    }

    @Override
    public void getPlayerDataFromRealm(int player_id) {
        if (mActions.hasPlayer(player_id)) {
            mPlayerView.setPlayer(mActions.getPlayer(player_id).get(INDEX_OF_FIRST_ELEMENT));
        }
        else {
            getPlayerData(player_id);
        }
    }

}
