package com.employee.pagetan.sdk.dept;


import com.employee.pagetan.sdk.base.entity.vm.UserVM;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pageTan on 2018/3/30.
 */

public class DeptContext {


    private Map<Integer, UserVM> userVMMap = new HashMap<>();


    /**
     * 可能会异步调用，所以需要synchronized
     */
    public synchronized UserVM referenceUserVM(int uid){
        UserVM userVM = userVMMap.get(uid);
        if (userVM == null) {
            userVM = new UserVM();
            userVMMap.put(uid, userVM);
        }
        return userVM;
    }
}
