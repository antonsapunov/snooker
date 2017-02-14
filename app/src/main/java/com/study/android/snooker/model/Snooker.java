package com.study.android.snooker.model;

import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.model.Info.RankInfo;

import org.androidannotations.annotations.EBean;

import java.util.List;

import retrofit2.http.Query;
import rx.Observable;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@EBean
public class Snooker implements SnookerService{
    static final String PLAYER_ID = "p";
    private static final String HTTP_API_SNOOKER_ORG = "http://api.snooker.org/";
    private SnookerService mSnookerService;

    Snooker() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(HTTP_API_SNOOKER_ORG)
                .build();
        mSnookerService = retrofit.create(SnookerService.class);
    }

    @Override
    public Observable<List<RankInfo>> getRanks() {
        return mSnookerService.getRanks();
    }

    @Override
    public Observable<List<PlayerInfo>> getPlayers() {
        return mSnookerService.getPlayers();
    }

    @Override
    public Observable<List<PlayerInfo>> getPlayer(@Query(PLAYER_ID) int player_id) {
        return mSnookerService.getPlayer(player_id);
    }
}
