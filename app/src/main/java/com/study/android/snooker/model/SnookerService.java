package com.study.android.snooker.model;

import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.model.Info.RankInfo;

import java.util.List;

import rx.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SnookerService {

    @GET("/?rt=MoneyRankings&s=2016")
    Observable<List<RankInfo>> getRanks();

    @GET("/")
    Observable<PlayerInfo> getPlayer(@Query("p") int p);
}
