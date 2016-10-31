package org.almiso.daggerdemo.ui.fragment;


import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.almiso.daggerdemo.R;
import org.almiso.daggerdemo.core.App;
import org.almiso.daggerdemo.model.UiUpdateEvent;
import org.almiso.daggerdemo.util.UserConfig;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public class ColorFragment extends BaseFragment {

    /* Constants */

    private static final int ANIMATION_DURATION = 250;

    /* Data */

    @Inject
    UserConfig userConfig;

    /* Views */

    private FrameLayout layoutColor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_color, container, false);

        App.getAppComponent().inject(this);

        initViews(rootView);
        updateUi();
        return rootView;
    }

    private void initViews(View rootView) {
        layoutColor = (FrameLayout) rootView.findViewById(R.id.layout_color);
    }

    private void updateUi() {
        if (!userConfig.isSignIn()) {
            changeColor(getResources().getColor(R.color.col_white));
            return;
        }

        switch (userConfig.getColor()) {
            case UserConfig.RED:
                changeColor(getResources().getColor(R.color.col_red));
                break;

            case UserConfig.YELLOW:
                changeColor(getResources().getColor(R.color.col_yellow));
                break;

            case UserConfig.GREEN:
                changeColor(getResources().getColor(R.color.col_green));
                break;
        }
    }

    private void changeColor(int colorTo) {
        ColorDrawable buttonColor = (ColorDrawable) layoutColor.getBackground();
        int colorFrom = buttonColor.getColor();

        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(ANIMATION_DURATION);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                layoutColor.setBackgroundColor((int) animator.getAnimatedValue());
            }
        });
        colorAnimation.start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(UiUpdateEvent event) {
        if (event.getEventId() == UiUpdateEvent.onColorUpdated) {
            updateUi();
        } else if (event.getEventId() == UiUpdateEvent.onSignStateUpdated) {
            updateUi();
        }
    }
}