package qbecker.cartracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import qbecker.cartracker.CarStuff.Car;
import qbecker.cartracker.CarStuff.Trip;

public class AddTripActivity extends AppCompatActivity {

    private EditText descriptionEditText;
    private EditText milesEditText;
    private EditText costEditText;
    private EditText dateEditText;

    private Button saveButton;
    private Button cancelButton;

    private Car parentCar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        setTitle("Add New Trip");

        descriptionEditText = (EditText) findViewById(R.id.addTripDescriptionEditText);
        milesEditText = (EditText) findViewById(R.id.addTripMilesEditText);
        costEditText = (EditText) findViewById(R.id.addTripCostEditText);
        dateEditText = (EditText) findViewById(R.id.addTripDateEditText);

        saveButton = (Button) findViewById(R.id.addTripSaveButton);
        cancelButton = (Button) findViewById(R.id.addTripCancelButton);

        Intent received = getIntent();
        Bundle b = received.getExtras();
        if(b != null){
            parentCar = databaseDAO.GetCarByName(b.getString("parent name"),this);
            setTitle("Add New Trip for " + parentCar.getName());
        }else{
            finish();
        }


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent back = new Intent(AddTripActivity.this,CarDisplayActivity.class);
                back.putExtra("Selected Car", parentCar.getName());
                AddTripActivity.this.startActivity(back);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Trip trip = new Trip();
                trip.setDescription(descriptionEditText.getText().toString());
                trip.setDate(dateEditText.getText().toString());
                trip.setCost(Double.parseDouble(costEditText.getText().toString()));
                trip.setMiles(Double.parseDouble(milesEditText.getText().toString()));
                trip.setId(parentCar.getId());
                trip.setUid(Utilities.CreateUID());
                boolean success = databaseDAO.InsertTrip(trip, AddTripActivity.this);
                if(success){
                    finish();

                }else{

                }
            }
        });
    }
}
