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

    public static Trip GetTrip(String desc, String uid, Context parent){
        Trip ret = new Trip();
        SQLiteDatabase crsDB;
        Cursor cur;
        CarDB db = new CarDB(parent);
        try {
            crsDB = db.openDB();
            cur = crsDB.rawQuery("SELECT * FROM trips WHERE description = ? AND uid = ?", new String[]{desc, uid});
            while(cur.moveToNext()){
                try{
                    ret = new Trip();
                    ret.setId(Integer.parseInt(cur.getString(0)));
                    ret.setDescription(cur.getString(1));
                    ret.setMiles(Double.parseDouble(cur.getString(2)));
                    ret.setCost(Double.parseDouble(cur.getString(3)));
                    ret.setDate(cur.getString(4));
                    ret.setUid(cur.getString(5));

                }catch(Exception ex){
                    android.util.Log.w(parent.getClass().getSimpleName(),"exception stepping thru cursor"+ex.getMessage());
                }
            }
            crsDB.close();
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

    public static boolean InsertTrip(Trip trip, Context context){
        boolean ret = true;
        CarDB db = new CarDB(context);

        try {
            SQLiteDatabase crsDB = db.openDB();
            String insert = "insert or replace into  trips VALUES('"+ trip.getId() + "', '"+trip.getDescription()+"',  '" + String.valueOf(trip.getMiles()) + "',  '" + String.valueOf(trip.getCost()) +"', '"+ trip.getDate() + "', '" + trip.getUid() + "');";
            crsDB.execSQL(insert);
            crsDB.close();
        } catch (SQLException e) {
            e.printStackTrace();
            ret = false;
        }
        db.close();
        return ret;
    }
}