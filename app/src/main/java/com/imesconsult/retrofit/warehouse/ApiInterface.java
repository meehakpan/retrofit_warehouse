package com.imesconsult.retrofit.warehouse;

import com.imesconsult.retrofit.warehouse.models.Item;
import com.imesconsult.retrofit.warehouse.models.AuthModel;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PATCH;
import retrofit2.http.DELETE;

public interface ApiInterface {

    @POST("/api/auth/login")
    Call<HashMap<String, String>> loginToWarehouse(@Body AuthModel auth);


    @POST("/api/auth/signup")
    Call<HashMap<String, String>> createNewAccount(@Body AuthModel auth);

    @POST("/api/items")
    Call<HashMap<String, String>> postNewItem(@Body Item item);

    @PUT("/api/items")
    Call<HashMap<String, String>> putItem(@Body Item item);

    @DELETE("/api/items")
    Call<HashMap<String, String>> deleteItem(@Body Item item);


    @GET("/api/{id}/items")
    Call<HashMap<String, String>> getItem(@Body Item item);


    @GET("/api/items")
    Call<List<Item>> getAllItems();


    //@PATCH("api/{id}/items")
    //Call<HashMap<String, String>> postItem(Body Item item);

    //@POST("/api/{id}/items")
    //Call<HashMap<String, String>> inceaseItem(Body Item item);

    //@POST("/api/{id}/items")
    //Call<HashMap<String, String>> decreaseItem(Body Item item);


}




