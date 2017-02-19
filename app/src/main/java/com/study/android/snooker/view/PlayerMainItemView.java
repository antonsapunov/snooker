package com.study.android.snooker.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.android.snooker.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.player_main_item_view)
public class PlayerMainItemView extends FrameLayout {

    public PlayerMainItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @ViewById(R.id.player_parameter_text_view)
    TextView player_parameter;
    @ViewById(R.id.hint_text_view)
    TextView hint;
    @ViewById
    LinearLayout main_linear_layout;

    public void setData(int stringRes, String parameter) {
        if(parameter.isEmpty()){
            main_linear_layout.setVisibility(GONE);
        }
        hint.setText(stringRes);
        player_parameter.setText(parameter);
    }
}
