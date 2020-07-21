package com.uttarakhand.kisanseva2.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uttarakhand.kisanseva2.Adapter.MainAdapter;
import com.uttarakhand.kisanseva2.R;
import com.uttarakhand.kisanseva2.activities.CropProductionActivity;
import com.uttarakhand.kisanseva2.activities.HorticultureActivity;
import com.uttarakhand.kisanseva2.activities.SelectPolicyActivity;
import com.uttarakhand.kisanseva2.activities.SelectProblemActivity;
import com.uttarakhand.kisanseva2.activities.SoilHealthActivity;
import com.uttarakhand.kisanseva2.model.MainListItem;

import java.util.ArrayList;

public class InformationFragment extends Fragment {
    private ArrayList<MainListItem> list;
    private RecyclerView recyclerView;
    private MainAdapter adapter;

    private Integer[] imageUrls = {R.raw.crop_production_opt, R.raw.treat, R.raw.shc2,/*R.raw.production_main,*/R.raw.horticulture_main, R.raw.govp};

    private Integer[] hindiTexts = {R.string.crop_production_card_title_hi, R.string.treatment_card_title_hi,
            R.string.storage_card_title_hi,/*R.string.survey_card_title_hi,*/
            R.string.horticulture_card_title_hi, R.string.policy_card_title_hi};

    private Integer[] englishTexts = {R.string.crop_production_card_title_en, R.string.treatment_card_title_en,
            R.string.storage_card_title_en,/*R.string.survey_card_title_en,*/
            R.string.horticulture_card_title_en, R.string.policy_card_title_en};

    private String[] backgroundColors = {"#35e372", "#a4f075", "#ffff4d",/*"#70dbdb",*/"#cef63c", "#ff9f80"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        View v = inflater.inflate(R.layout.fragment_information, container, false);
        Intent[] links = {
                new Intent(getContext(), CropProductionActivity.class),
                new Intent(getContext(), SelectProblemActivity.class),
                new Intent(getContext(), SoilHealthActivity.class),
                new Intent(getContext(), HorticultureActivity.class),
                new Intent(getContext(), SelectPolicyActivity.class)
        };

        list = new ArrayList<>();
        for (int i = 0; i < imageUrls.length; i++) {
            MainListItem item = new MainListItem();

            item.setImageUrl(imageUrls[i]);
            item.setHindiText(hindiTexts[i]);
            item.setEnglishText(englishTexts[i]);
            item.setBackgroundColor(backgroundColors[i]);
            item.setIntent(links[i]);

            list.add(item);
        }

        adapter = new MainAdapter(getContext(), list);

        recyclerView = v.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);
        Log.v("version", Build.VERSION.SDK_INT + "");
        v.findViewById(R.id.progress).setVisibility(View.GONE);
        return v;

    }

}