package com.employee.pagetan.sdk.base.entity.vm;

import android.support.annotation.Nullable;
import android.util.Log;

import com.employee.pagetan.sdk.base.QueryState;
import com.employee.pagetan.sdk.base.Runtime;

import java.util.ArrayList;

/**
 * Created by pageTan on 2018/3/29.
 */

public class BaseVM<T> {

    private int queryState;
    private T value;
    private Exception exception;

    private ArrayList<QueryStateChangeListener<T>> listeners = new ArrayList<>();


    public void subscribe(QueryStateChangeListener<T> listener) {
        subscribe(listener, false);
    }


    public void subscribe(QueryStateChangeListener<T> listener, boolean notify) {
        // im.actor.runtime.Runtime.checkMainThread();

        if (listeners.contains(listener)) {
            return;
        }
        listeners.add(listener);
        if (notify) {
            listener.onStateChanged(queryState, getValue(), getException());
        }
    }


    public void unsubscribe(QueryStateChangeListener<T> listener) {
        listeners.remove(listener);
    }

    public void unsubscribeAll(){
        listeners.clear();
    }

    public void setValue(T data) {
        this.value = data;

    }
    public T getValue() {
        return value;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public void setStateLoading(){
        Log.e("paget","setStateLoading");
        setQueryState(QueryState.LOADING);
    }
    public void setStateSuccess(T result){
        Log.e("paget","setStateSuccess");
        this.value =result;
        setQueryState(QueryState.LOAD_SUCCESS);
    }

    public void setStateFailed(Exception e){
        Log.e("paget","setStateFailed");
        this.exception = e;
        setQueryState(QueryState.LOAD_FAILURE);
    }

    public int getQueryState() {
        return queryState;
    }

    protected void setQueryState(int queryState) {
        this.queryState = queryState;
        notifyChange(queryState, value, exception);
    }
    private void notifyChange(final int queryState, @Nullable final T value, @Nullable final Exception e){
//        Runtime.postToMainThread();
        Runtime.postToMainThread(new Runnable() {
            @Override
            public void run() {
                notifyStateChanged(queryState, value, exception);
            }
        });
    }

    private void notifyStateChanged(int queryState, @Nullable T value, @Nullable Exception e){
        for (QueryStateChangeListener listener : listeners) {
            listener.onStateChanged(queryState, value, exception);
        }
    }

}
