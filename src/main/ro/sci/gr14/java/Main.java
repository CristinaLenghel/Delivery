package main.ro.sci.gr14.java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {
    public static void main(String... args) throws Exception {
        FileService<Package> fsp = new FileService<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-mm-dd");
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        long revenueValue=0;

        //Load packages information from inFile.txt
        LinkedList<Package> packageList = fsp.readFromFile("src\\resources\\inFile.txt", s -> {
            try {
                return new Package(s);
            } catch (ParseException ex) {
                System.out.println("Exceptie la parsare String to Package.");
            }
            return null;
        });

        //group packages based on the target location and delivery date.
        Map<String, List<Package>> packageGroups = packageList.stream()
                .collect(Collectors.groupingBy(p -> p.getLocation() + simpleDateFormat.format(p.getDeliveryDate())));

        //for all package group create deliveryTread an calculate revenue value
        for (List<Package> pl:packageGroups.values()) {
            Thread thread = new Thread(new DeliveryThread(pl.stream().collect(Collectors.toCollection(LinkedList<Package>::new))));
            executorService.submit(thread);
            revenueValue+=pl.get(0).getDistance();
        }
        executorService.shutdown();

        while (!executorService.isTerminated()){
            Thread.sleep(1000);
        }

        System.out.println("Total value of all delivered packages:" +
               packageList.stream().mapToLong(p->p.getValue()).sum());

        System.out.println("Total value of the revenue computed for all groups delivered: "+revenueValue);

    }
}

