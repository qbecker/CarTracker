package qbecker.cartracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import java.util.HashMap;
import java.util.List;


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
        this.carListView.setAdapter(carExpandListViewAdapter);
    }

    @Override
    public void onRestart() {
        super.onRestart();

        this.carListView.setAdapter(carExpandListViewAdapter);
        finish();
        startActivity(getIntent());
    }


}
