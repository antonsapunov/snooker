package com.study.android.snooker.view;

import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.model.Info.RankInfo;

import java.util.List;

public interface MainView {
    void setRanks(List<RankInfo> rank);
    void setPlayers(List<PlayerInfo> player);
}
