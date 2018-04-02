package ThreadPool;

import java.util.concurrent.*;

/**
 * 测试线程池中的饱和策略
 */
public class ThreadPoolDemo1 {
    public static void main(String[] args) {
        //四种多线程饱和策略
        //1.当要获取的线程超过当前线程池设置的最大线程数量时会抛出此异常
        RejectedExecutionHandler handler=new ThreadPoolExecutor.AbortPolicy();

//         RejectedExecutionHandler handler1=new ThreadPoolExecutor.CallerRunsPolicy();
//        RejectedExecutionHandler handler2=new ThreadPoolExecutor.DiscardOldestPolicy();
//        RejectedExecutionHandler handler3=new ThreadPoolExecutor.DiscardPolicy();
        BlockingDeque<Runnable> deque = new LinkedBlockingDeque<Runnable>(5);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 18, 10000, TimeUnit.SECONDS, deque);
        executor.allowCoreThreadTimeOut(false);
        for (int i = 0; i < 18; i++) {
            executor.execute(new Thread(new MyThread1()));
        }
        while(true){
            System.out.println("当前队列中的线程数量:"+deque.size());
            System.out.println("当前线程池中活跃线程数量:"+executor.getActiveCount());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class MyThread1 implements Runnable {
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
