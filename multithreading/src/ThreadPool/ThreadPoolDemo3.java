package ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
*
* newCachedThreadPool:基于ThreadPoolExecutor不创建核心线程,使任务线程延迟获取任务60秒
* */
public class ThreadPoolDemo3 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
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
