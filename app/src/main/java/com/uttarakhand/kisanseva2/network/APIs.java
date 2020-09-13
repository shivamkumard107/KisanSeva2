package com.uttarakhand.kisanseva2.network;

import com.google.gson.JsonObject;
import com.uttarakhand.kisanseva2.model.FarmerInfo;
import com.uttarakhand.kisanseva2.model.IsLoggedIn;
import com.uttarakhand.kisanseva2.model.allOrders.AllOrders;
import com.uttarakhand.kisanseva2.model.blockchain.BlockchainResponse;
import com.uttarakhand.kisanseva2.model.negotiation.Negotiation;
import com.uttarakhand.kisanseva2.model.uploadItem.ItemUploadInventory;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

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
                                                     @Field("type") String type,
                                                     @Field("quality") String quality,
                                                     @Field("quantity") String quantity,
                                                     @Field("price") String price,
                                                     @Field("category") String category);

    @GET("api/mobile/farmer/allOrders")
    Call<AllOrders> getAllOrders();

    @GET("api/mobile/farmer/negotiation")
    Call<Negotiation> getAllNegotiations();

    @PUT("api/mobile/farmer/updateItem")
    Call<JsonObject> editDetails(@Field("id") String id,
                                 @Field("item_name") String itemName,
                                 @Field("description") String description,
                                 @Field("quality") String quality,
                                 @Field("quantity") String quantity,
                                 @Field("price") String price,
                                 @Field("image") String image);

    @FormUrlEncoded
    @POST("api/mobile/farmer/greivance")
    Call<JsonObject> submitGrievance(@Field("content") String content,
                                     @Field("category") String category,
                                     @Field("crop") String crop);

    @GET("api/mobile/farmer/block")
    Call<BlockchainResponse> loanInfo();

}
