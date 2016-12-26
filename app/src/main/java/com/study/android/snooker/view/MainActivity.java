package com.study.android.snooker.view;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

public class MainActivity extends AppCompatActivity implements MainView{
    MainPresenterInterface mainPresenter = new MainPresenter(this);
    RecyclerView recyclerView;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.ranks_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new Adapter();
        recyclerView.setAdapter(adapter);

        adapter.setListener(this::startInfoActivity);

        mainPresenter.getRankData();
    }

    @Override
    public void setRanks(List<RankInfo> ranks) {
        adapter.setListRanks(ranks);
        adapter.notifyDataSetChanged();
        findViewById(R.id.mainProgressBar).setVisibility(View.GONE);
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
        Toast.makeText(this, "Нет подключения к интернету", Toast.LENGTH_LONG).show();
    }

    private void startInfoActivity(int playerID) {
        Intent intent = new Intent(this, PlayerActivity.class);
        intent.putExtra(PlayerActivity.EXTRA_playerID, playerID);
        startActivity(intent);
    }
}
