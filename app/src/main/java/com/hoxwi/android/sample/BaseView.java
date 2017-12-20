package com.hoxwi.android.sample;

public interface BaseView<T> {

    void setPresenter(T presenter);

    boolean isActive();

    void showMessage(String msg);
}
