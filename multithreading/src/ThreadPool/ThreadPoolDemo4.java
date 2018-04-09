package ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
* newSingleThreadExecutor:只创建一个核心线程,任务的队列长度为整数的最大值
* */
public class ThreadPoolDemo4 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i=0;i<8;i++){
            service.submit(new MyThread());
        }
    }
    static class MyThread implements Runnable{

        @Override
        public void run() {
            long now=System.currentTimeMillis();
            while(System.currentTimeMillis()-now<3000){

            }
            System.out.println("当前线程执行完成");
        }
    }
}
