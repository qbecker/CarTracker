package qbecker.cartracker;

import qbecker.cartracker.CarStuff.*;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import java.util.HashMap;
import java.util.List;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;


public class CarDisplayActivity extends AppCompatActivity {

    private Button addRepairButton;
    private Button addTripButton;
    public ExpandableListView carListView;
    public CarDisplayExpandableListAdapter carExpandListViewAdapter;
    public String selectedCar;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_display);
        carListView = (ExpandableListView) findViewById(R.id.individualCarListView);
        Intent intent = getIntent();
        selectedCar = intent.getStringExtra("Selected Car");
        setTitle("Details for " + selectedCar);

        carExpandListViewAdapter = new CarDisplayExpandableListAdapter(this, expandableListTitle, expandableListDetail, selectedCar);
        addRepairButton = (Button) findViewById(R.id.addRepairButton);
        addTripButton = (Button) findViewById(R.id.addTripButton);

        carListView.setAdapter(carExpandListViewAdapter);


        addTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CarDisplayActivity.this, AddTripActivity.class);
                i.putExtra("parent name", selectedCar);
                CarDisplayActivity.this.startActivity(i);
            }
        });
    }

    @Override
    public void onResume() {

        super.onResume();
        carExpandListViewAdapter = new CarDisplayExpandableListAdapter(this, expandableListTitle, expandableListDetail, selectedCar);
        carListView.setAdapter(carExpandListViewAdapter);
    }

    @Override
    public void onRestart() {
        super.onRestart();
        carListView.setAdapter(carExpandListViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        android.util.Log.d(this.getClass().getSimpleName(), "called onCreateOptionsMenu()");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.car_display_menu, menu);
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
            case R.id.removeCarActionButton:
                createDialog();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void createDialog(){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Deleting car")
                .setMessage("Are you sure you want to erase this car? It cannot be undone")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean compleate = databaseDAO.RemoveCarCompleately(
                                databaseDAO.GetCarByName(selectedCar, CarDisplayActivity.this),
                                CarDisplayActivity.this);
                        if(compleate){
                            finish();

                        }

                    }
                })
                .setNegativeButton("No", null)
                .show();
    }


}
