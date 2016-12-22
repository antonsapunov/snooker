package com.study.android.snooker.presenter;

import android.util.Log;

import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.model.Snooker;
import com.study.android.snooker.model.SnookerService;
import com.study.android.snooker.view.PlayerView;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PlayerPresenter implements PlayerPresenterInterface{
    private static final String TAG = "myLogs";
    private final SnookerService snooker = new Snooker();
    private final PlayerView playerView;

    public PlayerPresenter(PlayerView view) {
        playerView = view;
    }

    @Override
    public void getPlayerData(int p) {
        Observable<List<PlayerInfo>> dataObservable = snooker.getPlayer(p);
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
                            public void onNext(List<PlayerInfo> playerInfos) {
                                Log.d(TAG, "size = " + playerInfos.size());
                                playerView.setPlayer(playerInfos.get(0));
                            }
                        });
    }
}
