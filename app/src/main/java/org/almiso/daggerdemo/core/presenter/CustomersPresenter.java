package org.almiso.daggerdemo.core.presenter;


import org.almiso.daggerdemo.model.Customer;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CustomersPresenter {

    private List<Customer> customers;
    private boolean isLoading;

    public CustomersPresenter() {
        this.customers = new ArrayList<>();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void loadMore() {
        if (isLoading) {
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                isLoading = true;
                notifyOnDataChanged();

                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Random random = new Random();
                for (int i = 0; i < 10; i++) {
                    Customer customer = new Customer();
                    customer.setName("Alexander");
                    customer.setAge(random.nextInt(100));
                    customers.add(customer);
                }

                isLoading = false;
                notifyOnDataChanged();
            }
        }).start();
    }

    private void notifyOnDataChanged() {
        EventBus.getDefault().post(new UpdateEvent());
    }

    public static class UpdateEvent {
    }
}