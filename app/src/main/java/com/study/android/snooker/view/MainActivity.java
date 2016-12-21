package com.study.android.snooker.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.study.android.snooker.Adapter;
import com.study.android.snooker.R;
import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.model.Info.RankInfo;
import com.study.android.snooker.presenter.MainPresenter;
import com.study.android.snooker.presenter.MainPresenterInterface;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView{
    MainPresenterInterface mainPresenter = new MainPresenter(this);
    RecyclerView recyclerView;
    Adapter adapter;
    private static final String TAG = "myLogs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.ranks_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        adapter = new Adapter();
        recyclerView.setAdapter(adapter);

        mainPresenter.getRankData();
    }

    @Override
    public void setRanks(List<RankInfo> ranks) {
        adapter.setListRanks(ranks);
        adapter.notifyDataSetChanged();
    }
}
