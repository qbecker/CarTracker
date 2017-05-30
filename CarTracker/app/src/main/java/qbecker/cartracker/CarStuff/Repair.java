package qbecker.cartracker.CarStuff;

import java.io.Serializable;

/**
 * Created by qbecker on 5/27/17.
 */

public class Repair implements Serializable {
    String description;
    Double cost;
    String date;
    int id;

    public Repair(){
        description = "No Description";
        cost = 0.0;
        date = "";
        id = 0;

    }

    public Repair(String description, Double cost, String date, int id){
        this.description = description;
        this.cost = cost;
        this.date = date;
        this.id = id;
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
