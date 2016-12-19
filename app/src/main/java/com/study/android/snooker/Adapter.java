package com.study.android.snooker;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.android.snooker.model.PlayerClass;
import com.study.android.snooker.model.RankClass;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<RankClass> posts;
    private List<PlayerClass> players;

    public Adapter(List<RankClass> posts) {
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RankClass post = posts.get(position);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.post.setText(Html.fromHtml(post.getPosition(), Html.FROM_HTML_MODE_LEGACY));
            holder.post1.setText(Html.fromHtml(post.getPlayerID(), Html.FROM_HTML_MODE_LEGACY));
            holder.post2.setText(Html.fromHtml(post.getSum(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.post.setText(Html.fromHtml(post.getPosition()));
            holder.post1.setText(Html.fromHtml(post.getPlayerID()));
            holder.post2.setText(Html.fromHtml(post.getSum()));
        }
        holder.site.setText(post.getID());
    }

    @Override
    public int getItemCount() {
        if (posts == null)
            return 0;
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView post;
        TextView post1;
        TextView post2;
        TextView site;

        public ViewHolder(View itemView) {
            super(itemView);
            post = (TextView) itemView.findViewById(R.id.postitem_post);
            post1 = (TextView) itemView.findViewById(R.id.postitem_post1);
            post2 = (TextView) itemView.findViewById(R.id.postitem_post2);
            site = (TextView) itemView.findViewById(R.id.postitem_site);
        }
    }
}