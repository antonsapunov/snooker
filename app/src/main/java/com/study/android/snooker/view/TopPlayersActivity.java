package com.study.android.snooker.view;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.study.android.snooker.App;
import com.study.android.snooker.adapter.Adapter;
import com.study.android.snooker.R;
import com.study.android.snooker.model.Info.RankInfo;
import com.study.android.snooker.presenter.TopPlayersPresenterInterface;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import javax.inject.Inject;

@EActivity(R.layout.activity_main)
public class TopPlayersActivity extends AppCompatActivity implements TopPlayersView {
    @Inject
    TopPlayersPresenterInterface mTopPlayersPresenter;
    @Bean
    Adapter mAdapter;
    @ViewById(R.id.swipe)
    SwipeRefreshLayout mSwipe;
    @ViewById(R.id.ranks_recycle_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
        mTopPlayersPresenter.setView(this);
    }

    @AfterViews
    protected void afterviews() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setAdapter(mAdapter);
        mTopPlayersPresenter.getRankDataFromRealm();
        mAdapter.setListener(this::startInfoActivity);

        mSwipe.setOnRefreshListener(() -> mTopPlayersPresenter.getRankData());
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

    private void startInfoActivity(int playerId) {
        Intent intent = new Intent(this, PlayerActivity_.class);
        intent.putExtra(PlayerActivity.EXTRA_PLAYER_ID, playerId);
        startActivity(intent);
    }
}
