package ThreadPool;


import com.sun.xml.internal.bind.v2.model.core.EnumConstant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * newFixedThreadPool学习
 * newFixedThreadPool创建固定大小的线程池
 * 即是核心线程数跟最大线程数相等,而且任务队列为最大值
 *
 * 由此可见:newFixedThreadPool的最大优点是可以快速创建一定大小的线程池
 * 但是传统的ThreadPoolExecutor更灵活一点
 *
 * shutdown:会在当前线程池所有任务执行完关闭线程池
 * shutdownNow:停止任务队列里的任务,并在执行完当前线程后返回任务队列里的任务并关闭线程池
 * isTerminated():调用shutdown和shutdownNow后看当前任务是否都执行完成
 */
public class ThreadPoolDemo2 {
    public static void main(String[] args) throws InterruptedException {
        boolean shutDownFlag = false;
        ExecutorService service = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 8; i++) {
            service.submit(new MyThread());
        }
        shutDownFlag = service.isShutdown();
        System.out.println(shutDownFlag);
        service.shutdownNow();
        shutDownFlag = service.isShutdown();
        System.out.println(shutDownFlag);
        while (!service.isTerminated()) {
        }
        System.out.println("当前线程执行完所有任务并且已退出");
    }

    public static class MyThread implements Runnable {
        @Override
        public void run() {
            long now = System.currentTimeMillis();
            while (System.currentTimeMillis() - now < 10000) {
                System.out.println("执行");
            }
        }
    }
}
