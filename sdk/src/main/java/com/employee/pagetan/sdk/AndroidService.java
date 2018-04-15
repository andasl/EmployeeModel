package com.employee.pagetan.sdk;


import com.employee.pagetan.sdk.base.entity.vm.UserVM;
import com.employee.pagetan.sdk.dept.DeptContext;

/**
 * Created by pageTan on 2018/3/29.
 */

public class AndroidService {

    private static volatile AndroidService sdk = new AndroidService();

    private InputWindow inputWindow;
    private OutputWindow outputWindow;


    /**
     * Shared ActorSDK. Use this method to get instance of SDK for configuration and starting up
     *
     * @return ActorSDK instance. sdk单例
     */
    public static AndroidService getInstance() {
        // Use function if we will replace implementation for some cases
        return sdk;
    }

    public AndroidService() {
        DeptContext deptContext = new DeptContext();
        inputWindow = new InputWindow(deptContext);
        outputWindow = new OutputWindow(deptContext);


        inputWindow.run();
    }

    public void queryUserInfo(int uid){
        inputWindow.queryUserInfo(uid);
    }


    public void queryAdmin(){
        inputWindow.queryAdmin();
    }





    public UserVM getUseVM(int uid) {
        return outputWindow.getUserVM(uid);
    }

}
