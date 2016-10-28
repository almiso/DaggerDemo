package org.almiso.daggerdemo.ui.view;


import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import org.almiso.daggerdemo.R;

public class EmptyBottomView extends FrameLayout {

    public EmptyBottomView(Context context) {
        super(context);
        initView(context);
    }

    public EmptyBottomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public EmptyBottomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public EmptyBottomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        inflate(context, R.layout.view_empty_bottom, this);
    }
}