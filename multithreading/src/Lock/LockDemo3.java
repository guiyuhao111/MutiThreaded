package Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//tryLock会尝试获取锁,如果没有获取到锁会尝试等待一段时间,如果在等待时间内没有获取到锁就不尝试获取锁而去执行其他代码
//这样的特性被称为可中断特行,synchronized就不具备这样的特性
//如果说是在获得锁阻塞期间被中断的话就会抛中断异常并且清除中断标记
public class LockDemo3 {
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread=new MyThread();
        Thread thread=new Thread(myThread,"A");
        Thread thread1=new Thread(myThread,"B");
        thread.start();
        thread1.start();
        Thread.sleep(1000);
        //
        thread.interrupt();
        thread1.interrupt();
    }

    static class MyThread implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("此处竞争锁");
                if (lock.tryLock(10, TimeUnit.SECONDS)) {
                    long now = System.currentTimeMillis();
                    System.out.println("此处获得锁");
                    while (System.currentTimeMillis() - now < 1500) {
                        if (!Thread.currentThread().isInterrupted()){

                        }
                    }
                }

            } catch (InterruptedException e) {
                //此处表明了该线程是在获得锁阻塞期间被中断且检查中断状态时发现中断标记被清除
                System.out.println("当前线程中断标记是否被清除?"+!Thread.currentThread().isInterrupted());
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("释放锁");
            }
            System.out.println("当前线程:"+Thread.currentThread().getName()+"执行完毕");
        }
    }
}
