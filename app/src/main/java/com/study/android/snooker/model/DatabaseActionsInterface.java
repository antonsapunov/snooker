package com.study.android.snooker.model;

import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.model.Info.RankInfo;

import java.util.List;

public interface DatabaseActionsInterface {
    void writeToRealm(List<RankInfo> rankInfos);
    void writeToRealmPlayer(List<PlayerInfo> playerInfos);

    List<RankInfo> getRanks();
    List<PlayerInfo> getPlayer(int iD);

    boolean hasRanks();
    boolean hasPlayer(int iD);
}
