package com.study.android.snooker.view;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.study.android.snooker.R;
import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.presenter.PlayerPresenter;
import com.study.android.snooker.presenter.PlayerPresenterInterface;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_player)
public class PlayerActivity extends AppCompatActivity implements PlayerView{

    public static final String EXTRA_PLAYER_ID = "playerID";
    public static final String HTTP_TWITTER_COM = "http://twitter.com/";
    public static final String HTTP = "http://";
    private PlayerInfo mPlayer;
    @Bean(PlayerPresenter.class)
    PlayerPresenterInterface mPlayerPresenter;
    @ViewById(R.id.swipePlayer)
    SwipeRefreshLayout mSwipe;
    @ViewById
    PlayerImageItemView player_image;
    @ViewById
    PlayerMainItemView player_info_name;
    @ViewById
    PlayerMainItemView player_info_birthday;
    @ViewById
    PlayerMainItemView player_info_nationality;
    @ViewById
    PlayerLinksItemView player_link_twitter;
    @ViewById
    PlayerLinksItemView player_link_url;
    @ViewById
    PlayerLinksItemView player_link_bio_page;
    @Extra
    int playerID;

    @AfterInject
    protected  void inject() {
        mPlayerPresenter.setView(this);
    }
    @AfterViews
    protected void afterviews() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPlayerPresenter.getPlayerDataFromRealm(playerID);
        mSwipe.setOnRefreshListener(() -> mPlayerPresenter.getPlayerData(playerID));
    }

    @Override
    public void setPlayer(PlayerInfo player) {
        mPlayer = player;

        String firstName = player.getFirstName();
        String middleName = player.getMiddleName();
        String lastName = player.getLastName();

        String playerName = String.format("%s %s", firstName, lastName);
        String inversedPlayerName = String.format("%s %s", lastName, firstName);
        getSupportActionBar().setTitle(player.getSurnameFirst() ? inversedPlayerName : playerName);

        String photo = player.getPhoto();
        player_image.setData(photo);

        String fullPlayerName = String.format("%s %s %s", firstName, middleName, lastName);
        String inversedFullPlayerName = String.format("%s %s %s", lastName, middleName, firstName);
        player_info_name.setData(R.string.fullName, player.getSurnameFirst() ? inversedFullPlayerName : fullPlayerName);
        String born = player.getBorn();
        player_info_birthday.setData(R.string.birth, born);
        String nationality = player.getNationality();
        player_info_nationality.setData(R.string.nationality, nationality);
        String twitter = player.getTwitter();
        player_link_twitter.setData(R.string.twitter, twitter);
        String url = player.getURL();
        player_link_url.setData(R.string.url, url);
        String bioPage = player.getBioPage();
        player_link_bio_page.setData(R.string.bio_page, bioPage);
    }

    @Click(R.id.player_link_twitter)
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
    @Click(R.id.player_link_url)
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
    @Click(R.id.player_link_bio_page)
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
    public void error(){
        Toast.makeText(this, R.string.Error, Toast.LENGTH_LONG).show();
    }
}
