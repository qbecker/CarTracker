package qbecker.cartracker.CarStuff;

/**
 * Created by qbecker on 5/27/17.
 */

public class Car {
    String name;
    String make;
    String model;
    Double mpg;
    Double totalMiles;
    Double totalCost;
    Trip[] totalTrips;
    Repair[] totalRepairs;


    public Car(){
        name = "New Car";
        make = "Unknown Make";
        model  = "Uknown Model";
        mpg = 0.0;
        totalMiles = 0.0;
    }

    public Car(String name, String make, String model,
               Double mpg, Double totalMiles, Double totalCost,
               Trip[] totalTrips, Repair[] totalRepairs){
        this.name = name;
        this.model = model;
        this.make = make;
        this.mpg = mpg;
        this.totalMiles = totalMiles;
        this.totalTrips = totalTrips;
        this.totalRepairs = totalRepairs;
        this.totalCost = totalCost;
    }
}
