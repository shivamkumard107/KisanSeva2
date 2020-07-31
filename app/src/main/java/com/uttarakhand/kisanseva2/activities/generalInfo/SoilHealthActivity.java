package com.uttarakhand.kisanseva2.activities.generalInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.uttarakhand.kisanseva2.Adapter.SoilHealthCardAdapter;
import com.uttarakhand.kisanseva2.R;
import com.uttarakhand.kisanseva2.model.ItemHealthCard;
import com.uttarakhand.kisanseva2.utilities.SqliteHelper;

import java.util.ArrayList;

public class SoilHealthActivity extends AppCompatActivity {
    private SqliteHelper helper;
    private Spinner spinner1,spinner2;
    private Button btnSubmit;
    ArrayList<String> list1,list2;
    ArrayList<ItemHealthCard> card_list;
    SoilHealthCardAdapter adapter;

    ArrayAdapter<String> arrayAdapter1,arrayAdapter2;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soil_health);
        getSupportActionBar().setTitle(R.string.labs_heading);
        spinner1= (Spinner) findViewById(R.id.spinner1);
        spinner2= (Spinner) findViewById(R.id.spinner2);
        btnSubmit= (Button) findViewById(R.id.btnSubmit);
        list1=new ArrayList<>();
        list2=new ArrayList<>();

        helper=new SqliteHelper(this);

        list1=helper.getDistinctStates();

        Toast.makeText(SoilHealthActivity.this, R.string.soil_test_lab,Toast.LENGTH_LONG).show();

        arrayAdapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list1);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("positon1", "" + position);
                list2 = helper.getDistricts((String) spinner1.getItemAtPosition(position));
                arrayAdapter2 = new ArrayAdapter<String>(SoilHealthActivity.this, android.R.layout.simple_spinner_dropdown_item, list2);
                arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(arrayAdapter2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        list2.add(getString(R.string.select_district));

        arrayAdapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list2);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(arrayAdapter2);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinner1.getSelectedItemPosition()!=0 && spinner2.getSelectedItemPosition() != 0) {
                    card_list= helper.getHealthCard(spinner1.getSelectedItem()+"",spinner2.getSelectedItem()+"");
                    adapter=new SoilHealthCardAdapter(SoilHealthActivity.this,card_list);

                    recyclerView = (RecyclerView) findViewById(R.id.recycler);
                    recyclerView.setLayoutManager(new LinearLayoutManager(SoilHealthActivity.this));
                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }

}