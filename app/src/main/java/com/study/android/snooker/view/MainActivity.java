package com.study.android.snooker.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.study.android.snooker.Adapter;
import com.study.android.snooker.presenter.App;
import com.study.android.snooker.R;
import com.study.android.snooker.model.RankClass;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "myLogs";
    RecyclerView recyclerView;
    List<RankClass> posts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.posts_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Adapter adapter = new Adapter(posts);
        recyclerView.setAdapter(adapter);

        App.getApi().getData().enqueue(new Callback<List<RankClass>>() {
            @Override
            public void onResponse(Call<List<RankClass>> call, Response<List<RankClass>> response) {
                posts.addAll(response.body());
                Log.d(TAG, "Api:\n" + posts.size());
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<RankClass>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
