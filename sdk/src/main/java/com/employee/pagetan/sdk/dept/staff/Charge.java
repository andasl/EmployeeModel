package com.employee.pagetan.sdk.dept.staff;

import com.employee.pagetan.sdk.dept.dispatch.Bill;

import java.util.HashMap;

/**
 * 主管负责接受任务请求，并分发给具体的employ执行
 * 空闲员工队列，
 * Created by pageTan on 2018/4/2.
 */

public class Charge<T> extends Employee{
    private static final int CHARGE_NUMBER = 0;

    private HashMap<Integer, Employee> employeeHashMap = new HashMap<>();

    public Charge() {
        super(CHARGE_NUMBER, null); // 本来想再构造方法中设置进去，但是不能调用非静态方法
        setProcessAbility(new ProcessAbility() {
            @Override
            public void process(Bill bill) {
                int employNumber = bill.getEmployNumber();
                Employee employee = findSuitableEmploy(employNumber);
                employee.takeJob(bill, false);// 管理员的任务是负责分发个具体的employee
            }
        });
    }


    public Employee registEmployee(ProcessAbility processAbility){
        Employee employee = new Employee(processAbility);
        employeeHashMap.put(employee.getNumber(), employee);
        return employee;
    }


    public Employee findSuitableEmploy(int employNumber){
        int realNumber = employNumber;
        Employee workEmployee = null;
        if (employNumber > 0) { // 指定一个人做
            int index = realNumber % employeeHashMap.keySet().size();
            Object[] numberArray =employeeHashMap.keySet().toArray();
            if (numberArray.length > index) {
                realNumber = (int) numberArray[index];
                workEmployee = employeeHashMap.get(realNumber);
            }
        }
        if (workEmployee == null ) {
            // 取最空闲的employee来处理
            for (Employee employee : employeeHashMap.values()) {
                if (workEmployee == null ||
                        workEmployee.getBusyDegree() > employee.getBusyDegree()) {
                    workEmployee = employee;
                }
            }
        }
        if (workEmployee == null) { // 如果没有找到一个员工，那么，该招人了。
            workEmployee = registEmployee(null);
        }

        return workEmployee;
    }



}
