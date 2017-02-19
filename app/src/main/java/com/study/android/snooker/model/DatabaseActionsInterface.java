package com.study.android.snooker.model;

import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.model.Info.RankInfo;

import java.util.List;

import io.realm.RealmObject;

public interface DatabaseActionsInterface {
    <T extends RealmObject> void writeToRealm(List<T> info);

    List<RankInfo> getRanks();
    List<PlayerInfo> getPlayer(int iD);

    boolean hasRanks();
    boolean hasPlayer(int iD);
}
