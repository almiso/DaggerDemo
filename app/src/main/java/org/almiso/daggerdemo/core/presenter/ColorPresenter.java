package org.almiso.daggerdemo.core.presenter;


import org.almiso.daggerdemo.model.UiUpdateEvent;
import org.greenrobot.eventbus.EventBus;

import static org.almiso.daggerdemo.core.presenter.ColorPresenter.Color.RED;

public class ColorPresenter {

    public enum Color {
        RED, YELLOW, GREEN
    }

    private Color color;

    public ColorPresenter() {
        this.color = RED;
    }

    public void updateColor(Color newColor) {
        if (color != newColor) {
            color = newColor;
            EventBus.getDefault().post(new UiUpdateEvent(UiUpdateEvent.onColorPresenterUpdated));
        }
    }

    public Color getColor() {
        return color;
    }
}