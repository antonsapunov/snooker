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

        if(player.getSurnameFirst()){
            getSupportActionBar().setTitle(player.getLastName() + " " + player.getFirstName());
            fullName.setText(player.getLastName() + " " + player.getMiddleName() + " " + player.getFirstName());
        } else {
            getSupportActionBar().setTitle(player.getFirstName() + " " + player.getLastName());
            fullName.setText(player.getFirstName() + " " + player.getMiddleName() + " " + player.getLastName());
        }
        findViewById(R.id.forename).setVisibility(View.VISIBLE);
        if(player.getPhoto().equals("")) {
            findViewById(R.id.photo).setVisibility(View.GONE);
        } else {
            Picasso.with(this)
                    .load(player.getPhoto())
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
        if(!player.getBorn().equals("")) {
            findViewById(R.id.birth).setVisibility(View.VISIBLE);
            birthday.setText(player.getBorn());
        }
        if(!player.getNationality().equals("")) {
            findViewById(R.id.nat).setVisibility(View.VISIBLE);
            nationality.setText(player.getNationality());
        }
        if(!player.getTwitter().equals("")) {
            findViewById(R.id.twit).setVisibility(View.VISIBLE);
            twitter.setText(player.getTwitter());
        }
        if(!player.getURL().equals("")) {
            findViewById(R.id.url).setVisibility(View.VISIBLE);
            urlName.setText(player.getURL());
        }
        if(!player.getBioPage().equals("")) {
            findViewById(R.id.bioPage).setVisibility(View.VISIBLE);
            bioPageLink.setText(R.string.snooker_org);
        }

        this.player = player;
        findViewById(R.id.playerProgressBar).setVisibility(View.GONE);
    }

    public void onTwitter(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        try {
            String twitterName = player.getTwitter();
            String formattedTwitterAddress = "http://twitter.com/" + twitterName;
            Intent browseTwitter = new Intent(Intent.ACTION_VIEW, Uri.parse(formattedTwitterAddress));
            startActivity(browseTwitter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onUrl(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        try {
            String url = player.getURL();
            String formattedUrlAddress = "http://" + url;
            Intent browseURL = new Intent(Intent.ACTION_VIEW, Uri.parse(formattedUrlAddress));
            startActivity(browseURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBioPage(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        try {
            String bioPage = player.getBioPage();
            Intent browseBioPage = new Intent(Intent.ACTION_VIEW, Uri.parse(bioPage));
            startActivity(browseBioPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
