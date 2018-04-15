package com.employee.pagetan.sdk.network;

/**
 * Created by pageTan on 2018/3/29.
 */

public interface RestCallback<T> {

    void onSuccess(T result);

    void onError(Exception e);

}
