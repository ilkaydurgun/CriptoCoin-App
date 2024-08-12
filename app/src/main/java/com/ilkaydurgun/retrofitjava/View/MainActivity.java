package com.ilkaydurgun.retrofitjava.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ilkaydurgun.retrofitjava.Model.CriptoModel;
import com.ilkaydurgun.retrofitjava.R;
import com.ilkaydurgun.retrofitjava.Service.CriptoAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?CMC_PRO_API_KEY=7ab71a14-8ba2-4c0f-b7be-d3f0ee9a3a3a

    ArrayList<CriptoModel> criptoModels;
    private String BASE_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/";
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        loadData();

    }

    private void loadData(){
        CriptoAPI criptoAPI = retrofit.create(CriptoAPI.class);
        Call<List<CriptoModel>> call = criptoAPI.getData();
        call.enqueue(new Callback<List<CriptoModel>>() {
            @Override
            public void onResponse(Call<List<CriptoModel>> call, Response<List<CriptoModel>> response) {

                if(response.isSuccessful()){
                    List<CriptoModel> responseList = response.body();
                        criptoModels = new ArrayList<>(responseList) ;

                        for (CriptoModel criptoModel : criptoModels){

                            System.out.println( criptoModel);

                        }


                }
            }

            @Override
            public void onFailure(Call<List<CriptoModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}