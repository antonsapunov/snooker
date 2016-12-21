package com.study.android.snooker;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.android.snooker.model.Info.PlayerInfo;
import com.study.android.snooker.model.Info.RankInfo;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<RankInfo> ranks;
    private List<PlayerInfo> players;
    private static final String TAG = "myLogs";

    public Adapter() {
    }

    public void setListRanks(List<RankInfo> ranks) {
        this.ranks = ranks;
    }
    public void setListPlayers(List<PlayerInfo> players) {
        this.players = players;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RankInfo rank = ranks.get(position);
//        Log.d(TAG, ""+rank.getPlayerID());
//        int listNumber = 0;
        Log.d(TAG, "" + players.size());
//        for (int i = 0; i < players.size(); i++) {
//            if(players.get(0).getID().equals(rank.getPlayerID()))
//                listNumber = i;
//        }
//        Log.d(TAG, "listnumber " + listNumber);
//        PlayerInfo player = players.get(listNumber); // playerID не соответствует номеру массива
        holder.position.setText(String.valueOf(rank.getPosition()));
//        holder.name.setText(player.getFirstName() + " "+ player.getMiddleName() + " " + player.getLastName());
        holder.sum.setText(String.valueOf(rank.getSum()));
    }

    @Override
    public int getItemCount() {
        if (ranks == null)
            return 0;
        return ranks.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView position;
        TextView name;
        TextView sum;

        public ViewHolder(View itemView) {
            super(itemView);
            position = (TextView) itemView.findViewById(R.id.position);
            name = (TextView) itemView.findViewById(R.id.name);
            sum = (TextView) itemView.findViewById(R.id.sum);
        }
    }
}