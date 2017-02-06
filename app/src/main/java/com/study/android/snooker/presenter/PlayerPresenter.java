package com.study.android.snooker.presenter;

import com.study.android.snooker.model.DatabaseActions;
import com.study.android.snooker.model.DatabaseActionsInterface;
import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.model.Snooker;
import com.study.android.snooker.model.SnookerService;
import com.study.android.snooker.view.PlayerView;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class PlayerPresenter implements PlayerPresenterInterface{
    private static final int INDEX_OF_FIRST_ELEMENT = 0;
    private final SnookerService mSnooker = new Snooker();
    private final PlayerView mPlayerView;
    private final DatabaseActionsInterface mActions;

    public PlayerPresenter(PlayerView view) {
        mPlayerView = view;
        mActions = new DatabaseActions();
    }

    @Override
    public void getPlayerData(int player_id) {
        if (mPlayerView.isOnline()) {
            Observable<List<PlayerInfo>> dataObservable = mSnooker.getPlayer(player_id);
            dataObservable.subscribeOn(Schedulers.computation())
                    .observeOn(Schedulers.computation())
                    .doOnNext(mActions::writeToRealm)
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
        if (mActions.hasPlayer(player_id))
            mPlayerView.setPlayer(mActions.getPlayer(player_id).get(INDEX_OF_FIRST_ELEMENT));
        else getPlayerData(player_id);
    }
}
