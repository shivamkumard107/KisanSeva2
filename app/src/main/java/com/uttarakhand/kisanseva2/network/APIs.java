package com.uttarakhand.kisanseva2.network;

import com.google.gson.JsonObject;
import com.uttarakhand.kisanseva2.model.FarmerInfo;
import com.uttarakhand.kisanseva2.model.IsLoggedIn;
import com.uttarakhand.kisanseva2.model.allOrders.AllOrders;
import com.uttarakhand.kisanseva2.model.uploadItem.ItemUploadInventory;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface APIs {
    @GET("api/mobile/farmer/info")
    Call<FarmerInfo> getFarmerInfo();

    @FormUrlEncoded
    @POST("api/mobile/users/otp")
    Call<JsonObject> sendNumber(@Field("phone") String number);

    @FormUrlEncoded
    @POST("api/mobile/users/submitOtp")
    Call<JsonObject> submitOtp(@Field("otp") String otp);

    @FormUrlEncoded
    @POST("api/mobile/users/signUp/google")
    Call<JsonObject> uploadInformation(@Field("phone") String phone,
                                       @Field("address") String address,
                                       @Field("name") String name,
                                       @Field("district") String district,
                                       @Field("token") String token,
                                       @Field("email") String email);

    @GET("api/mobile/users/test")
    Call<IsLoggedIn> isLoggedIn();

    @FormUrlEncoded
    @POST("api/mobile/farmer/upload")
    Call<ItemUploadInventory> uploadingItemInventory(@Field("image") String image,
                                                     @Field("item_name") String name,
                                                     @Field("description") String description,
                                                     @Field("quality") String quality,
                                                     @Field("quantity") String quantity,
                                                     @Field("price") String price,
                                                     @Field("category") String category);
    @GET("api/mobile/farmer/allOrders")
    Call<AllOrders> getAllOrders();
}
