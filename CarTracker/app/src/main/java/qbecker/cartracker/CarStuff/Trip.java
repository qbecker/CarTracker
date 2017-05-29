package qbecker.cartracker.CarStuff;

/**
 * Created by qbecker on 5/27/17.
 */

public class Trip {
    Double miles;
    Double cost;

    public Double getMiles() {
        return miles;
    }

    public void setMiles(Double miles) {
        this.miles = miles;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Trip(){
        miles = 0.0;

        cost = 0.0;
    }

    public  Trip(Double miles, Double cost){
        this.miles = miles;
        this.cost = cost;
    }
}
