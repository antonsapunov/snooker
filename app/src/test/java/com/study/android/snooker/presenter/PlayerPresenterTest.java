package com.study.android.snooker.presenter;

import com.study.android.snooker.AppForTest;
import com.study.android.snooker.BuildConfig;
import com.study.android.snooker.RxSchedulersOverrideRule;
import com.study.android.snooker.model.DatabaseActionsInterface;
import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.model.SnookerService;
import com.study.android.snooker.view.PlayerView;

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

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, application = AppForTest.class)
public class PlayerPresenterTest {
    private static final int ID = 1;
    private static final int INDEX_OF_FIRST_ELEMENT = 0;
    private PlayerPresenter mPresenter;
    private PlayerPresenter spyPresenter;
    private List<PlayerInfo> playerList;

    @Rule
    public final RxSchedulersOverrideRule mRxRule = new RxSchedulersOverrideRule();
    @Mock
    PlayerView mPlayerView;
    @Mock
    SnookerService mSnookerService;
    @Mock
    DatabaseActionsInterface mDatabaseActionsInterface;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mPresenter = new PlayerPresenter(mSnookerService, mDatabaseActionsInterface);
        mPresenter.setView(mPlayerView);
        spyPresenter = spy(mPresenter);
        PlayerInfo playerInfo = new PlayerInfo();
        playerInfo.setiD(ID);
        playerList = new ArrayList<>();
        playerList.add(playerInfo);
    }

    @Test
    public void callMethodsWithInternet() {
        when(mPlayerView.isOnline()).thenReturn(true);
        when(mSnookerService.getPlayer(ID)).thenReturn(Observable.just(playerList));
        mPresenter.getPlayerData(ID);
        verify(mSnookerService).getPlayer(ID);
        verify(mDatabaseActionsInterface).writeToRealm(playerList);
        verify(mPlayerView).setPlayer(playerList.get(INDEX_OF_FIRST_ELEMENT));
    }

    @Test
    public void callMethodsWithoutInternet() {
        when(mPlayerView.isOnline()).thenReturn(false);
        mPresenter.getPlayerData(ID);
        verify(mPlayerView).noConnection();
        verify(mPlayerView).progressBarDisable();
    }

    @Test
    public void callSwipeBarDisable() {
        mPresenter.getPlayerData(ID);
        verify(mPlayerView).swipeBarDisable();
    }

    @Test
    public void callGetPlayerDataWithoutDatabase() {
        when(mDatabaseActionsInterface.hasPlayer(ID)).thenReturn(false);
        spyPresenter.getPlayerDataFromRealm(ID);
        verify(spyPresenter).getPlayerData(ID);

    }

    @Test
    public void callGetPlayerDataWithDatabase() {
        when(mDatabaseActionsInterface.hasPlayer(ID)).thenReturn(true);
        when(mDatabaseActionsInterface.getPlayer(ID)).thenReturn(playerList);
        mPresenter.getPlayerDataFromRealm(ID);
        verify(mPlayerView).setPlayer(playerList.get(INDEX_OF_FIRST_ELEMENT));
    }

    @Test
    public void errorCall() {
        String error = "error";
        when(mPlayerView.isOnline()).thenReturn(true);
        when(mSnookerService.getPlayer(ID)).thenReturn(Observable.error(new Throwable(error)));
        mPresenter.getPlayerData(ID);
        verify(mPlayerView).error();
    }
}
