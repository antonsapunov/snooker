package com.study.android.snooker.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.study.android.snooker.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.player_image_item_view)
public class PlayerImageItemView extends FrameLayout {
    public PlayerImageItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @ViewById
    ImageView picture;
    @ViewById
    ProgressBar progressBar;
    @ViewById
    RelativeLayout player_image_layout;

    public void setData(String photo) {
        if(photo.isEmpty()) {
            player_image_layout.setVisibility(GONE);
        } else {
            Glide.with(getContext())
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
                            progressBar.setVisibility(GONE);
                            return false;
                        }
                    })
                    .into(picture);
        }
    }
}
