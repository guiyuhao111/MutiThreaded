package Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//tryLock()方法没有获取到锁时不会被阻塞
public class LockDemo2 {
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread, "线程A");
        Thread thread1 = new Thread(myThread, "线程B");
        thread.start();
        thread1.start();
    }

    static class MyThread implements Runnable {
        @Override
        public void run() {
            //当第一个线程进来后,会获得锁,第二个线程进来后会尝试获得锁,如果没有获得锁不会进入阻塞状态不会运行同步代码
            if (lock.tryLock()) {
                try {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("当前线程:" + Thread.currentThread().getName());
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
