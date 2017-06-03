package qbecker.cartracker.CarStuff;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by qbecker on 5/27/17.
 */

public class Car implements Serializable {
    String name;
    String make;
    String model;
    String description;
    Double mpg;
    Double totalMiles;
    Double totalCost;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Car(String jsonString){
        try{
            JSONObject json = new JSONObject(jsonString);
            name = json.getString("name");
            description = json.getString("description");
            make = json.getString("make");
            model = json.getString("model");
            mpg = json.getDouble("mpg");
            totalMiles = json.getDouble("totalMiles");
            totalCost = json.getDouble("totalCost");
            id = json.getString("id");

        }catch(Exception e){
            android.util.Log.w(this.getClass().getSimpleName(), "Error reading from JSON");
        }
    }

    public Car(){
        name = "New Car";
        make = "Unknown Make";
        model  = "Unknown Model";
        description = "No description";
        mpg = 0.0;
        totalMiles = 0.0;
        totalCost = 0.0;

    }

    public Car(String name, String make, String model,
               Double mpg, Double totalMiles, Double totalCost,
               String description){
        this.name = name;
        this.model = model;
        this.make = make;
        this.mpg = mpg;
        this.totalMiles = totalMiles;
        this.totalCost = totalCost;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
