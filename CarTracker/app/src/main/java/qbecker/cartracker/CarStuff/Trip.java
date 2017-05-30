package qbecker.cartracker.CarStuff;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by qbecker on 5/27/17.
 */

public class Trip implements Serializable {
    String date;
    Double miles;
    Double cost;
    int id;

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
        date = "";
        id = 0;

    }

    public Trip(String jsonString){
        try {
            JSONObject json = new JSONObject(jsonString);
            miles = json.getDouble("miles");
            cost = json.getDouble("cost");
            id = json.getInt("id");
        } catch (Exception e) {
            android.util.Log.w(this.getClass().getSimpleName(), "Error reading from JSON");
        }

    }

    public  Trip(Double miles, Double cost, String date, int id){
        this.miles = miles;
        this.cost = cost;
        this.date = date;
        this.id = id;
    }
}
