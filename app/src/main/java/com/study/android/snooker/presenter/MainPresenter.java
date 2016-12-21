package com.study.android.snooker.presenter;


import android.util.Log;

import com.study.android.snooker.model.*;
import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.model.Info.RankInfo;
import com.study.android.snooker.view.MainView;


import java.util.List;

import rx.Observable;
import rx.Subscriber;
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

        Observable<List<RankInfo>> dataObservable = snooker.getRanks();

        dataObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<RankInfo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<RankInfo> rankInfos) {
                        getPlayerData();
                        mainView.setRanks(rankInfos);

                    }
                });
    }

    private void getPlayerData() {
        Observable<List<PlayerInfo>> dataObservable = snooker.getPlayers();
        dataObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<PlayerInfo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<PlayerInfo> playerInfo) {
                        mainView.setPlayers(playerInfo);
                    }
                });
    }
}
