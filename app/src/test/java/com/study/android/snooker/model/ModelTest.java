package com.study.android.snooker.model;

import com.study.android.snooker.AppForTest;
import com.study.android.snooker.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
@Config(constants = BuildConfig.class, application = AppForTest.class)

public class ModelTest {
    private SnookerService mSnookerService;

    @Before
    public void set() {
        mSnookerService = new Snooker();
    }

    @Test
    public void checkApiInfo() {
        assertNotNull(mSnookerService.getRanks());
        assertNotNull(mSnookerService.getPlayers());
        assertNotNull(mSnookerService.getPlayer(1));
    }
}
