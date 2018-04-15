package com.employee.pagetan.sdk.dept.staff;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;

import com.employee.pagetan.sdk.dept.dispatch.Bill;
import com.employee.pagetan.sdk.dept.dispatch.queue.Queue;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pageTan on 2018/4/2.
 */

public class Employee {

    protected Object LOCK = new Object();

    private static AtomicInteger NEXT_ID = new AtomicInteger(1);

    protected Queue<Bill> pendingJob;
    private int number;
    private boolean isProcessing = false; // 是否处于正在处理的状态
    private int busyDegree = 0; // 忙碌程度

    private HandlerThread handlerThread;
    private Handler handler;
    private ProcessAbility processAbility;

    public Employee(ProcessAbility processAbility) {
        this(NEXT_ID.get(), processAbility);
    }

    public Employee(int number, ProcessAbility processAbility) {
        this.number = number;
        this.processAbility = processAbility;
        pendingJob = new Queue<Bill>(number);
        handlerThread = new HandlerThread(number+"", Thread.NORM_PRIORITY);
        handlerThread.start();

        // Wait for Looper ready
        while (handlerThread.getLooper() == null) {

        }

        handler = new Handler(handlerThread.getLooper());

    }

    public void takeJob(Bill bill){
        pendingJob.getQueue().add(bill);
    }

    public void takeJob(Bill bill, boolean isFirst){
        pendingJob.getQueue().add(bill);
        updateBusyDegree();
        if (!isProcessing()) { // 当前没有处理任务
            deelJob(process,0);
        }
    }
    public JobCancellable deelJob(final Runnable job, long delay){
        final Object o = new Object();
        handler.postAtTime(job, o, SystemClock.uptimeMillis() + delay);
        return new JobCancellable() {
            @Override
            public void cancel() {
                handler.removeCallbacks(job, o);
            }
        };
    }


    public int getNumber() {
        return number;
    }

    public boolean isProcessing() {
        return isProcessing;
    }

    public void setProcessing(boolean processing) {
        isProcessing = processing;
    }

    public void updateBusyDegree() { // 空闲度暂时用任务队列长度来计算
        this.busyDegree = pendingJob.getQueue().size();
    }

    public int getBusyDegree(){
        return busyDegree;
    }

    public void setProcessAbility(ProcessAbility processAbility) {
        this.processAbility = processAbility;
    }

    private Runnable process = new Runnable() {
        @Override
        public void run() {
            synchronized (LOCK) {
                setProcessing(true);
            }
            while (pendingJob.getQueue().size() > 0) {
                Bill bill = pendingJob.getQueue().remove(0);
                if (processAbility != null) {
                    processAbility.process(bill);
                }
                updateBusyDegree();
            }
            synchronized (LOCK) {
                setProcessing(false);
            }
        }
    };


}
