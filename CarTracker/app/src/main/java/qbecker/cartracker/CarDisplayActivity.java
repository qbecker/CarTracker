package qbecker.cartracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView;


public class CarDisplayActivity extends AppCompatActivity {

    private Button addRepairButton;
    private Button addTripButton;
    public ExpandableListView carListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_display);
        addRepairButton = (Button) findViewById(R.id.addRepairButton);
        addTripButton = (Button) findViewById(R.id.addTripButton);
        carListView = (ExpandableListView) findViewById(R.id.individualCarListView);
    }
}
