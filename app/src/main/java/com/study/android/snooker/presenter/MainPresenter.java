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
    private static final String TAG = "myLogs";
    private final SnookerService snooker = new Snooker();
    private final MainView mainView;

    public MainPresenter(MainView view) {
        mainView = view;
    }

    @Override
    public void getRankData() {
        Observable.zip(
                snooker.getRanks().subscribeOn(Schedulers.newThread()),
                snooker.getPlayers().subscribeOn(Schedulers.newThread()),
                (rankInfos, playerInfos) -> {
                    for(RankInfo rankInfo : rankInfos){
                        for(PlayerInfo playerInfo : playerInfos){
                            if(rankInfo.getPlayerID().equals(playerInfo.getID())) {
                                if(playerInfo.getSurnameFirst()) {
                                    rankInfo.setName(playerInfo.getLastName() + " "
                                            + playerInfo.getFirstName());
                                }
                                else rankInfo.setName(playerInfo.getFirstName() + " "
                                        + playerInfo.getLastName());
                                break;
                            }
                        }
                    }
                    return rankInfos;
                }
        ).observeOn(AndroidSchedulers.mainThread())
        .subscribe(mainView::setRanks);
    }
}
