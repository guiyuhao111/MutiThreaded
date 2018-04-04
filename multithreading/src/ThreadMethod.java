public class ThreadMethod {
    //线程中断
    public static void main(String[] args) {
        //MyThread不会真正中断只是会获得一个中断的状态
//        Thread thread=new Thread(new MyThread());
//        thread.start();
        //interrupt()必须和isInterrupted()配合起来中断程序
        MyThread1 thread1=new MyThread1();
        thread1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.interrupt();
        //thread.interrupt();

//        ThreadJoin2 threadJoin2=new ThreadJoin2();
//        threadJoin2.start();
//        try {
//            threadJoin2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        for (int i = 0; i < 10; i++) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("main"+i);
//        }
    }
}
class MyThread implements  Runnable{
    private int i=0;
    @Override
    public void run() {
        while(true){
            i++;
            System.out.println("当前线程:"+Thread.currentThread().getName()+","+"共运行了:"+i+"次");
        }
    }
}
class MyThread1 extends Thread{
    private int i=0;
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            i++;
            System.out.println("当前线程:"+Thread.currentThread().getName()+","+"共运行了:"+i+"次");
        }
        System.out.println("当前线程被中断");
        System.out.println("再次调用查看中断状态:"+Thread.currentThread().isInterrupted());
    }
}

class ThreadJoin2 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("222");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}