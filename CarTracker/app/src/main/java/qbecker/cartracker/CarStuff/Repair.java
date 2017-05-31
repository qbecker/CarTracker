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
    String uid;


    public Repair(String description, Double cost, String date, int id, String uid){
        this.description = description;
        this.cost = cost;
        this.date = date;
        this.id = id;
    }


    public Repair(){
        description = "No Description";
        cost = 0.0;
        date = "";
        id = 0;
        uid = "";

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
