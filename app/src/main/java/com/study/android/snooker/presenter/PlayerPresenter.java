package com.study.android.snooker.presenter;

import android.util.Log;

import com.study.android.snooker.model.DatabaseActions;
import com.study.android.snooker.model.DatabaseActionsInterface;
import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.model.Snooker;
import com.study.android.snooker.model.SnookerService;
import com.study.android.snooker.view.PlayerView;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PlayerPresenter implements PlayerPresenterInterface{
    private static final int IndexOfTheFirstElement = 0;
    private final SnookerService snooker = new Snooker();
    private final PlayerView playerView;
    private final DatabaseActionsInterface mActions;
    private static final String TAG = "My logs";

    public PlayerPresenter(PlayerView view) {
        playerView = view;
        mActions = new DatabaseActions();
    }

    @Override
    public void getPlayerData(int p) {
        if (playerView.isOnline()) {
            Observable<List<PlayerInfo>> dataObservable = snooker.getPlayer(p);
            dataObservable.subscribeOn(Schedulers.computation())
                    .observeOn(Schedulers.computation())
                    .doOnNext(playerInfos -> mActions.writeToRealmPlayer(playerInfos, p))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(playerInfos -> playerView.setPlayer(playerInfos.get(IndexOfTheFirstElement)));
        } else playerView.noConnection();

        playerView.swipeBarDisable();
    }
    @Override
    public void getPlayerDataFromRealm(int p) {
        Log.v(TAG, "getPlayerFromRealm");

        if (mActions.hasPlayer(p))
            playerView.setPlayer(mActions.getPlayer(p).get(IndexOfTheFirstElement));
        else getPlayerData(p);
    }
}
