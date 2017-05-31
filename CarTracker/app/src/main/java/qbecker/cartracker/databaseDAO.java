package qbecker.cartracker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

import qbecker.cartracker.CarStuff.Repair;
import qbecker.cartracker.CarStuff.Trip;

/**
 * Created by qbecker on 5/30/17.
 */

public class databaseDAO {


    //method to get list of all cars for main page
    public static ArrayList<String> getAllCars(Context parent){
        SQLiteDatabase crsDB;
        Cursor cur;
        CarDB db = new CarDB(parent);
        ArrayList<String> al = new ArrayList<String>();
        try {
            crsDB = db.openDB();
            cur = crsDB.rawQuery("select name from cars;", new String[]{});

            while(cur.moveToNext()){
                try{
                    al.add(cur.getString(0));
                }catch(Exception ex){
                    android.util.Log.w(parent.getClass().getSimpleName(),"exception stepping thru cursor"+ex.getMessage());
                }
            }
            crsDB.close();
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return al;
    }

    public static ArrayList<Repair> GetAllRepairsNamesForCar(String name, Context parent){
        SQLiteDatabase crsDB;
        Cursor cur;
        CarDB db = new CarDB(parent);
        ArrayList<Repair> al = new ArrayList<Repair>();
        try {

            crsDB = db.openDB();
            cur = crsDB.rawQuery("SELECT repairs.description, repairs.date, repairs.uid FROM repairs  JOIN cars WHERE cars.name = ? AND cars.id = repairs.id;", new String[]{name});
            while(cur.moveToNext()){
                try{
                    Repair rep = new Repair();
                    rep.setDescription(cur.getString(0));
                    rep.setDate(cur.getString(1));
                    rep.setUid(cur.getString(2));
                    al.add(rep);
                }catch(Exception ex){
                    android.util.Log.w(parent.getClass().getSimpleName(),"exception stepping thru cursor"+ex.getMessage());
                }
            }
            crsDB.close();
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return al;
    }

    public static ArrayList<Trip> GetAllTripNamesForCar(String name, Context parent){
        SQLiteDatabase crsDB;
        Cursor cur;
        CarDB db = new CarDB(parent);
        ArrayList<Trip> al = new ArrayList<Trip>();
        try {
            crsDB = db.openDB();
            cur = crsDB.rawQuery("SELECT trips.description, trips.uid, trips.date FROM trips  JOIN cars WHERE cars.name = ? AND cars.id = trips.id", new String[]{name});
            while(cur.moveToNext()){
                try{
                    Trip trip = new Trip();
                    trip.setDescription(cur.getString(0));
                    trip.setUid(cur.getString(1));
                    trip.setDate(cur.getString(2));
                    al.add(trip);
                }catch(Exception ex){
                    android.util.Log.w(parent.getClass().getSimpleName(),"exception stepping thru cursor"+ex.getMessage());
                }
            }
            crsDB.close();
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return al;
    }
}
