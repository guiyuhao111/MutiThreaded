package test;

import java.util.ArrayList;
import java.util.List;
/*
 * 此类是一个生产者和消费者的模型
 */
public class ProductionAndConsumption
{
    public static void main(String[] args) {
        Plate plate = new Plate();
        //生产线程
        Thread t1 = new Thread(new Production(plate));
        //消费线程
        Thread t2=new Thread(new Consumption(plate));
        t1.start();
        t2.start();
    }
}
//实物
class Plate{
    private List<Object> eggs=new ArrayList<>();
    //获得实物的eggs
    public synchronized List<Object> getEggs() throws InterruptedException {
        if (eggs.size()<=0){
            //如果消费完进入阻塞状态
            wait();
        }
        //未消费完或者是被生产者唤醒
        eggs.remove(0);
        System.out.println("消费");
        notify();
        return eggs;
    }
    //生产eggs
    public synchronized void setEggs() throws InterruptedException {
        if (eggs.size()>=1){
            wait();
        }
        //eggs被消费完或者被消费者唤醒
        eggs.add(new Object());
        System.out.println("生产");
        notify();
    }
}
//生产任务
class Production implements Runnable{
    private Plate plate;
    public Production(Plate plate) {
        this.plate=plate;
    }

    @Override
    public void run() {
        try {
            plate.setEggs();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
//消费任务
class Consumption implements Runnable{
    private Plate plate;
    public Consumption(Plate plate) {
        this.plate=plate;
    }
    @Override
    public void run() {
        try {
            plate.getEggs();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
