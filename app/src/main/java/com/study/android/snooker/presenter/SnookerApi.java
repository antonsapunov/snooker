package com.study.android.snooker.presenter;

import com.study.android.snooker.model.PlayerClass;
import com.study.android.snooker.model.RankClass;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SnookerApi {
    @GET("/?rt=MoneyRankings&s=2016")
    Call<List<RankClass>> getData();

    @GET("/")
    Call<List<PlayerClass>> getPlayer(@Query("p") int p);
}
