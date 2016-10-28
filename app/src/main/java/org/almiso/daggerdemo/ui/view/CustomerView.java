package org.almiso.daggerdemo.ui.view;


import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import org.almiso.daggerdemo.model.Customer;
import org.almiso.daggerdemo.R;

public class CustomerView extends FrameLayout {

    public interface Callback {
        void onRootClick();
    }

    private AppCompatTextView tvName;
    private AppCompatTextView tvAge;

    private Callback callback;

    public CustomerView(Context context) {
        super(context);
        initView(context);
    }

    public CustomerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CustomerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        inflate(context, R.layout.view_customer, this);

        tvName = (AppCompatTextView) findViewById(R.id.tv_name);
        tvAge = (AppCompatTextView) findViewById(R.id.tv_age);

        findViewById(R.id.layout_root).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null) {
                    callback.onRootClick();
                }
            }
        });
    }

    public void init(Customer customer) {
        tvName.setText(customer.getName());
        tvAge.setText(String.valueOf(customer.getAge()));
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}