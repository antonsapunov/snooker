package com.study.android.snooker.model;

import android.util.Log;

import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.model.Info.RankInfo;

import java.util.List;

import retrofit2.http.Query;
import rx.Observable;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Snooker implements SnookerService{
    private static final String TAG = "myLogs";
    private SnookerService snookerService;

    public Snooker() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://api.snooker.org/")
                .build();
        snookerService = retrofit.create(SnookerService.class);
        Log.d(TAG, snookerService.getRanks().toString());
    }

    @Override
    public Observable<List<RankInfo>> getRanks() {
        return snookerService.getRanks();
    }

    @Override
    public Observable<PlayerInfo> getPlayer(@Query("p") int p) {
        return snookerService.getPlayer(p);
    }

}
