package qbecker.cartracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import qbecker.cartracker.CarStuff.Car;


public class AddCarActivity extends AppCompatActivity {

    private Button saveButton;
    private Button cancelButton;

    private EditText nameEditText;
    private EditText descriptionEditText;
    private EditText makeEditText;
    private EditText modelEditText;
    private EditText milesEditText;

    private Car newCar;

    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        setTitle("Add New Car");

        saveButton = (Button) findViewById(R.id.addCarSaveButton);
        cancelButton = (Button) findViewById(R.id.addCarCancelButton);

        nameEditText = (EditText) findViewById(R.id.addCarNameEditText);
        descriptionEditText = (EditText) findViewById(R.id.addCarDescriptionEditText);
        makeEditText = (EditText) findViewById(R.id.addCarMakeEditText);
        modelEditText = (EditText) findViewById(R.id.addCarModelEditText);
        milesEditText = (EditText) findViewById(R.id.addCarMilesEditText);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newCar = new Car();
                newCar.setName(nameEditText.getText().toString());
                newCar.setMake(makeEditText.getText().toString());
                newCar.setDescription(descriptionEditText.getText().toString());

                newCar.setTotalMiles(Double.parseDouble(milesEditText.getText().toString()));
                newCar.setModel(modelEditText.getText().toString());
                boolean success = databaseDAO.InsertCar(newCar, AddCarActivity.this);
                if(success){
                    finish();
                }
                else{
                    //handel
                }
            }
        });
    }
}
