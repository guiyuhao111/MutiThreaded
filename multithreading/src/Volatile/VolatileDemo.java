package Volatile;

public class VolatileDemo {
    public static int i=0;
    public volatile static int k=0;
    static class ThreadDemo implements Runnable{
        @Override
        public void run() {
            for(int j=0;j<15;j++){
                i=i+1;
                //就算加了volatile关键字这段代码也不是线程安全的,因为下面这段操作本身就具备有原子性假如两个线程同时从内存中读取数据
                //就会读到同一份数据,并且会将同一个结果输出到内存中去
                k=k+1;
                System.out.println("当前线程"+Thread.currentThread().getName()+" i:"+i+" k:"+k);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        ThreadDemo threadDemo=new ThreadDemo();
        Thread thread=new Thread(threadDemo);
        Thread thread1=new Thread(threadDemo);
        thread.start();
        thread1.start();
    }
}

