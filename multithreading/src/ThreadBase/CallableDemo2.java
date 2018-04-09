package ThreadBase;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo2 {
    public static void main(String[] args) {
        Callable callable=new MyCallable();
        FutureTask<Integer> futureTask=new FutureTask<Integer>(callable);
        Thread thread=new Thread(futureTask);
        thread.start();
        try {
            futureTask.cancel(true);
            Integer reback = futureTask.get();
            System.out.println(reback);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    static class MyCallable implements Callable{

        @Override
        public Integer call() throws Exception {
            long now=System.currentTimeMillis();
            while(System.currentTimeMillis()-now<3000){
            }
            return 5;
        }
    }
}
