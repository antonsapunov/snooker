package com.study.android.snooker.view;

import com.study.android.snooker.AppForTest;
import com.study.android.snooker.BuildConfig;
import com.study.android.snooker.presenter.PlayerPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, application = AppForTest.class)
public class PlayerActivityTest {

    @Mock
    public static PlayerPresenter mPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Robolectric.setupActivity(PlayerActivity_.class);
    }

    @Test
    public void checkGetDataCall() {
        verify(mPresenter).getPlayerDataFromRealm(0);
    }
}
