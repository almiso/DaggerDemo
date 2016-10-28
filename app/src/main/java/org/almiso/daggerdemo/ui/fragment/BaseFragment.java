package org.almiso.daggerdemo.ui.fragment;

import android.support.v4.app.Fragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusException;

public class BaseFragment extends Fragment {

    @Override
    public void onResume() {
        super.onResume();
        registerSubscribers();
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterSubscribers();
    }

    private void registerSubscribers() {
        try {
            EventBus.getDefault().register(this);
        } catch (EventBusException ignored) {
        }
    }

    private void unregisterSubscribers() {
        EventBus.getDefault().unregister(this);
    }
}