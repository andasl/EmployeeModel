package com.employee.pagetan.sdk.base.entity.vm;

import android.support.annotation.Nullable;

/**
 * Created by pageTan on 2018/3/29.
 */

public interface QueryStateChangeListener<T> {

    void onStateChanged(int queryState, @Nullable T value, @Nullable Exception e);

}
