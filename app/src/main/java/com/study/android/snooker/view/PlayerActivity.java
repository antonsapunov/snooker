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

    public static final String EXTRA_PLAYER_ID = "playerID";
    public static final String HTTP_TWITTER_COM = "http://twitter.com/";
    public static final String HTTP = "http://";
    private PlayerPresenterInterface mPlayerPresenter = new PlayerPresenter(this);
    private PlayerInfo mPlayer;
    private SwipeRefreshLayout mSwipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSwipe = (SwipeRefreshLayout) findViewById(R.id.swipePlayer);
        int playerID = (int) getIntent().getExtras().get(EXTRA_PLAYER_ID);

        mPlayerPresenter.getPlayerDataFromRealm(playerID);
        mSwipe.setOnRefreshListener(() -> mPlayerPresenter.getPlayerData(playerID));
    }

    @Override
    public void setPlayer(PlayerInfo player) {

        mPlayer = player;

        ImageView imageView = (ImageView) findViewById(R.id.picture);
        String photo = player.getPhoto();
        if(photo.isEmpty()) {
            makeGone(R.id.photo);
        } else {
            Glide.with(this)
                    .load(photo)
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
                            makeGone(R.id.progressBar);
                            return false;
                        }
                    })
                    .into(imageView);
        }
        TextView fullNameView = (TextView) findViewById(R.id.fullName);
        String firstName = player.getFirstName();
        String middleName = player.getMiddleName();
        String lastName = player.getLastName();

        String playerName = String.format("%s %s", firstName, lastName);
        String inversedPlayerName = String.format("%s %s", lastName, firstName);
        getSupportActionBar().setTitle(player.getSurnameFirst() ? inversedPlayerName : playerName);

        String fullPlayerName = String.format("%s %s %s", firstName, middleName, lastName);
        String inversedFullPlayerName = String.format("%s %s %s", lastName, middleName, firstName);
        if(!fullPlayerName.isEmpty()) {
            makeVisible(R.id.full);
            fullNameView.setText(player.getSurnameFirst() ? inversedFullPlayerName : fullPlayerName);
        } else makeGone(R.id.full);

        TextView birthdayView = (TextView) findViewById(R.id.birthday);
        String born = player.getBorn();
        if(!born.isEmpty()) {
            makeVisible(R.id.birth);
            birthdayView.setText(born);
        } else makeGone(R.id.birth);

        TextView nationalityView = (TextView) findViewById(R.id.nationality);
        String nationality = player.getNationality();
        if(!nationality.isEmpty()) {
            makeVisible(R.id.nat);
            nationalityView.setText(nationality);
        } else makeGone(R.id.nat);

        TextView twitterView = (TextView) findViewById(R.id.twitter);
        String twitter = player.getTwitter();
        if(!twitter.isEmpty()) {
            makeVisible(R.id.twit);
            twitterView.setText(twitter);
        } else makeGone(R.id.twit);

        TextView urlView = (TextView) findViewById(R.id.urlName);
        String url = player.getURL();
        if(!url.isEmpty()) {
            makeVisible(R.id.url);
            urlView.setText(url);
        } else makeGone(R.id.url);

        TextView bioPageView = (TextView) findViewById(R.id.bioPageLink);
        String bioPage = player.getBioPage();

        if(!bioPage.isEmpty()) {
            makeVisible(R.id.bioPage);
            bioPageView.setText(R.string.snooker);
        } else makeGone(R.id.bioPage);
    }

    public void makeVisible(int layoutId){
        findViewById(layoutId).setVisibility(View.VISIBLE);
    }

    public void makeGone(int layoutId){
        findViewById(layoutId).setVisibility(View.GONE);
    }

    public void onTwitter(View view) {
        String mTwitter = mPlayer.getTwitter();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        try {
            String formattedTwitterAddress = HTTP_TWITTER_COM + mTwitter;
            Intent browseTwitter = new Intent(Intent.ACTION_VIEW, Uri.parse(formattedTwitterAddress));
            startActivity(browseTwitter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onUrl(View view) {
        String mUrl = mPlayer.getURL();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        try {
            String formattedUrlAddress = HTTP + mUrl;
            Intent browseURL = new Intent(Intent.ACTION_VIEW, Uri.parse(formattedUrlAddress));
            startActivity(browseURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBioPage(View view) {
        String mBioPage = mPlayer.getBioPage();
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
