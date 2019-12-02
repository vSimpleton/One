package oms.pomelo.one.one.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import oms.pomelo.one.R;

/**
 * Created by Sherry on 2019/12/2
 * ONE首页的每日内容
 */

public class OneTopView extends RelativeLayout {

    public OneTopView(Context context) {
        this(context, null);
    }

    public OneTopView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OneTopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.one_top_view, this, false);
        addView(view);
    }
}
