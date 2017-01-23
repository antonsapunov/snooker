package com.study.android.snooker.model;

import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.model.Info.RankInfo;

import java.util.List;

import io.realm.Realm;

public class DatabaseActions implements DatabaseActionsInterface{

    @Override
    public void writeToRealm(List<RankInfo> rankInfos) {
        Realm realm = Realm.getDefaultInstance();
        for (RankInfo rankInfo : rankInfos) {
            realm.executeTransaction(transaction -> realm.copyToRealmOrUpdate(rankInfo));
        }
        realm.close();
    }

    @Override
    public void writeToRealmPlayer(List<PlayerInfo> playerInfos) {
        Realm realm = Realm.getDefaultInstance();
        for (PlayerInfo playerInfo : playerInfos) {
            realm.executeTransaction(transaction -> realm.copyToRealmOrUpdate(playerInfo));
        }
        realm.close();
    }

    @Override
    public boolean hasRanks() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(RankInfo.class).count()!= 0;
    }

    @Override
    public boolean hasPlayer(int iD) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(PlayerInfo.class).equalTo("iD", iD).count()!= 0;
    }

    @Override
    public List<RankInfo> getRanks() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(RankInfo.class).findAll();
    }

    @Override
    public List<PlayerInfo> getPlayer(int iD) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(PlayerInfo.class).equalTo("iD", iD).findAll();
    }
}
