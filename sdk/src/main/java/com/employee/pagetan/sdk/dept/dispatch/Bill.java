package com.employee.pagetan.sdk.dept.dispatch;

import com.employee.pagetan.sdk.dept.DeptSystem;

import java.util.List;

/**
 * Created by pageTan on 2018/3/29.
 */

public class Bill<T> {
    //
    private int key; // 位内容：request employ dept
    private T data;
    private List<Bill> nextRelationBills; // 有可能是相互关联的清单链，需要顺序执行


    public Bill(int queryType, int dept, T data) {
        this(queryType,0, dept, data);
    }
    public Bill(int queryType, int specialDealMan,int dept, T data) {
        this.data = data;
        this.key = generateKey(queryType, specialDealMan, dept);
    }
    private int generateKey(int queryType,int specialEmployee, int dept){
        int queryTypeMaskCount = DeptSystem.MASK_COUNT_DEPT + DeptSystem.MASK_COUNT_EMPLOYEE;
        int employeeMaskCount = DeptSystem.MASK_COUNT_EMPLOYEE;
        return (queryType<< queryTypeMaskCount)+(specialEmployee<< employeeMaskCount)+dept;
    }


    public int getDeptID(){// 是保存在低位，所以使用mask
        int mask = (1<< DeptSystem.MASK_COUNT_DEPT) -1;
        return key & mask;
    }

    public int getEmployNumber(){// 是保存在中间位，所以使用mask
        int maskEndCount =  DeptSystem.MASK_COUNT_DEPT+ DeptSystem.MASK_COUNT_EMPLOYEE;
        int maskStartCount = DeptSystem.MASK_COUNT_DEPT;
        int mask = (1 << maskEndCount) -(1 << maskStartCount);
        return (key & mask) >> maskStartCount;
    }

    public int getQueryNumber(){ // 是保存在最高位，所以使用位移就好
        int maskCount = DeptSystem.MASK_COUNT_DEPT+ DeptSystem.MASK_COUNT_EMPLOYEE;
        return key >> maskCount;
    }


    public T getData() {
        return data;
    }
}
