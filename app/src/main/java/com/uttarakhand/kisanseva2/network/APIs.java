package com.uttarakhand.kisanseva2.network;

import com.uttarakhand.kisanseva2.model.FarmerInfo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIs {
    @GET("api/mobile/farmer/info")
    Call<FarmerInfo> getFarmerInfo();
}
