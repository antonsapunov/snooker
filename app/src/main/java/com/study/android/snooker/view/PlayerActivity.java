package com.study.android.snooker.view;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.study.android.snooker.R;
import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.presenter.PlayerPresenter;
import com.study.android.snooker.presenter.PlayerPresenterInterface;

public class PlayerActivity extends AppCompatActivity implements PlayerView{

    public static final String EXTRA_playerID = "playerID";
    PlayerPresenterInterface playerPresenter = new PlayerPresenter(this);
    private PlayerInfo player;
    private SwipeRefreshLayout mSwipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSwipe = (SwipeRefreshLayout) findViewById(R.id.swipePlayer);
        int playerID = (int) getIntent().getExtras().get(EXTRA_playerID);

        playerPresenter.getPlayerDataFromRealm(playerID);
        mSwipe.setOnRefreshListener(() -> playerPresenter.getPlayerData(playerID));
    }

    @Override
    public void setPlayer(PlayerInfo player) {

        ImageView imageView = (ImageView) findViewById(R.id.picture);
        TextView fullName = (TextView) findViewById(R.id.fullName);
        TextView twitter = (TextView) findViewById(R.id.twitter);
        TextView birthday = (TextView) findViewById(R.id.birthday);
        TextView nationality = (TextView) findViewById(R.id.nationality);
        TextView urlName = (TextView) findViewById(R.id.urlName);
        TextView bioPageLink = (TextView) findViewById(R.id.bioPageLink);

        String mFirstName = player.getFirstName();
        String mMiddleName = player.getMiddleName();
        String mLastName = player.getLastName();
        String mPhoto = player.getPhoto();
        String mBorn = player.getBorn();
        String mNationality = player.getNationality();
        String mTwitter = player.getTwitter();
        String mUrl = player.getURL();
        String mBioPage = player.getBioPage();

        //TODO use ternary operator + string.format pattern instead of if else.
        if(player.getSurnameFirst()){
            getSupportActionBar().setTitle(mLastName + " " + mFirstName);
            fullName.setText(mLastName + " " + mMiddleName + " " + mFirstName);
        } else {
            getSupportActionBar().setTitle(mFirstName + " " + mLastName);
            fullName.setText(mFirstName + " " + mMiddleName + " " + mLastName);
        }

        findViewById(R.id.forename).setVisibility(View.VISIBLE);
        //TODO Why so complex, why not use mPhoto.isEmpty() ?
        if(mPhoto.equals("")) {
            findViewById(R.id.photo).setVisibility(View.GONE);
        } else {
            Glide.with(this)
                    .load(mPhoto)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target,
                                                   boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model,
                                                       Target<GlideDrawable> target, boolean isFromMemoryCache,
                                                       boolean isFirstResource) {
                            findViewById(R.id.progressBar).setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(imageView);
        }
        if(!mBorn.equals("")) {
            findViewById(R.id.birth).setVisibility(View.VISIBLE);
            birthday.setText(mBorn);
        }
        if(!mNationality.equals("")) {
            findViewById(R.id.nat).setVisibility(View.VISIBLE);
            nationality.setText(mNationality);
        }
        if(!mTwitter.equals("")) {
            findViewById(R.id.twit).setVisibility(View.VISIBLE);
            twitter.setText(mTwitter);
        }
        if(!mUrl.equals("")) {
            findViewById(R.id.url).setVisibility(View.VISIBLE);
            urlName.setText(mUrl);
        }
        if(!mBioPage.equals("")) {
            findViewById(R.id.bioPage).setVisibility(View.VISIBLE);
            bioPageLink.setText(R.string.snooker_org);
        }
        //TODO allways remember about else block. What if you some of members was empty ?

        this.player = player;
    }

    public void onTwitter(View view) {
        String mTwitter = player.getTwitter();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        try {
            //TODO named constants instead of hardcode.
            String formattedTwitterAddress = "http://twitter.com/" + mTwitter;
            Intent browseTwitter = new Intent(Intent.ACTION_VIEW, Uri.parse(formattedTwitterAddress));
            startActivity(browseTwitter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onUrl(View view) {
        String mUrl = player.getURL();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        try {
            String formattedUrlAddress = "http://" + mUrl;
            Intent browseURL = new Intent(Intent.ACTION_VIEW, Uri.parse(formattedUrlAddress));
            startActivity(browseURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBioPage(View view) {
        String mBioPage = player.getBioPage();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        try {
            Intent browseBioPage = new Intent(Intent.ACTION_VIEW, Uri.parse(mBioPage));
            startActivity(browseBioPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        findViewById(R.id.progressBar).setVisibility(View.GONE);
    }

    @Override
    public void error(){
        Toast.makeText(this, R.string.Error, Toast.LENGTH_LONG).show();
    }
}
