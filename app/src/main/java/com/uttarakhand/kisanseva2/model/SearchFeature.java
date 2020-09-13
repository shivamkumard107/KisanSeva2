package com.uttarakhand.kisanseva2.model;

import android.content.Intent;

public class SearchFeature {
    private String mFeatureName, mFeatureMessage;
    private Integer mFeatureIcon;
    private Intent intent;

    public SearchFeature(String mFeatureName, Integer mFeatureIcon, String mFeatureMessage) {
        this.mFeatureName = mFeatureName;
        this.mFeatureIcon = mFeatureIcon;
        this.mFeatureMessage = mFeatureMessage;
    }

    public String getmFeatureName() {
        return mFeatureName;
    }

    public Integer getmFeatureIcon() {
        return mFeatureIcon;
    }

    public String getmFeatureMessage() {
        return mFeatureMessage;
    }

    public Intent getIntent() {
        return intent;
    }
}
