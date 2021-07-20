package com.imesconsult.retrofit.warehouse;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit getClient(String url, String token) {
        Retrofit.Builder retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create());

        if(token != null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new TokenInterceptor(token))
                    .build();
            retrofit.client(client);
        }

        return retrofit.build();
    }
}
