package com.employee.pagetan.sdk;


import com.employee.pagetan.sdk.base.DataHeader;
import com.employee.pagetan.sdk.dept.DeptContext;
import com.employee.pagetan.sdk.dept.UserDept;
import com.employee.pagetan.sdk.dept.dispatch.Bill;

/**
 * Created by pageTan on 2018/3/29.
 */

public class InputWindow {


    private DeptContext context;
    private UserDept userDept;


    public InputWindow(DeptContext context) {
        this.context = context;
        userDept = new UserDept(context);
    }

    public void run(){
        userDept.run();
    }



    public void queryUserInfo(int uid){
        Bill bill = new Bill(DataHeader.USER, UserDept.DEPT_NUMBER, uid);
        userDept.send(bill, userDept);
    }

    public void queryAdmin(){

        Bill bill = new Bill(DataHeader.USER_ADMIN, UserDept.DEPT_NUMBER, 0);
        userDept.send(bill, userDept);
    }





}
