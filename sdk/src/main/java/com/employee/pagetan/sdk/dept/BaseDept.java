package com.employee.pagetan.sdk.dept;

import com.employee.pagetan.sdk.dept.dispatch.Bill;
import com.employee.pagetan.sdk.dept.staff.Charge;
import com.employee.pagetan.sdk.dept.staff.ProcessAbility;

/**
 * Created by pageTan on 2018/3/29.
 */

public class BaseDept {

    private final Charge<Bill> charge = new Charge<>();

    protected DeptContext context;

    public BaseDept(DeptContext context) {
        this.context = context;
    }




    public void registEmployee(ProcessAbility processAbility){
        charge.registEmployee(processAbility);
    }



    public void send(Bill bill) {
        send(bill, this);
    }
    public void send(Bill bill, BaseDept dept) {
        dept.post(bill);
    }

    public void post(Bill bill){
        charge.takeJob(bill, false);
    }

}
