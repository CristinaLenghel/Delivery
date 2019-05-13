package main.ro.sci.gr14.java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Package {
    private String location;
    private long distance;
    private long value;
    private Date deliveryDate;

    public Package(){
        location="";
        distance=0;
        value=0;
        deliveryDate=null;
    }

    public Package(String packageString) throws ParseException {
        String[] buff=packageString.split(",");
        this.location=buff[0];
        this.distance=Long.parseLong(buff[1]);
        this.value=Long.parseLong(buff[2]);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        deliveryDate = formatter.parse(buff[3]);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return "Package{" +
                "location='" + location + '\'' +
                ", distance=" + distance +
                ", value=" + value +
                ", deliveryDate=" + deliveryDate +
                '}';
    }
}
