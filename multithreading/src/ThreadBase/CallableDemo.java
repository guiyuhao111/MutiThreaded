package ThreadBase;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo {
    public static void main(String[] args) {
        Callable callable = new MyCallable();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
        Thread thread=new Thread(futureTask);
        thread.start();
        try {
            Integer callBack= futureTask.get();
            System.out.println(callBack);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class MyCallable implements Callable {
        @Override
        public Integer call() throws Exception {
            return 5;
        }
    }
}
