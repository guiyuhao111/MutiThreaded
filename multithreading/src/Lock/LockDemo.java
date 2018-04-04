package Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//lock机制
//lock()方法如果在阻塞期间被中断的话,这种中断是无效的.
public class LockDemo {
    private static Lock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread,"A");
        Thread thread1=new Thread(myThread,"B");
        thread1.start();
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
        thread1.interrupt();
    }

    static class MyThread implements Runnable {
        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"此处获得锁");
                long now=System.currentTimeMillis();
                int i=1;
                while (System.currentTimeMillis() - now < 1500) {
                    if (!Thread.currentThread().isInterrupted()&&i==1){
                        System.out.println(Thread.currentThread().getName()+"此处被中断");
                        i++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName()+"此处释放锁");
            }
        }
    }
}
