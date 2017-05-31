package qbecker.cartracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.util.HashMap;
import java.util.List;


public class CarDisplayActivity extends AppCompatActivity {

    private Button addRepairButton;
    private Button addTripButton;
    public ExpandableListView carListView;
    public CarDisplayExpandableListAdapter carExpandListViewAdapter;
    private String selectedCar;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_display);

        Intent intent = getIntent();
        selectedCar = intent.getStringExtra("Selected Car");

        carExpandListViewAdapter = new CarDisplayExpandableListAdapter(this, expandableListTitle, expandableListDetail, selectedCar);

        addRepairButton = (Button) findViewById(R.id.addRepairButton);
        addTripButton = (Button) findViewById(R.id.addTripButton);
        carListView = (ExpandableListView) findViewById(R.id.individualCarListView);
        carListView.setAdapter(carExpandListViewAdapter);
    }
}
