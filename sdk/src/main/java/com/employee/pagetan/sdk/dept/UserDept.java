package com.employee.pagetan.sdk.dept;

import android.util.Log;

import com.employee.pagetan.sdk.base.DataHeader;
import com.employee.pagetan.sdk.base.entity.User;
import com.employee.pagetan.sdk.base.entity.vm.UserVM;
import com.employee.pagetan.sdk.db.UserDbInterface;
import com.employee.pagetan.sdk.db.impl.UserDb;
import com.employee.pagetan.sdk.dept.dispatch.Bill;
import com.employee.pagetan.sdk.dept.staff.ProcessAbility;
import com.employee.pagetan.sdk.mock.MockUser;
import com.employee.pagetan.sdk.network.RestCallback;


/**
 * Created by pageTan on 2018/3/29.
 */

public class UserDept extends BaseDept implements ProcessAbility {

    public static final int DEPT_NUMBER = 0x01;

    private UserDbInterface userDb;
    private ProcessAbility processAbility;

    public UserDept(DeptContext context) {
        super(context);
        this.userDb = new UserDb();
        processAbility = this;
    }

    public void run(){
        registEmployee(processAbility);
    }

    @Override
    public void process(Bill bill) {
        switch (bill.getQueryNumber()) {
            case DataHeader.USER:
                queryUser(bill);
                break;
            case DataHeader.USER_ADMIN:
                queryAdmin(bill);
                break;

        }
    }


    public void addUser(final int uid) {
        MockUser.addUser(uid, new RestCallback<User>() {
            @Override
            public void onSuccess(User result) {
                userDb.addOrUpdateUser(result);
                UserVM userVM= context.referenceUserVM(uid);
                userVM.setStateSuccess(result);
            }

            @Override
            public void onError(Exception e) {
                UserVM userVM= context.referenceUserVM(uid);
                userVM.setStateFailed(e);
            }
        });
    }

    public void queryAdmin(Bill bill){
        Log.e("paget","queryUser" + bill.getQueryNumber());
        final int uid = (int) bill.getData();
        UserVM userVM= context.referenceUserVM(uid);
        userVM.setStateLoading();
        final User user = userDb.findUserById(uid);
        if (user != null) {
            Log.e("paget","queryUser in userDb");
            userVM.setStateSuccess(user);
            return;
        }
        // need request from server
        MockUser.requestUser(uid, new RestCallback<User>() {
            @Override
            public void onSuccess(User result) {
                userDb.addOrUpdateUser(result);
                UserVM userVM= context.referenceUserVM(uid);
                userVM.setStateSuccess(result);
            }

            @Override
            public void onError(Exception e) {
                UserVM userVM= context.referenceUserVM(uid);
                userVM.setStateFailed(e);
            }
        });
    }


    public void queryUser(Bill bill){
        Log.e("paget","queryUser" + bill.getQueryNumber());
        final int uid = (int) bill.getData();
        UserVM userVM= context.referenceUserVM(uid);
        userVM.setStateLoading();
        final User user = userDb.findUserById(uid);
        if (user != null) {
            Log.e("paget","queryUser in userDb");
            userVM.setStateSuccess(user);
            return;
        }
        // need request from server
        MockUser.requestUser(uid, new RestCallback<User>() {
            @Override
            public void onSuccess(User result) {
                userDb.addOrUpdateUser(result);
                UserVM userVM= context.referenceUserVM(uid);
                userVM.setStateSuccess(result);
            }

            @Override
            public void onError(Exception e) {
                UserVM userVM= context.referenceUserVM(uid);
                userVM.setStateFailed(e);
            }
        });

    }


}
