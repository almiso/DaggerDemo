package org.almiso.daggerdemo.core.presenter;

public class PresenterInfo {
    private CustomersPresenter presenter;

    public PresenterInfo(CustomersPresenter presenter) {
        this.presenter = presenter;
    }

    public int getSize() {
        return presenter.getCustomers().size();
    }
}