package com.employee.pagetan.sdk.dept;

/**
 * Created by pageTan on 2018/3/29.
 */

public class DeptSystem {


    public static final int MASK_COUNT_DEPT = 8;
    public static final int MASK_COUNT_EMPLOYEE = 8;



    public static BaseDept referenceDept(int actorNumber, DeptContext context){
        switch (actorNumber) {
            case UserDept.DEPT_NUMBER:
                return new UserDept(context);
        }
        return null;
    }

}
