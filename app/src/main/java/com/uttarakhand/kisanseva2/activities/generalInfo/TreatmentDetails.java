package com.uttarakhand.kisanseva2.activities.generalInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.uttarakhand.kisanseva2.R;

public class TreatmentDetails extends AppCompatActivity {
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_treatment_details);

        int image = getIntent().getIntExtra("image", R.drawable.t1);
        int text = getIntent().getIntExtra("info", 0);

        Log.v("###", text + "");

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.detail);

        imageView.setImageResource(image);
        textView.setText(text);
    }
}
