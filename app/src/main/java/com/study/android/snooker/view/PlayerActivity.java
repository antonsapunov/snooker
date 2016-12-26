package com.study.android.snooker.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.study.android.snooker.R;
import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.presenter.PlayerPresenter;
import com.study.android.snooker.presenter.PlayerPresenterInterface;

public class PlayerActivity extends AppCompatActivity implements PlayerView{

    public static final String EXTRA_playerID = "playerID";
    PlayerPresenterInterface playerPresenter = new PlayerPresenter(this);
    private PlayerInfo player;

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

        if(player.getSurnameFirst()){
            getSupportActionBar().setTitle(mLastName + " " + mFirstName);
            fullName.setText(mLastName + " " + mMiddleName + " " + mFirstName);
        } else {
            getSupportActionBar().setTitle(mFirstName + " " + mLastName);
            fullName.setText(mFirstName + " " + mMiddleName + " " + mLastName);
        }
        findViewById(R.id.forename).setVisibility(View.VISIBLE);
        if(mPhoto.equals("")) {
            findViewById(R.id.photo).setVisibility(View.GONE);
        } else {
            Picasso.with(this)
                    .load(mPhoto)
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            findViewById(R.id.progressBar).setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {

                        }
                    });
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

        this.player = player;
    }

    public void onTwitter(View view) {
        String mTwitter = player.getTwitter();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        try {
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
}
