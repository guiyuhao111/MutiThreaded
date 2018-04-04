package Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//测试lock的方法lockInterruptibly(),如果当前线程在获取锁阻塞期间被中断则抛出异常,并且清除标记位
public class LockDemo4 {
    static private Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread, "A");
        Thread thread1 = new Thread(myThread, "B");
        thread.setPriority(9);
        thread1.start();
        thread.start();
        Thread.sleep(4000);
        thread1.interrupt();
        thread.interrupt();
    }

    static class MyThread implements Runnable {

        @Override
        public void run() {
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                System.out.println("当前线程中断标记是否被清除?"+!Thread.currentThread().isInterrupted());
                e.printStackTrace();
            }
            try {
                long now = System.currentTimeMillis();
                System.out.println("当前线程:" + Thread.currentThread().getName());
                while (System.currentTimeMillis() - now < 5000) {
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
