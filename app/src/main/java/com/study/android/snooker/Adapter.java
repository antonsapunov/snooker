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

import rx.subjects.PublishSubject;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<RankInfo> ranks;
    private static final String TAG = "myLogs";
    private Listener listener;

    public Adapter() {
    }

    public void setListRanks(List<RankInfo> ranks) {
        this.ranks = ranks;
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        View textView = holder.itemView;
        RankInfo rank = ranks.get(position);
        holder.position.setText(String.valueOf(rank.getPosition()) + ". ");
        holder.name.setText(rank.getName());
        holder.sum.setText("£ " + String.valueOf(rank.getSum()));

        textView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(rank.getPlayerID());
            }
        });
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