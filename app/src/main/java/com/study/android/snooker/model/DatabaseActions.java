package com.study.android.snooker.model;

import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.model.Info.RankInfo;

import org.androidannotations.annotations.EBean;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;

@EBean
public class DatabaseActions implements DatabaseActionsInterface{

    @Override
    public <T extends RealmObject> void writeToRealm(List<T> rankInfos) {
        Realm realm = Realm.getDefaultInstance();
        for (T rankInfo : rankInfos) {
            realm.executeTransaction(transaction -> realm.copyToRealmOrUpdate(rankInfo));
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
