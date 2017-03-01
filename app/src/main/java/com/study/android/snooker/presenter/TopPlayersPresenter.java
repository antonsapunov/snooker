package com.study.android.snooker.presenter;

import com.study.android.snooker.model.*;
import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.model.Info.RankInfo;
import com.study.android.snooker.view.TopPlayersView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TopPlayersPresenter implements TopPlayersPresenterInterface {
    private SnookerService mSnooker;
    private DatabaseActionsInterface mActions;
    private TopPlayersView mTopPlayersView;

    public TopPlayersPresenter (SnookerService snooker, DatabaseActionsInterface actions){
        mSnooker = snooker;
        mActions = actions;
    }
    @Override
    public void setView(TopPlayersView view) {
        mTopPlayersView = view;
    }

    @Override
    public void getRankData() {
        if (mTopPlayersView.isOnline()) {
            Observable.zip(
                    mSnooker.getRanks(),
                    mSnooker.getPlayers(),
                    (rankInfos, playerInfos) -> {
                        for (RankInfo rankInfo : rankInfos) {
                            for (PlayerInfo playerInfo : playerInfos) {
                                if (rankInfo.getPlayerID().equals(playerInfo.getID())) {

                                    String playerName = String.format("%s %s",
                                            playerInfo.getFirstName(), playerInfo.getLastName());
                                    String inversedPlayerName = String.format("%s %s",
                                            playerInfo.getLastName(), playerInfo.getFirstName());
                                    rankInfo.setName(playerInfo.getSurnameFirst() ? inversedPlayerName : playerName);
                                    break;
                                }
                            }
                        }
                        return rankInfos;
                    }
            ).doOnNext(mActions::writeToRealm)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(bar -> mTopPlayersView.progressBarDisable())
                    .subscribe(mTopPlayersView::setRanks, throwable -> mTopPlayersView.error());
        } else {
            mTopPlayersView.noConnection();
            mTopPlayersView.progressBarDisable();
        }
        mTopPlayersView.swipeBarDisable();
    }

    @Override
    public void getRankDataFromRealm(){
        if (mActions.hasRanks()) {
            mTopPlayersView.setRanks(mActions.getRanks());
            mTopPlayersView.progressBarDisable();
        }
        else getRankData();
    }
}