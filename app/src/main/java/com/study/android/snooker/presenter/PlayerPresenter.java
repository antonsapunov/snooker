package com.study.android.snooker.presenter;

import com.study.android.snooker.model.DatabaseActions;
import com.study.android.snooker.model.DatabaseActionsInterface;
import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.model.Snooker;
import com.study.android.snooker.model.SnookerService;
import com.study.android.snooker.view.PlayerView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@EBean
public class PlayerPresenter implements PlayerPresenterInterface{
    @Bean(Snooker.class)
    SnookerService mSnooker;
    @Bean(DatabaseActions.class)
    DatabaseActionsInterface mActions;
    private static final int INDEX_OF_FIRST_ELEMENT = 0;
    private PlayerView mPlayerView;

    @Override
    public void setView(PlayerView view) {
        mPlayerView = view;
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
