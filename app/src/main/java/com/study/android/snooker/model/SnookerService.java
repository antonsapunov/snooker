package com.study.android.snooker.model;

import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.model.Info.RankInfo;

import java.util.List;

import rx.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SnookerService {

    String MONEY_RANKINGS_S_2016 = "/?rt=MoneyRankings&s=2016";
    String PLAYERS_S_2016 = "/?t=10&st=p&s=2016";

    @GET(MONEY_RANKINGS_S_2016)
    Observable<List<RankInfo>> getRanks();

    @GET(PLAYERS_S_2016)
    Observable<List<PlayerInfo>> getPlayers();

    @GET("/")
    Observable<List<PlayerInfo>> getPlayer(@Query(Snooker.PLAYER_ID) int player_id);
}
