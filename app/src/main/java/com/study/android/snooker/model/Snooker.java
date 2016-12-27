package com.study.android.snooker.model;

import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.model.Info.RankInfo;

import java.util.List;

import retrofit2.http.Query;
import rx.Observable;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Snooker implements SnookerService{
    static final String P = "p";
    private static final String HTTP_API_SNOOKER_ORG = "http://api.snooker.org/";
    private SnookerService snookerService;

    public Snooker() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(HTTP_API_SNOOKER_ORG)
                .build();
        snookerService = retrofit.create(SnookerService.class);
    }

    @Override
    public Observable<List<RankInfo>> getRanks() {
        return snookerService.getRanks();
    }

    @Override
    public Observable<List<PlayerInfo>> getPlayers() {
        return snookerService.getPlayers();
    }

    @Override
    public Observable<List<PlayerInfo>> getPlayer(@Query(P) int p) {
        return snookerService.getPlayer(p);
    }


}
