package com.employee.pagetan.sdk.db.impl;

import com.employee.pagetan.sdk.base.entity.User;
import com.employee.pagetan.sdk.db.UserDbInterface;
import com.employee.pagetan.sdk.mock.MockUser;

import java.util.HashMap;

/**
 * Created by pageTan on 2018/3/29.
 */

public class UserDb implements UserDbInterface {

    // 将可能常用的缓存到内存里面
    private final HashMap<Integer, User> cache = new HashMap<>();


    // 用map模拟数据库
    public static HashMap<Integer, User> userDB = new HashMap<>();


    @Override
    public User findUserById(int uid) {
        // 这里模拟从数据库取数据
        if (uid%2 == 0) { // 数据库中又数据
            if (cache.containsKey(uid)) {
                return  cache.get(uid);
            }
            User user =  MockUser.generateUse(uid); // replace findInDB
            cache.put(uid, user);
            return user;
        } else {
            return null;
        }
    }


    @Override
    public void addOrUpdateUser(User user) {
        cache.put(user.getUid(), user);
        // save to db
        saveUserToDB(user);
    }


    @Override
    public void removeUser(int uid) {
        cache.remove(uid);
        // todo remove from db
    }

    @Override
    public void clearDb(){
        cache.clear();
        // todo clear db
    }


    private static void saveUserToDB(User user){
        // 用hashmap模拟数据库保存
        try {
            Thread.sleep(500); // 保存到数据库所需要时间假设为500ms
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userDB.put(user.getUid(), user);
    }

}
