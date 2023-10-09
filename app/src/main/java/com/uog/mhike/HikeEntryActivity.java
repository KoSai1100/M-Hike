package com.uog.mhike;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.uog.mhike.database.Hike;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class HikeEntryActivity extends AppCompatActivity {

    EditText txthikename,txtLength,txtdescription;

    Spinner spnLocation, spnDifficulty;

    TextView txtdate;
    Button btndate, btnnext;

    RadioButton rdoYes, rdoNo;

    private String[] locations={"l1","l2","l3"};
    private String [] Difficulty={"Easy","Normal", "Hard"};

    private int id;
    private int locationIndex=0;
    private int difficultyIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hike_entry);

        txthikename = findViewById(R.id.txthikename);
        txtdate = findViewById(R.id.txtdate);
        txtdescription = findViewById(R.id.txtdescription);
        txtLength = findViewById(R.id.txtLength);

        btndate = findViewById(R.id.btndate);
        btnnext = findViewById(R.id.btnnext);

        spnDifficulty = findViewById(R.id.spnDifficulty);
        spnLocation = findViewById(R.id.spnLocation);

        rdoYes = findViewById(R.id.rdoYes);
        rdoNo = findViewById(R.id.rdoNo);

        ArrayAdapter<String> adapterLocation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, locations);
        adapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnLocation.setAdapter(adapterLocation);
        spnLocation.setSelection(locationIndex);
        spnLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                location=locations[i];

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> adapterDifficulty = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Difficulty);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDifficulty.setAdapter(adapterDifficulty);
        spnDifficulty.setSelection(difficultyIndex);
        spnDifficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                diff=Difficulty[i];

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoNext();
            }
        });


        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateTimePickerFragment dateTimePickerFragment=new DateTimePickerFragment();
                dateTimePickerFragment.show(getSupportFragmentManager(),"datePicker");
            }
        });

        receivedData();
    }
        public void setDate(LocalDate date){
   ZonedDateTime zdt = ZonedDateTime.now();
//                txtdate.setText(date.toString());
  txtdate.setText(zdt.format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm")));
        }
        String location, diff;
        private void gotoNext(){

        String name=txthikename.getText().toString();
        String date=txtdate.getText().toString();
        String parking=rdoYes.isChecked()?"Yes" : "No";
        String length=txtLength.getText().toString();
        String description=txtdescription.getText().toString();

        if(name==null || name.trim().isEmpty()){
            new AlertDialog.Builder(this).setTitle("Error").setMessage("Please Enter the Name of Hike").show();
            txthikename.requestFocus();
            return;
        }
         if (location==null || location.trim().isEmpty()) {
            new AlertDialog.Builder(this).setTitle("Error").setMessage("Please Enter the Location of Hike").show();
             return;
        }

         if (diff==null || diff.trim().isEmpty()) {
                new AlertDialog.Builder(this).setTitle("Error").setMessage("Please Enter the Difficulty of Hike").show();
             return;
         }

        if (length==null || length.trim().isEmpty()) {
            new AlertDialog.Builder(this).setTitle("Error").setMessage("Please Enter the Length of Hike").show();
            return;
        }

        if (description==null || description.trim().isEmpty()) {
            new AlertDialog.Builder(this).setTitle("Error").setMessage("Please Enter the Description of Hike").show();
            return;
        }
        if (date==null || date.trim().isEmpty()) {
            new AlertDialog.Builder(this).setTitle("Error").setMessage("Please Enter the Date of Hike").show();
            return;
        }

        Intent intent=new Intent(this,HikeDetailActivity.class);
        intent.putExtra(Hike.ID,id);
        intent.putExtra(Hike.NAME,name);
        intent.putExtra(Hike.DATE,date);
        intent.putExtra(Hike.LOCATION,location);
        intent.putExtra(Hike.DIFFICULTY,diff);
        intent.putExtra(Hike.LENGTH,length);
        intent.putExtra(Hike.PARKING,parking);
        intent.putExtra (Hike.DESCRIPTION,description);
        startActivity(intent);
    }

        private void receivedData(){
            Bundle bundle=getIntent().getExtras();
            if(bundle!=null){

                id=bundle.getInt(Hike.ID,0);

                String name=bundle.getString(Hike.NAME);
                location=bundle.getString(Hike.LOCATION);
               String date=bundle.getString(Hike.DATE);
                String parking=bundle.getString(Hike.PARKING);
//              double length=bundle.getDouble(Hike.LENGTH);
//              double length=Double.parseDouble(bundle.getString(Hike.LENGTH));
              diff=bundle.getString(Hike.DIFFICULTY);
              String  description=bundle.getString(Hike.DESCRIPTION);

                txthikename.setText(name);
                txtdate.setText(date);
//                txtLength.setText(length+"");
                txtLength.setText(bundle.getDouble(Hike.LENGTH)+"");

                txtdescription.setText(description);
                //// for location spinner
                for (int i=0;i<locations.length;i++) {
                    if(location.equals(locations[i])){
                        locationIndex=i;
                        spnLocation.setSelection(locationIndex);
                        break;
                    }
                  }

                for (int i=0;i<Difficulty.length;i++) {
                    if(diff.equals(Difficulty[i])){
                        difficultyIndex=i;
                        spnDifficulty.setSelection(difficultyIndex);
                        break;
                    }
                }

//                //for difficulty spinner
//                for (int i=0; i<Difficulty.length; i++) {
//                    if(diff.equals(Difficulty[i])){
//                        difficultyIndex=i;
//                        spnDifficulty.setSelection(difficultyIndex);
//                        break;
//                    }
//                }

                /// for parking
                if(parking.equalsIgnoreCase("Yes")){

                    rdoYes.setChecked(true);
                }else {
                    rdoNo.setChecked(true);
                }
            }

            }

        }


