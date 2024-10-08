package com.ilkaydurgun.retrofitjava.Service;

import com.ilkaydurgun.retrofitjava.Model.CriptoModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CriptoAPI {
    //https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?CMC_PRO_API_KEY=7ab71a14-8ba2-4c0f-b7be-d3f0ee9a3a3a

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    Observable<List<CriptoModel>> getData();
    //Call<List<CriptoModel>> getData();

}
