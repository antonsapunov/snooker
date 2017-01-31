package com.study.android.snooker.view;

import com.study.android.snooker.model.Info.RankInfo;

import java.util.List;
//TODO Naming must be more descriptive.
public interface MainView {
    void setRanks(List<RankInfo> rank);
    void noConnection();
    void swipeBarDisable();
    void progressBarDisable();
    void error();
    boolean isOnline();
}
