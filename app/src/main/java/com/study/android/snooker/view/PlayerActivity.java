package com.study.android.snooker.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.study.android.snooker.Adapter;
import com.study.android.snooker.R;
import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.model.Info.RankInfo;
import com.study.android.snooker.presenter.PlayerPresenter;
import com.study.android.snooker.presenter.PlayerPresenterInterface;

import java.util.List;

public class PlayerActivity extends AppCompatActivity implements PlayerView{

    public static final String EXTRA_playerID = "playerID";
    PlayerPresenterInterface playerPresenter = new PlayerPresenter(this);
    private PlayerInfo player;
    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int playerID = (int) getIntent().getExtras().get(EXTRA_playerID);

        playerPresenter.getPlayerData(playerID);
    }

    @Override
    public void setPlayer(PlayerInfo player) {
        TextView textView = (TextView) findViewById(R.id.player);

        textView.setText(player.getFirstName());
        this.player = player;
    }
}
