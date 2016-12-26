package com.study.android.snooker.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.android.snooker.R;
import com.study.android.snooker.model.Info.RankInfo;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<RankInfo> ranks;
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

        String mPositionField = String.valueOf(rank.getPosition()) + ". ";
        String mNameField = rank.getName();
        String mSumField = "£ " + String.valueOf(rank.getSum());

        holder.mPosition.setText(mPositionField);
        holder.mName.setText(mNameField);
        holder.mSum.setText(mSumField);
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
        TextView mPosition;
        TextView mName;
        TextView mSum;

        ViewHolder(View itemView) {
            super(itemView);
            mPosition = (TextView) itemView.findViewById(R.id.position);
            mName = (TextView) itemView.findViewById(R.id.name);
            mSum = (TextView) itemView.findViewById(R.id.sum);
        }
    }
}