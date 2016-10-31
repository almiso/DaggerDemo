package org.almiso.daggerdemo.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.almiso.daggerdemo.R;
import org.almiso.daggerdemo.core.App;
import org.almiso.daggerdemo.util.UserConfig;

import javax.inject.Inject;

public class SelectorFragment extends BaseFragment {

    /* Data */

    @Inject
    UserConfig userConfig;

    /* Common methods */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_selector, container, false);

        App.getAppComponent().inject(this);

        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView) {
        rootView.findViewById(R.id.fab_red).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userConfig.setColor(UserConfig.RED);
            }
        });

        rootView.findViewById(R.id.fab_yellow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userConfig.setColor(UserConfig.YELLOW);
            }
        });

        rootView.findViewById(R.id.fab_green).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userConfig.setColor(UserConfig.GREEN);
            }
        });
    }
}