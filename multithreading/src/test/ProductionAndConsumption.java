package test;

import java.util.ArrayList;
import java.util.List;

public class ProductionAndConsumption
{
    public static void main(String[] args) {
        Plate plate = new Plate();
        Thread t1 = new Thread(new Production(plate));
        Thread t2=new Thread(new Consumption(plate));
        t1.start();
        t2.start();
    }
}
class Plate{
    private List<Object> eggs=new ArrayList<>();

    public synchronized List<Object> getEggs() throws InterruptedException {
        if (eggs.size()<=0){
            wait();
        }
        eggs.remove(0);
        System.out.println("消费");
        notify();
        return eggs;
    }

    public synchronized void setEggs() throws InterruptedException {
        if (eggs.size()>=1){
            wait();
        }
        eggs.add(new Object());
        System.out.println("生产");
        notify();
    }
}
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