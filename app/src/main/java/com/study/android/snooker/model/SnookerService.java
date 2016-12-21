package com.study.android.snooker.model;

import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.model.Info.RankInfo;

import java.util.List;

import rx.Observable;

import retrofit2.http.GET;

public interface SnookerService {

    @GET("/?rt=MoneyRankings&s=2016")
    Observable<List<RankInfo>> getRanks();

    @GET("/?t=10&st=p&s=2016")
    Observable<List<PlayerInfo>> getPlayers();
}
