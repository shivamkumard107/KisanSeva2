package com.uttarakhand.kisanseva2.activities.generalInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.uttarakhand.kisanseva2.Adapter.PolicyAdapter;
import com.uttarakhand.kisanseva2.R;

import java.util.ArrayList;
import java.util.Arrays;

public class SelectPolicyActivity extends AppCompatActivity {
    private PolicyAdapter adapter;
    private ArrayList list;
    private RecyclerView recyclerView;

    private String[] links;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_policy);
        getSupportActionBar().setTitle(getString(R.string.policy_title));

        links=getResources().getStringArray(R.array.policies_link);

        list = new ArrayList<>();

        list=new ArrayList();

        String[] array=getResources().getStringArray(R.array.policies);

        list.addAll(Arrays.asList(array));

        adapter = new PolicyAdapter(this,list,links);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        findViewById(R.id.progress).setVisibility(View.GONE);
    }
}
