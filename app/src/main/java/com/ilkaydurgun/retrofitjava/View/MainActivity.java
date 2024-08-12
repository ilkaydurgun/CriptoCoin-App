    package com.ilkaydurgun.retrofitjava.View;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import android.os.Bundle;

    import com.google.gson.Gson;
    import com.google.gson.GsonBuilder;
    import com.ilkaydurgun.retrofitjava.Adapter.RecyclerViewAdapter;
    import com.ilkaydurgun.retrofitjava.Model.CriptoModel;
    import com.ilkaydurgun.retrofitjava.R;
    import com.ilkaydurgun.retrofitjava.Service.CriptoAPI;

    import java.util.ArrayList;
    import java.util.List;

    import io.reactivex.Scheduler;
    import io.reactivex.android.schedulers.AndroidSchedulers;
    import io.reactivex.disposables.CompositeDisposable;
    import io.reactivex.schedulers.Schedulers;
    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;
    import retrofit2.Retrofit;
    import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
    import retrofit2.converter.gson.GsonConverterFactory;

    public class MainActivity extends AppCompatActivity {

        //https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?CMC_PRO_API_KEY=7ab71a14-8ba2-4c0f-b7be-d3f0ee9a3a3a
        //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
        ArrayList<CriptoModel> criptoModels;
        private String BASE_URL = "https://raw.githubusercontent.com/";
        Retrofit retrofit;
        RecyclerView recyclerView;

        RecyclerViewAdapter recyclerViewAdapter;

        CompositeDisposable compositeDisposable;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            recyclerView =findViewById(R.id.recyclerView);

            Gson gson = new GsonBuilder().setLenient().create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            loadData();

        }

        private void loadData(){
            CriptoAPI criptoAPI = retrofit.create(CriptoAPI.class);

            compositeDisposable = new CompositeDisposable();
            compositeDisposable.add(criptoAPI.getData()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handlerResponse));
            /*
            Call<List<CriptoModel>> call = criptoAPI.getData();
            call.enqueue(new Callback<List<CriptoModel>>() {
                @Override
                public void onResponse(Call<List<CriptoModel>> call, Response<List<CriptoModel>> response) {

                    if(response.isSuccessful() && response.body() != null  ){
                        List<CriptoModel> responseList = response.body();
                            criptoModels = new ArrayList<>(responseList) ;


                            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            recyclerViewAdapter = new RecyclerViewAdapter(criptoModels);
                            recyclerView.setAdapter(recyclerViewAdapter);




                    }
                }

                @Override
                public void onFailure(Call<List<CriptoModel>> call, Throwable t) {
                    t.printStackTrace();
                }
            });
            */
        }
        private void handlerResponse(List<CriptoModel>criptoModelList){

            criptoModels = new ArrayList<>(criptoModelList) ;


            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            recyclerViewAdapter = new RecyclerViewAdapter(criptoModels);
            recyclerView.setAdapter(recyclerViewAdapter);


        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
        }
    }
