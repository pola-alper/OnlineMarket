package com.alper.pola.andoid.onlinemarket.Service;

import com.alper.pola.andoid.onlinemarket.Model.Model1.Example;
import com.alper.pola.andoid.onlinemarket.Model.Model2.Example2;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RequestInterface {

    @GET("categorylist.json")
    Observable<ArrayList<Example>> register();

    @GET()
    Observable<ArrayList<Example2>> registerelse(
            @Url String url

    );
}
