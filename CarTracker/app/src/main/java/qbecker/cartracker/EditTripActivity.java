package qbecker.cartracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import qbecker.cartracker.CarStuff.Trip;

public class EditTripActivity extends AppCompatActivity {

    private Button saveButton;

    private TextView mpgTextView;

    private EditText desEditText;
    private EditText costEditText;
    private EditText milesEditText;
    private EditText dateEditText;

    private Trip selectedTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip);
        setTitle("Edit Trip");

        saveButton = (Button) findViewById(R.id.tripSaveButton);

        mpgTextView = (TextView) findViewById(R.id.tripMpgTextView);

        desEditText = (EditText) findViewById(R.id.tripDescriptionEditText);
        costEditText = (EditText) findViewById(R.id.tripCostEditText);
        milesEditText = (EditText) findViewById(R.id.tripMilesEditText);
        dateEditText = (EditText) findViewById(R.id.tripDateEditText);
        selectedTrip = new Trip();
        Intent received = getIntent();
        Bundle b = received.getExtras();
        if(b != null){
            selectedTrip = databaseDAO.GetTrip(b.getString("desc"), b.getString("uid"), this);
        }
        fillEditTexts(selectedTrip);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFromEditTexts(selectedTrip);
                boolean success = databaseDAO.InsertTrip(selectedTrip,EditTripActivity.this);
                if(success){
                    finish();
                }
            }
        });
    }

    private void fillEditTexts(Trip trip) {
        desEditText.setText(trip.getDescription());
        costEditText.setText(Double.toString(trip.getCost()));
        milesEditText.setText(Double.toString(trip.getMiles()));
        dateEditText.setText(trip.getDate());
    }

    
    //// TODO: Handel error checking for input types
    private void setFromEditTexts(Trip trip){
        trip.setDescription(desEditText.getText().toString());
        trip.setMiles(Double.parseDouble(milesEditText.getText().toString()));
        trip.setCost(Double.parseDouble(costEditText.getText().toString()));
        trip.setDate(dateEditText.getText().toString());
    }
}
