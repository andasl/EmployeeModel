package com.employee.pagetan.sdk.db;


import com.employee.pagetan.sdk.base.entity.User;

/**
 * Created by pageTan on 2018/3/29.
 */

public interface UserDbInterface {

    User findUserById(int uid);


    void addOrUpdateUser(User user);

    void removeUser(int uid);

    void clearDb();


}
