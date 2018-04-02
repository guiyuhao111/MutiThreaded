package Timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer定时任务
 * 一个Timer可以启动多个TimerTask
 * Timer是一种线程设施，用于安排以后在后台线程中执行的任务。
 * 可安排任务执行一次，或者定期重复执行，可以看成一个定时器，可以调度TimerTask。
 * TimerTask是一个抽象类，实现了Runnable接口，所以具备了多线程的能力。
 */
public class TimerDemo {
    public static void main(String[] args) {
        //若要创建两个并发执行的TimerTask写两个timer即可,若要循环执行两个Timer必须相互调用即可实现循环顺序执行
        new Timer().schedule(new Timer2(),2000);
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("时间:"+new Date().getSeconds());
        }
    }
}
class Timer1 extends TimerTask{

    @Override
    public void run() {
        System.out.println("爆炸1");
        new Timer().schedule(new Timer2(),2000);
    }
}
class Timer2 extends TimerTask{

    @Override
    public void run() {
        System.out.println("爆炸2");
        new Timer().schedule(new Timer1(),3000);
    }
}