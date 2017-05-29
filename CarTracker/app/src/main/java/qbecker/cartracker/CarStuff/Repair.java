package qbecker.cartracker.CarStuff;

/**
 * Created by qbecker on 5/27/17.
 */

public class Repair {
    String description;
    Double cost;

    public Repair(){
        description = "No Description";
        cost = 0.0;

    }

    public Repair(String description, Double cost){
        this.description = description;
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }


}
