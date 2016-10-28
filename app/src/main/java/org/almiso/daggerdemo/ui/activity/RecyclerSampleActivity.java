package org.almiso.daggerdemo.ui.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.almiso.daggerdemo.R;
import org.almiso.daggerdemo.core.App;
import org.almiso.daggerdemo.core.presenter.CustomersPresenter;
import org.almiso.daggerdemo.core.presenter.PresenterInfo;
import org.almiso.daggerdemo.model.Customer;
import org.almiso.daggerdemo.ui.adapter.CustomersAdapter;

import javax.inject.Inject;

public class RecyclerSampleActivity extends BaseActivity {

    /* Constants */

    private static final String TAG = RecyclerSampleActivity.class.getSimpleName();

    /* Data */

    @Inject
    CustomersPresenter presenter;

    @Inject
    PresenterInfo presenterInfo;

    private CustomersAdapter adapter;

    /* Start methods */

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, RecyclerSampleActivity.class);
        activity.startActivity(intent);
    }

    /* Common methods */

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.updateAdapter();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_sample);

        App.getRecyclerComponent().inject(this);

        initToolbar();
        initView();
    }

    /* Initialisation methods */

    private void initToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle(R.string.st_multiple_injections);
        }
    }

    private void initView() {

        /* init adapter */
        adapter = new CustomersAdapter(new CustomersAdapter.Callback() {
            @Override
            public void onCustomerClick(Customer customer) {
                Toast.makeText(RecyclerSampleActivity.this, customer.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        /* init recycler view */
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        findViewById(R.id.button_load_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int presenterSize = presenterInfo.getSize();
                Log.d(TAG, "size=" + presenterSize);

                presenter.loadMore();
            }
        });
    }
}