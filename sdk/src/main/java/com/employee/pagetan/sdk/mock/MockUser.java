package com.employee.pagetan.sdk.mock;

import android.util.Log;

import com.employee.pagetan.sdk.base.entity.User;
import com.employee.pagetan.sdk.base.exception.UserAddFailedException;
import com.employee.pagetan.sdk.base.exception.UserNotExistException;
import com.employee.pagetan.sdk.network.RestCallback;


/**
 * Created by pageTan on 2018/3/29.
 */

public class MockUser {



    public static User generateUse(int uid){
        return new User(uid, "tan:"+uid);
    }

    public static void requestUser(int uid, RestCallback callback) {
        Log.e("paget","requestUser-waiting");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("paget","requestUser-waiting success");
        if (uid %2 == 0) {
            callback.onSuccess(generateUse(uid));
        } else {
           callback.onError(new UserNotExistException());
        }
    }


    public static void addUser(int uid, RestCallback callback) {
        Log.e("paget","addUser-waiting");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("paget","addUser-waiting success");
        if (uid %2 == 0) {
            callback.onSuccess(generateUse(uid));
        } else {
            callback.onError(new UserAddFailedException());
        }
    }


    public static void configUser(User user, RestCallback callback) {
        Log.e("paget","addUser-waiting");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("paget","addUser-waiting success");
//        if (user %2 == 0) {
//            callback.onSuccess(generateUse(uid));
//        } else {
//            callback.onError(new UserAddFailedException());
//        }
        user.setName("new name:"+user.getUid());
    }


}
