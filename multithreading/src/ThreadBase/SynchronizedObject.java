package ThreadBase;

public class SynchronizedObject {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);
        thread.start();
    }
}

class MyThread implements Runnable {

    @Override
    public void run() {
        //this指的是当前类的实例对象也就是---new MyThread()
        synchronized (this) {
            System.out.println("第一次进入synchronized代码块");
            //synchronzied是一个可重入的同步锁,进入一个代码块中时还可以进入同一个锁对象的代码块且没有阻塞
            hello();
        }
    }

    public synchronized void hello() {
        System.out.println("第二次进入synchronized代码块");
    }
}