package com.employee.pagetan.sdk.base.exception;

/**
 * Created by pageTan on 2018/3/29.
 */

public class UserNotExistException extends Exception {

    public UserNotExistException() {
        super("user not exist");
    }
}
