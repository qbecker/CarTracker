package qbecker.cartracker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

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

    public static ArrayList<String> GetAllRepairsForCar(String name, Context parent){
        SQLiteDatabase crsDB;
        Cursor cur;
        CarDB db = new CarDB(parent);
        ArrayList<String> al = new ArrayList<String>();
        try {

            crsDB = db.openDB();
            cur = crsDB.rawQuery("SELECT repairs.description, repairs.cost, repairs.date FROM repairs  JOIN cars WHERE cars.name = ? AND cars.id = repairs.id;", new String[]{name});
            while(cur.moveToNext()){
                try{
                    al.add(cur.getString(0));
                    android.util.Log.w(parent.getClass().getSimpleName(), cur.getString(0));
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

    public static ArrayList<String> GetAllTripsForCar(String name, Context parent){
        SQLiteDatabase crsDB;
        Cursor cur;
        CarDB db = new CarDB(parent);
        ArrayList<String> al = new ArrayList<String>();
        try {
            crsDB = db.openDB();
            cur = crsDB.rawQuery("select name from cars;", new String[]{name});

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
}
