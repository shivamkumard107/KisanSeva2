package com.uttarakhand.kisanseva2.model;

public class CropToSell {
    public CropToSell(String mCropName, Integer mCropPhoto, int mCropPrice, double mCropRating, double   mCropQuality) {
        this.mCropName = mCropName;
        this.mCropPhoto = mCropPhoto;
        this.mCropPrice = mCropPrice;
        this.mCropRating = mCropRating;
        this.mCropQuality = mCropQuality;
    }

    private String mCropName;
    private Integer mCropPhoto;
    private int mCropPrice;
    private double mCropRating, mCropQuality;

    public String getmCropName() {
        return mCropName;
    }

    public Integer getmCropPhoto() {
        return mCropPhoto;
    }

    public int getmCropPrice() {
        return mCropPrice;
    }

    public double getmCropRating() {
        return mCropRating;
    }

    public double getmCropQuality() {
        return mCropQuality;
    }
}
