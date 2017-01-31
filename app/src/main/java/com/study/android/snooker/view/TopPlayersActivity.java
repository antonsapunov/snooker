package com.study.android.snooker.view;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.study.android.snooker.adapter.Adapter;
import com.study.android.snooker.R;
import com.study.android.snooker.model.Info.RankInfo;
import com.study.android.snooker.presenter.MainPresenter;
import com.study.android.snooker.presenter.MainPresenterInterface;

import java.util.List;

import io.realm.Realm;
public class TopPlayersActivity extends AppCompatActivity implements TopPlayersView {
    private MainPresenterInterface mMainPresenter = new MainPresenter(this);
    private Adapter mAdapter;
    private SwipeRefreshLayout mSwipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this);
        setContentView(R.layout.activity_main);

        mSwipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.ranks_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new Adapter();
        recyclerView.setAdapter(mAdapter);

        mMainPresenter.getRankDataFromRealm();
        mSwipe.setOnRefreshListener(() -> mMainPresenter.getRankData());

        mAdapter.setListener(this::startInfoActivity);
    }

    @Override
    public void setRanks(List<RankInfo> ranks) {
        mAdapter.setListRanks(ranks);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean isOnline(){
        if(getApplicationContext() == null) {
            return false;
        }
        ConnectivityManager cm =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void noConnection(){
        Toast.makeText(this, R.string.NoInternet, Toast.LENGTH_LONG).show();
    }

    @Override
    public void swipeBarDisable() {
        mSwipe.setRefreshing(false);
    }

    @Override
    public void progressBarDisable() {
        findViewById(R.id.mainProgressBar).setVisibility(View.GONE);
    }

    @Override
    public void error(){
        Toast.makeText(this, R.string.Error, Toast.LENGTH_LONG).show();
    }

    private void startInfoActivity(int playerID) {
        Intent intent = new Intent(this, PlayerActivity.class);
        intent.putExtra(PlayerActivity.EXTRA_PLAYER_ID, playerID);
        startActivity(intent);
    }
}