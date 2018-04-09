package Timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 循环执行并使用相关Api
 */
public class TimerDemo1 {
    public static void main(String[] args) throws InterruptedException {
        Timer timer=new Timer();
        timer.schedule(new TimerCir(),2000,3000);
        while(true){
            System.out.println("时间:"+new Date().getSeconds());
            Thread.sleep(1000);
        }
    }
}
class TimerCir extends TimerTask{
    private int i=0;
    @Override
    public void run() {
        i++;
        System.out.println("执行");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(i==10){
            //cancel取消当时执行任务
            cancel();
        }
    }
}
