package qbecker.cartracker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.sql.SQLException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase crsDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public ArrayList<String> getAllPlaces(){

        CarDB db = new CarDB((Context)this);
        try {
            crsDB = db.openDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cur = crsDB.rawQuery("select name from cars;", new String[]{});
        ArrayList<String> al = new ArrayList<String>();
        while(cur.moveToNext()){
            try{
                al.add(cur.getString(0));
            }catch(Exception ex){
                android.util.Log.w(this.getClass().getSimpleName(),"exception stepping thru cursor"+ex.getMessage());
            }
        }
        crsDB.close();
        db.close();
        return al;

    }
}
