package qbecker.cartracker;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener{

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
    public boolean onCreateOptionsMenu(Menu menu){
        android.util.Log.d(this.getClass().getSimpleName(), "called onCreateOptionsMenu()");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        android.util.Log.d(this.getClass().getSimpleName(), "called onOptionsItemSelected()");
        switch (item.getItemId()) {
            case R.id.addCarActionButton:
                Intent addCar = new Intent(this, AddCarActivity.class);
                this.startActivityForResult(addCar, 1);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.w(this.getClass().getSimpleName(), "OnStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        this.prepareAdapter();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1 , new ArrayList<>(Arrays.asList(names)));
        carList.setAdapter(adapter);
        Log.w(this.getClass().getSimpleName(), "onResume");
    }

    @Override
    public void onRestart() {
        android.util.Log.w("onResert", "called");
        super.onRestart();
        this.prepareAdapter();
        this.carList.setAdapter(adapter);
        finish();
        startActivity(getIntent());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Intent displayCar = new Intent(this, CarDisplayActivity.class);
        String temp = carList.getItemAtPosition(position).toString();
        displayCar.putExtra("Selected Car", temp);
        this.startActivityForResult(displayCar,1);
    }


    private void prepareAdapter(){
        ArrayList<String> al = databaseDAO.getAllCars(this);
        names =  al.toArray(new String[al.size()]);
    }
}
