package com.employee.pagetan.sdk;


import com.employee.pagetan.sdk.base.entity.vm.UserVM;
import com.employee.pagetan.sdk.dept.DeptContext;

/**
 * Created by pageTan on 2018/3/30.
 */

public class OutputWindow {

    private DeptContext context;

    public OutputWindow(DeptContext context) {
        this.context = context;
    }

    public UserVM getUserVM(int uid){
        return context.referenceUserVM(uid);
    }



}
