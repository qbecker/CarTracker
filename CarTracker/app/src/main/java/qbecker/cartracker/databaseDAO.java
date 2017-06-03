package qbecker.cartracker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

import qbecker.cartracker.CarStuff.Repair;
import qbecker.cartracker.CarStuff.Trip;
import qbecker.cartracker.CarStuff.Car;

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

    public static Car GetCarByName(String name, Context parent){
        Car ret = new Car();
        SQLiteDatabase crsDB;
        Cursor cur;
        CarDB db = new CarDB(parent);
        try {
            crsDB = db.openDB();
            cur = crsDB.rawQuery("SELECT * FROM cars WHERE name = ?", new String[]{name});
            while(cur.moveToNext()){
                try{
                    ret.setId(cur.getString(0));
                    ret.setName(cur.getString(1));
                    ret.setDescription(cur.getString(2));
                    ret.setMake(cur.getString(3));
                    ret.setModel(cur.getString(4));
                    ret.setTotalMiles(Double.parseDouble(cur.getString(5)));
                    ret.setTotalCost(Double.parseDouble(cur.getString(6)));
                    ret.setMpg(Double.parseDouble(cur.getString(7)));

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
                    ret.setId(cur.getString(0));
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

    public static boolean InsertTrip(Trip trip, Context parent){
        boolean ret = true;
        CarDB db = new CarDB(parent);
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

    public static Repair GetRepair(String desc, String uid, Context parent){
        Repair ret = new Repair();
        SQLiteDatabase crsDB;
        Cursor cur;
        CarDB db = new CarDB(parent);
        try {
            crsDB = db.openDB();
            cur = crsDB.rawQuery("SELECT * FROM repairs WHERE description = ? AND uid = ?", new String[]{desc, uid});
            while(cur.moveToNext()){
                try{
                    ret.setId(Integer.parseInt(cur.getString(0)));
                    ret.setDescription(cur.getString(1));
                    ret.setCost(Double.parseDouble(cur.getString(2)));
                    ret.setDate(cur.getString(3));
                    ret.setUid(cur.getString(4));
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

    public static boolean InsertRepair(Repair repair, Context parent){
        boolean ret = true;
        CarDB db = new CarDB(parent);
        try {
            SQLiteDatabase crsDB = db.openDB();
            String insert = "insert or replace into  repairs VALUES('"+ repair.getId()
                    + "', '"+repair.getDescription()+"',  '" + String.valueOf(repair.getCost())
                    + "',  '" + String.valueOf(repair.getDate())
                    +"', '"+ repair.getUid() + "');";
            crsDB.execSQL(insert);
            crsDB.close();
        } catch (SQLException e) {
            e.printStackTrace();
            ret = false;
        }
        db.close();
        return ret;
    }

    public static boolean InsertCar(Car car, Context parent){
        boolean ret = true;
        CarDB db = new CarDB(parent);
        try {
            SQLiteDatabase crsDB = db.openDB();
            String insert = "insert or replace into  cars VALUES('"+ Utilities.CreateUID()
                    + "', '"+car.getName()+"',  '" + car.getDescription()
                    + "',  '" + car.getMake()
                    +"', '"+ car.getModel() +  "', '"+ Double.toString(car.getTotalMiles()) +
                    "', '"+ Double.toString(car.getTotalCost()) +
                    "', '"+ Double.toString(car.getMpg()) +"');";
            crsDB.execSQL(insert);
            crsDB.close();
        } catch (SQLException e) {
            e.printStackTrace();
            ret = false;
        }
        db.close();
        return ret;
    }

    public static boolean RemoveCarCompleately(Car car, Context parent){
        boolean ret = true;
        CarDB db = new CarDB(parent);
        try {
            SQLiteDatabase crsDB = db.openDB();
            String id = car.getId();
            String insert = "DELETE FROM cars where id = '"+id +"'; DELETE FROM trips where id = '"+ id+"'; " +
                    "DELETE FROM repairs where id = '"+id+"';";
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


