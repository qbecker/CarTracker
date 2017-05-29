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
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getMpg() {
        return mpg;
    }

    public void setMpg(Double mpg) {
        this.mpg = mpg;
    }

    public Double getTotalMiles() {
        return totalMiles;
    }

    public void setTotalMiles(Double totalMiles) {
        this.totalMiles = totalMiles;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Trip[] getTotalTrips() {
        return totalTrips;
    }

    public void setTotalTrips(Trip[] totalTrips) {
        this.totalTrips = totalTrips;
    }

    public Repair[] getTotalRepairs() {
        return totalRepairs;
    }

    public void setTotalRepairs(Repair[] totalRepairs) {
        this.totalRepairs = totalRepairs;
    }


}
