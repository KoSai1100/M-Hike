package com.uog.mhike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uog.mhike.R;
import com.uog.mhike.database.Hike;
import com.uog.mhike.database.Observation;

import java.io.BufferedReader;

public class HikeDetailViewActivity extends AppCompatActivity {

    private int id;
    private int SAVE_REQUEST_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hike_detail_view);

        TextView lblname=findViewById(R.id.lblHikeDetailName);
        TextView lbllocation=findViewById(R.id.lblHikeDetailLocation);
        TextView lbldate=findViewById(R.id.lblHikeDetailDate);
        TextView lblparking=findViewById(R.id.lblHikeDetailParking);
        TextView lbllength=findViewById(R.id.lblHikeDetailLength);
        TextView lbldifficulty=findViewById(R.id.lblHikeDetailDifficulty);
        TextView lbldescription=findViewById(R.id.lblHikeDetailDescription);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){

            int id=bundle.getInt(Hike.ID,0);

            String name=bundle.getString(Hike.NAME);
            String location=bundle.getString(Hike.LOCATION);
            String date=bundle.getString(Hike.DATE);
            String parking=bundle.getString(Hike.PARKING);
            String length= String.valueOf(Double.parseDouble(bundle.getString(Hike.LENGTH)));
            String difficulty=bundle.getString(Hike.DIFFICULTY);
            String description=bundle.getString(Hike.DESCRIPTION);



            lblname.setText(name);
            lbllocation.setText(location);
            lbldate.setText(date);
            lblparking.setText(parking);
            lbllength.setText(length+"");
            lbldifficulty.setText(difficulty);
            lbldescription.setText(description);

        }

        Button btnAddObservation=findViewById(R.id.btnAddObservation);
        btnAddObservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),ObservationEntryActivity.class);
                intent.putExtra(Observation.HIKE_ID,id);
                startActivityForResult(intent,SAVE_REQUEST_CODE);
            }
        });
    }
}