package com.study.android.snooker.view;

import com.study.android.snooker.model.Info.RankInfo;

import java.util.List;

public interface MainView {
    void setRanks(List<RankInfo> rank);
    boolean isOnline();
    void noConnection();
}
