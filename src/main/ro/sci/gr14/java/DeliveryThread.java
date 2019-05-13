package main.ro.sci.gr14.java;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class DeliveryThread implements Runnable {
    private LinkedList<Package> packageList;

    public DeliveryThread(List <Package> packageList){
        this.packageList=(LinkedList<Package>) packageList;
    }

    public void run(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        try {
            if (packageList.size() > 0) {
                Package pack = packageList.get(0);
                System.out.println("[Delivering for <" + pack.getLocation() +
                        "> and date <" + simpleDateFormat.format(pack.getDeliveryDate()) +
                        "> in <" + pack.getDistance() + "> seconds]");
                Thread.sleep(pack.getDistance()*1000);
            }
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
