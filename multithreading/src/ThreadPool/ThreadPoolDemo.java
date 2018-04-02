package ThreadPool;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        BlockingDeque<Runnable> deque = new LinkedBlockingDeque<Runnable>(5);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 18, 1000, TimeUnit.SECONDS, deque);
        for (int i = 0; i < 23; i++) {
            executor.execute(new Thread(new MyThread()));
        }
        while(true){
            System.out.println("当前队列中的线程数量:"+deque.size());
            System.out.println("当前线程池中线程数量:"+executor.getActiveCount());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyThread implements Runnable {
    @Override
    public void run() {
        synchronized (ThreadPoolDemo.class){
            System.out.println("执行:" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
