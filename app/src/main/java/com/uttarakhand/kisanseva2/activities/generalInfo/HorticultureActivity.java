package com.uttarakhand.kisanseva2.activities.generalInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.uttarakhand.kisanseva2.Adapter.HorticultureAdapter;
import com.uttarakhand.kisanseva2.R;
import com.uttarakhand.kisanseva2.model.MainListItem;

import java.util.ArrayList;

public class HorticultureActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ArrayList<MainListItem> list;
    HorticultureAdapter adapter;

    Integer[] imageUrls={R.drawable.mango_main,R.drawable.banana_main,R.drawable.guava_main};
    Integer[] englishTexts={R.string.horticulture_item1_en,R.string.horticulture_item2_en,
            R.string.horticulture_item3_en};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horticulture);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.horticulture_card_title));
        }

        list=new ArrayList<>();

        Intent[] links=getIntents();

        for (int i=0;i<imageUrls.length;i++)
        {
            MainListItem item=new MainListItem();
            item.setEnglishText(englishTexts[i]);

            item.setImageUrl(imageUrls[i]);
            item.setIntent(links[i]);
            list.add(item);
        }

        adapter=new HorticultureAdapter(this,list);

        mRecyclerView=(RecyclerView)findViewById(R.id.recycler);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        findViewById(R.id.progress).setVisibility(View.GONE);
    }

    public Intent[] getIntents(){
        Intent i1=new Intent(this, HorticultureDetailActivity.class);
        Intent i2=new Intent(this, HorticultureDetailActivity.class);
        Intent i3=new Intent(this, HorticultureDetailActivity.class);
        i1.putExtra("number",1);
        i2.putExtra("number",2);
        i3.putExtra("number",3);

        Intent[] links={ i1, i2, i3 };

        return links;
    }

}
