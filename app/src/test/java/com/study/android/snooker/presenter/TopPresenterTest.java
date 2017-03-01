package com.study.android.snooker.presenter;

import com.study.android.snooker.AppForTest;
import com.study.android.snooker.BuildConfig;
import com.study.android.snooker.RxSchedulersOverrideRule;
import com.study.android.snooker.model.DatabaseActionsInterface;
import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.model.Info.RankInfo;
import com.study.android.snooker.model.SnookerService;
import com.study.android.snooker.view.TopPlayersView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, application = AppForTest.class)
public class TopPresenterTest {
    private TopPlayersPresenter mPresenter;
    private TopPlayersPresenter spyPresenter;
    private List<RankInfo> rankList;

    @Rule
    public final RxSchedulersOverrideRule mRxRule = new RxSchedulersOverrideRule();
    @Mock
    TopPlayersView mTopPlayersView;
    @Mock
    SnookerService mSnookerService;
    @Mock
    DatabaseActionsInterface mDatabaseActionsInterface;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mPresenter = new TopPlayersPresenter(mSnookerService, mDatabaseActionsInterface);
        mPresenter.setView(mTopPlayersView);
        spyPresenter = spy(mPresenter);
        RankInfo rankInfo = new RankInfo();
        rankInfo.setPlayerID(1);
        PlayerInfo playerInfo = new PlayerInfo();
        playerInfo.setiD(1);
        playerInfo.setSurnameFirst(true);
        rankList = new ArrayList<>();
        rankList.add(rankInfo);
        List<PlayerInfo> playerList = new ArrayList<>();
        playerList.add(playerInfo);
        when(mSnookerService.getRanks()).thenReturn(Observable.just(rankList));
        when(mSnookerService.getPlayers()).thenReturn(Observable.just(playerList));
    }

    @Test
    public void callMethodsWithInternet() {
        when(mTopPlayersView.isOnline()).thenReturn(true);
        mPresenter.getRankData();
        verify(mSnookerService).getRanks();
        verify(mSnookerService).getPlayers();
        verify(mDatabaseActionsInterface).writeToRealm(rankList);
        verify(mTopPlayersView).progressBarDisable();
        verify(mTopPlayersView).setRanks(rankList);
    }

    @Test
    public void callMethodsWithoutInternet() {
        when(mTopPlayersView.isOnline()).thenReturn(false);
        mPresenter.getRankData();
        verify(mTopPlayersView).noConnection();
        verify(mTopPlayersView).progressBarDisable();
    }

    @Test
    public void callSwipeBarDisable() {
        mPresenter.getRankData();
        verify(mTopPlayersView).swipeBarDisable();
    }

    @Test
    public void callGetRankDataWithoutDatabase() {
        when(mDatabaseActionsInterface.hasRanks()).thenReturn(false);
        spyPresenter.getRankDataFromRealm();
        verify(spyPresenter).getRankData();
    }

    @Test
    public void callGetRankDataWithDatabase() {
        when(mDatabaseActionsInterface.hasRanks()).thenReturn(true);
        mPresenter.getRankDataFromRealm();
        verify(mTopPlayersView).progressBarDisable();
        verify(mTopPlayersView).setRanks(any());
    }

    @Test
    public void errorCall() {
        String error = "error";
        when(mTopPlayersView.isOnline()).thenReturn(true);
        when(mSnookerService.getRanks()).thenReturn(Observable.error(new Throwable(error)));
        mPresenter.getRankData();
        verify(mTopPlayersView).error();
    }
}