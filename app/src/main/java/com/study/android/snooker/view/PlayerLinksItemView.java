package com.study.android.snooker.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.android.snooker.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.player_links_item_view)
public class PlayerLinksItemView extends FrameLayout {

    public PlayerLinksItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @ViewById(R.id.link_text_view)
    TextView player_link;
    @ViewById(R.id.link_hint_text_view)
    TextView hint;
    @ViewById
    LinearLayout links_linear_layout;

    public void setData(int stringRes, String link) {
        if(link.isEmpty()){
            links_linear_layout.setVisibility(GONE);
        }
        hint.setText(stringRes);
        player_link.setText(link);
    }
}
