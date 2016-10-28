package org.almiso.daggerdemo.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import org.almiso.daggerdemo.core.App;
import org.almiso.daggerdemo.core.presenter.CustomersPresenter;
import org.almiso.daggerdemo.core.presenter.PresenterInfo;
import org.almiso.daggerdemo.model.Customer;
import org.almiso.daggerdemo.ui.view.CustomerView;
import org.almiso.daggerdemo.ui.view.EmptyBottomView;
import org.almiso.daggerdemo.ui.view.Holder;
import org.almiso.daggerdemo.ui.view.ProgressView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

public class CustomersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

     /* Callbacks */

    public interface Callback {
        void onCustomerClick(Customer customer);
    }

    /* Constants */

    private static final String TAG = CustomersAdapter.class.getSimpleName();

    private static final int VIEW_TYPE_NONE = 0;
    private static final int VIEW_TYPE_PROGRESS = 1;
    private static final int VIEW_TYPE_CUSTOMER = 2;
    private static final int VIEW_TYPE_EMPTY_BOTTOM = 3;

    /* Data */

    private int viewPosition;
    private int positionProgress;
    private SparseArray<Customer> customerMap;
    private int positionEmptyBottom;

    /* Controllers */

    @Inject
    CustomersPresenter presenter;

    @Inject
    PresenterInfo presenterInfo;

    private Callback callback;

    /* Constructors */

    public CustomersAdapter(Callback callback) {
        this.callback = callback;
        this.customerMap = new SparseArray<>();

        App.getRecyclerComponent().inject(this);

        EventBus.getDefault().register(this);
    }

    /**
     * Updates list.
     * This method determines what to display, calculate all needed view positions
     * and call notifyDataSetChanged.
     * <p/>
     * Use this method instead of
     * {@link RecyclerView.Adapter#notifyDataSetChanged() notifyDataSetChanged()}
     */
    public void updateAdapter() {

        /* clear all view positions */

        viewPosition = 0;
        positionProgress = -1;
        customerMap.clear();
        positionEmptyBottom = -1;

        /* init all view positions */

        int presenterSize = presenterInfo.getSize();
        Log.d(TAG, "size=" + presenterSize);

        List<Customer> customers = presenter.getCustomers();
        boolean isLoading = presenter.isLoading();

        /* display customers */
        for (Customer customer : customers) {
            customerMap.put(viewPosition++, customer);
        }

        /* display progress */
        if (isLoading) {
            positionProgress = viewPosition++;
        }

        positionEmptyBottom = viewPosition++;

        /* update views */
        notifyDataSetChanged();
    }

    /* Override methods */
    @Override
    public int getItemCount() {
        return viewPosition;
    }

    @Override
    public int getItemViewType(int position) {
        if (customerMap.get(position) != null) {
            return VIEW_TYPE_CUSTOMER;
        } else if (position == positionProgress) {
            return VIEW_TYPE_PROGRESS;
        }else if (position == positionEmptyBottom) {
            return VIEW_TYPE_EMPTY_BOTTOM;
        }

        return VIEW_TYPE_NONE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        Context context = parent.getContext();

        switch (viewType) {

            case VIEW_TYPE_PROGRESS:
                view = new ProgressView(context);
                break;

            case VIEW_TYPE_CUSTOMER:
                view = new CustomerView(context);
                break;

            case VIEW_TYPE_EMPTY_BOTTOM:
                view = new EmptyBottomView(context);
                break;

            default:
                view = new View(context);
                break;
        }

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);

        switch (viewType) {
            case VIEW_TYPE_CUSTOMER:
                CustomerView customerView = (CustomerView) holder.itemView;
                final Customer customer = customerMap.get(position);
                if (customer == null) {
                    return;
                }

                customerView.init(customer);
                customerView.setCallback(new CustomerView.Callback() {
                    @Override
                    public void onRootClick() {
                        callback.onCustomerClick(customer);
                    }
                });
                break;

            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(CustomersPresenter.UpdateEvent event) {
        updateAdapter();
    }
}