package qbecker.cartracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

import qbecker.cartracker.CarStuff.Repair;

public class EditRepairActivity extends AppCompatActivity {

    private EditText descriptionEditText;
    private EditText costEditText;
    private EditText dateEditText;

    private Button saveButton;

    private Repair selectedRepair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_repair);
        setTitle("Edit Repair");

        descriptionEditText = (EditText) findViewById(R.id.repairDescriptionEditText);
        costEditText = (EditText) findViewById(R.id.repairCostEditText);
        dateEditText = (EditText) findViewById(R.id.repairDateEditText);

        saveButton = (Button) findViewById(R.id.repairSaveButton);

        selectedRepair = new Repair();

        Intent received = getIntent();
        Bundle b = received.getExtras();
        if(b != null){
            selectedRepair = databaseDAO.GetRepair(b.getString("desc"), b.getString("uid"), this);
        }
        fillEditTexts(selectedRepair);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFromEditTexts();
                boolean success = databaseDAO.InsertRepair(selectedRepair, EditRepairActivity.this);
                if(success){
                    finish();
                }
            }
        });
    }
    
    //// TODO: 5/31/17 Add error checking for inputs
    private void saveFromEditTexts(){
        selectedRepair.setDescription(descriptionEditText.getText().toString());
        selectedRepair.setCost(Double.parseDouble(costEditText.getText().toString()));
        selectedRepair.setDate(dateEditText.getText().toString());
    }

    private void fillEditTexts(Repair repair){
        descriptionEditText.setText(selectedRepair.getDescription());
        costEditText.setText(String.valueOf(selectedRepair.getCost()));
        dateEditText.setText(selectedRepair.getDate());
    }
}
