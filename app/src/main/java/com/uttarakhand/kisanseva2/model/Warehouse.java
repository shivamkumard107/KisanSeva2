package com.uttarakhand.kisanseva2.model;

public class Warehouse {
    private String centerName;
    private String district;
    private String officerName;
    private String mobileNum;
    private int selfPot;
    private int rentalPot;
    private int reserveCap;
    private int privateWarehouse;

    public Warehouse(String centerName, String district, String officerName, String mobileNum, int selfPot, int rentalPot, int reserveCap, int privateWarehouse) {
        this.centerName = centerName;
        this.district = district;
        this.officerName = officerName;
        this.mobileNum = mobileNum;
        this.selfPot = selfPot;
        this.rentalPot = rentalPot;
        this.reserveCap = reserveCap;
        this.privateWarehouse = privateWarehouse;
    }

    public String getCenterName() {
        return centerName;
    }

    public String getDistrict() {
        return district;
    }

    public String getOfficerName() {
        return officerName;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public int getSelfPot() {
        return selfPot;
    }

    public int getRentalPot() {
        return rentalPot;
    }

    public int getReserveCap() {
        return reserveCap;
    }

    public int getPrivateWarehouse() {
        return privateWarehouse;
    }
}
