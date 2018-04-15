package com.employee.pagetan.sdk.base;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by pageTan on 2018/4/3.
 */

public class Runtime {

    private static final Handler handler = new Handler(Looper.getMainLooper());

    public static void postToMainThread(Runnable runnable) {
        handler.post(runnable);
    }



}
