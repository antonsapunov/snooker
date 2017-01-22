package com.study.android.snooker.presenter;

import android.util.Log;

import com.study.android.snooker.model.*;
import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.model.Info.RankInfo;
import com.study.android.snooker.view.MainView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter implements MainPresenterInterface{
    private final SnookerService snooker = new Snooker();
    private final MainView mainView;
    private final DatabaseActionsInterface mActions;
    private static final String TAG = "My logs";

    public MainPresenter(MainView view) {
        mainView = view;
        mActions = new DatabaseActions();
    }

    @Override
    public void getRankData() {
        if (mainView.isOnline()) {
            Observable.zip(
                    snooker.getRanks().subscribeOn(Schedulers.io()),
                    snooker.getPlayers().subscribeOn(Schedulers.io()),
                    (rankInfos, playerInfos) -> {
                        for (RankInfo rankInfo : rankInfos) {
                            for (PlayerInfo playerInfo : playerInfos) {
                                if (rankInfo.getPlayerID().equals(playerInfo.getID())) {
                                    if (playerInfo.getSurnameFirst()) {
                                        rankInfo.setName(playerInfo.getLastName() + " "
                                                + playerInfo.getFirstName());
                                    } else rankInfo.setName(playerInfo.getFirstName() + " "
                                            + playerInfo.getLastName());
                                    break;
                                }
                            }
                        }
                        return rankInfos;
                    }
            ).observeOn(Schedulers.computation())
                    .doOnNext(mActions::writeToRealm)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(mainView::setRanks);
        } else mainView.noConnection();

        mainView.swipeBarDisable();
    }

    @Override
    public void getRankDataFromRealm(){
        Log.v(TAG, "getRanksFromRealm");

        if (mActions.hasRanks())
            mainView.setRanks(mActions.getRanks());
        else getRankData();
    }
}