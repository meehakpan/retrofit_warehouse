package com.imesconsult.retrofit.warehouse;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor  implements Interceptor {

    private final String token;
    public TokenInterceptor(String token) {
        this.token = token;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request newRequest=chain.request().newBuilder()
                .header("Authorization","Bearer "+ this.token)
                .build();

        return chain.proceed(newRequest);    }
}
