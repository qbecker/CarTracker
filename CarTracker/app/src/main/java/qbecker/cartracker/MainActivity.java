package qbecker.cartracker;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener{
    SQLiteDatabase crsDB;
    private String[] names;
    public ArrayAdapter adapter;
    private  ListView carList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carList = (ListView) findViewById(R.id.carlistview);
        names = new String[]{"No Cars, Yet"};
        this.prepareAdapter();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1 , new ArrayList<>(Arrays.asList(names)));
        carList.setAdapter(adapter);
        setTitle("select car");
        carList.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){


        Intent displayCar = new Intent(this, CarDisplayActivity.class);
        String temp = carList.getItemAtPosition(position).toString();
        displayCar.putExtra("selectedCar", temp);
        this.startActivityForResult(displayCar,1);
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

    private void prepareAdapter(){

        ArrayList<String> al = getAllPlaces();
        names =  al.toArray(new String[al.size()]);
    }
}
