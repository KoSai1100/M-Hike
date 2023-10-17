package com.uog.mhike;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.uog.mhike.database.DatabaseHelper;
import com.uog.mhike.database.Observation;

import java.time.ZonedDateTime;

public class ObservationEntryActivity extends AppCompatActivity {

    private int id;
    private int hikeId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observation_entry);

        EditText txtobservation=findViewById(R.id.txtobservation);
        EditText txtcomment=findViewById(R.id.txtcomment);
        Button btnobservationsave=findViewById(R.id.btnobservationsave);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            hikeId=bundle.getInt(Observation.HIKE_ID);
        }

        btnobservationsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String strObservation=txtobservation.getText().toString();
                    if(strObservation==null || strObservation.trim().isEmpty()){
                        Toast.makeText(getBaseContext(),"Please Enter the observation",Toast.LENGTH_LONG).show();
                        txtobservation.requestFocus();
                        return;
                    }
                DatabaseHelper databaseHelper=new DatabaseHelper(getBaseContext());
                    long result=0;
                    if(id==0){
                        Observation observation=new Observation(hikeId,strObservation, ZonedDateTime.now(),txtcomment.getText().toString(),null);
                        result=databaseHelper.saveObservation(observation);
                    }else {

                    }
                if(result>0){

                    new AlertDialog.Builder(ObservationEntryActivity.this).setTitle("Data Saved!").setMessage("Successfully Save!").setIcon(R.drawable.baseline_info_24).show();
                    Intent intent=new Intent();
                    setResult(RESULT_OK,intent);
                    finish();
                }
                else {
                    new AlertDialog.Builder(ObservationEntryActivity.this).setTitle("Error").setMessage("Something wrong!").setIcon(R.drawable.baseline_report_gmailerrorred_24).show();
                }
                }

        });
    }
}