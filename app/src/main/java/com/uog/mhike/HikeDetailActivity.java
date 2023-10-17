package com.uog.mhike;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uog.mhike.database.DatabaseHelper;
import com.uog.mhike.database.Hike;

public class HikeDetailActivity extends AppCompatActivity {
    String name,location,date,parking,difficulty,description;
    private double length;
    private int id=0;
    private Hike hike;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hike_detail);

        TextView lblname=findViewById(R.id.lblname);
        TextView lbllocation=findViewById(R.id.lbllocation);
        TextView lbldate=findViewById(R.id.lbldate);
        TextView lblparking=findViewById(R.id.lblparking);
        TextView lbllength=findViewById(R.id.lbllength);
        TextView lbldifficulty=findViewById(R.id.lbldifficulty);
        TextView lbldescription=findViewById(R.id.lbldescription);

        Button btnback=findViewById(R.id.btnback);
        Button btnsave=findViewById(R.id.btnsave);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){

            id=bundle.getInt(Hike.ID,0);

             name=bundle.getString(Hike.NAME);
             location=bundle.getString(Hike.LOCATION);
             date=bundle.getString(Hike.DATE);
             parking=bundle.getString(Hike.PARKING);
             length=Double.parseDouble(bundle.getString(Hike.LENGTH));
             difficulty=bundle.getString(Hike.DIFFICULTY);
             description=bundle.getString(Hike.DESCRIPTION);

            lblname.setText(name);
            lbllocation.setText(location);
            lbldate.setText(date);
            lblparking.setText(parking);
            lbllength.setText(length+"");
            lbldifficulty.setText(difficulty);
            lbldescription.setText(description);

            hike=new Hike(
                  id,
                    name,
                    location,
                    date,
                    parking,
                    length,
                    difficulty,
                    description
            );

        }

btnback.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        finish();
    }
});
        databaseHelper=new DatabaseHelper(getBaseContext());

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long result=0;

                if(id==0){
                    /// for save new record
                  result=  databaseHelper.saveHike(hike);

                    Log.i("Save","ok");

                }else{
                    result=databaseHelper.updateHike(hike);
                    Intent intent=new Intent();
                    setResult(RESULT_OK,intent);
                    finish();
                }
                if(result>0){

                    new AlertDialog.Builder(HikeDetailActivity.this).setTitle("Data Saved!").setMessage("Successfully Save!").setIcon(R.drawable.baseline_info_24).show();
                }
                else {
                    new AlertDialog.Builder(HikeDetailActivity.this).setTitle("Error").setMessage("Something wrong!").setIcon(R.drawable.baseline_report_gmailerrorred_24).show();
                }
            }
        });
    }
}