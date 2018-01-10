package com.geekghost.gp.geekghost.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：戈鹏
 * on 2018/1/2 21:50
 */

public class RetrofitUtils {

    private static volatile RetrofitUtils instance;
    private final Retrofit retrofit;

    private RetrofitUtils(){
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://api.svipmovie.com/")
                .build();
    }

    public static RetrofitUtils getInstance(){
        if(instance==null){
            synchronized (RetrofitUtils.class){
                if(instance==null){
                    instance=new RetrofitUtils();
                }
            }
        }
        return instance;
    }

    public ApiService getApiService(){
        ApiService apiService = retrofit.create(ApiService.class);
        return apiService;
    }
}
