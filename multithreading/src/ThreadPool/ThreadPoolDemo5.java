package ThreadPool;

import java.util.concurrent.*;

/*
* 同样是基于ThreadPoolExecutor可以指定核心线程数,最大任务线程的长度为整数的最大值
* */
public class ThreadPoolDemo5 {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService service= Executors.newScheduledThreadPool(4);
        //延迟三秒后执行该任务
        //service.schedule(new MyThread(),3, TimeUnit.SECONDS);
        //循环执行某项任务设置初始化时间和循环时间(多少分钟执行一次,是两项任务开始的时间间隔)
        //service.scheduleAtFixedRate(new MyThread(),2,5,TimeUnit.SECONDS);
        //循环执行某项任务设置,此延迟时间是相对于上一次任务执行完都到下一项任务开始执行的时间
        service.scheduleWithFixedDelay(new MyThread(),2,5,TimeUnit.SECONDS);
        while(true){
            Thread.sleep(1000);
            System.out.println(System.currentTimeMillis());
        }
    }
    static  class MyThread implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行");
        }
    }
}
