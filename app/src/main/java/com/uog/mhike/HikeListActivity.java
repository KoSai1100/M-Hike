package com.uog.mhike;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uog.mhike.adapter.HikeAdapter;
import com.uog.mhike.database.DatabaseHelper;
import com.uog.mhike.database.Hike;

import java.util.ArrayList;
import java.util.List;

public class HikeListActivity extends AppCompatActivity {

    private HikeAdapter hikeAdapter;
    private List<Hike> hikeList=new ArrayList<>();
    private DatabaseHelper databaseHelper;

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hike_list);


        recyclerView=findViewById(R.id.recycular);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseHelper=new DatabaseHelper(getBaseContext());
        hikeAdapter=new HikeAdapter(hikeList);

        hikeAdapter.setListener(new HikeAdapter.ClickListener() {
            @Override
            public void onButtonClick(int position, View v, long id) {
                Hike hike=hikeList.get(position);
                if(id==R.id.btndetail){

                }else if(id==R.id.btnedit){
                    gotoEntry(hike);
                } else if (id == R.id.btndelete) {
                    long result = databaseHelper.delete(hike.getId());
                    if (result != 1) {
                        new AlertDialog.Builder(getBaseContext()).setTitle("Error").setMessage("Still can't delete this Hike").show();
                    } else {
                        search();
                            }
                }
            }
        });


        recyclerView.setAdapter(hikeAdapter);
        search();
        FloatingActionButton fab=findViewById(R.id.fabAddHike);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),HikeEntryActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
   //  search();
    }

    private void search(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    hikeList=databaseHelper.searchHike("");
                    Log.i("MyName", hikeList.size() + "");
                    hikeAdapter.setHikeList(hikeList);
                    hikeAdapter.notifyDataSetChanged(); //refresh the data
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    //// pass the data to the HikeEntryActivity
    private void gotoEntry(Hike hike){
        Intent intent=new Intent(this,HikeEntryActivity.class);
        intent.putExtra(Hike.ID,hike.getId());
        intent.putExtra(Hike.NAME,hike.getName());
        intent.putExtra(Hike.DATE,hike.getDate());
        intent.putExtra(Hike.LOCATION,hike.getLocation());
        intent.putExtra(Hike.DIFFICULTY,hike.getDifficulty());
        intent.putExtra(Hike.LENGTH,hike.getLength());
        intent.putExtra(Hike.PARKING,hike.getParking());
        intent.putExtra (Hike.DESCRIPTION,hike.getDescription());
        startActivity(intent);
    }
}