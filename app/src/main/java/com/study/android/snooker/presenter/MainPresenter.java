package com.study.android.snooker.presenter;


import android.util.Log;

import com.study.android.snooker.model.*;
import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.model.Info.RankInfo;
import com.study.android.snooker.view.MainView;


import java.util.ArrayList;
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

        Log.d(TAG, "my " + dataObservable);
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
                        Log.d(TAG, "" +rankInfos.size());
                        mainView.setRanks(rankInfos);
                    }
                });
    }

    @Override
    public String getName(int playerID) {
        Observable<PlayerInfo> dataObservable = snooker.getPlayer(playerID);
        final String[] name = new String[1];
        dataObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(player -> player.getFirstName() + " "
                        + player.getMiddleName() + " "
                        + player.getLastName())
                .subscribe(player ->
                {
                    name[0] = player;
                });
        return name[0];
    }
}
