package com.uttarakhand.kisanseva2.activities;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.uttarakhand.kisanseva2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class About_us extends AppCompatActivity {

    private String[] names = new String[]{"Kshitij", "Abhinav", "Ishaan", "Chirag", "Pranav", "Shivam"};
    private String[] descriptions = new String[]{"", "", "", "", "", "", ""};
    private int[] images = new int[]{R.drawable.kshitij, R.drawable.abhinav, R.drawable.ic_ishan, R.drawable.chirag, R.drawable.pranav, R.drawable.shivam};

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        listView = findViewById(R.id.listView);
        List<HashMap<String, String>> aList = new ArrayList<>();
        for (int x = 0; x < names.length; x++) {
            HashMap<String, String> hm = new HashMap<>();
            hm.put("Name", names[x]);
            hm.put("", descriptions[x]);
            hm.put("Image", Integer.toString(images[x]));
            aList.add(hm);
        }
        String[] from = {"Image", "Name"};
        int[] to = {R.id.image, R.id.name};
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, aList, R.layout.contributors_row, from, to);
        listView.setAdapter(simpleAdapter);

    }
}