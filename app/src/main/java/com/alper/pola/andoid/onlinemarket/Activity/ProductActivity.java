package com.alper.pola.andoid.onlinemarket.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Adapter;

import com.alper.pola.andoid.onlinemarket.Adapter.ProductAdapter;
import com.alper.pola.andoid.onlinemarket.Model.Model2.Example2;
import com.alper.pola.andoid.onlinemarket.Model.Model1.SubCategory_;
import com.alper.pola.andoid.onlinemarket.Model.Model2.Product;
import com.alper.pola.andoid.onlinemarket.Model.Model2.Variant;
import com.alper.pola.andoid.onlinemarket.R;
import com.alper.pola.andoid.onlinemarket.Service.RequestInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductActivity extends AppCompatActivity {
    private static final String baseurl = "https://www.zopnow.com/";
    private SubCategory_ subCategory_;
    private CompositeDisposable mcompositeDisposable;
    private String url;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
int postion = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);
        setCardList();
        Intent intent = getIntent();
        subCategory_ = (SubCategory_) intent.getSerializableExtra("products");
        url = subCategory_.getUrl() + ".json";
        mcompositeDisposable = new CompositeDisposable();
        Log.d("url", url);
        loadJSON();

    }

    private void loadJSON() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Example2.class, new Example2.LocationsDeserializer())
                .create();
        RequestInterface requestInterface = new Retrofit.Builder()
                .baseUrl(baseurl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(RequestInterface.class);

        mcompositeDisposable.add(requestInterface.registerelse(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleError(Throwable error) {
        Log.d("request", error.getLocalizedMessage());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mcompositeDisposable.clear();
    }

    private void handleResponse(ArrayList<Example2> example) {
        Log.d("request", example.toString());

        if (example.get(postion).getDataList().get(0).getProducts()==null){
            postion =2;
        }
        ArrayList<Product>  products =(ArrayList<Product>) example.get(postion).getDataList().get(0).getProducts();

        Log.d("productsize", String.valueOf(example.get(postion).getDataList().get(0).getProducts().size()));
        Log.d("products", products.toString());

        ProductAdapter productAdapter = new ProductAdapter(products,this);
        recyclerView.setAdapter(productAdapter);


//    ArrayList<Product>  products =(ArrayList<Product>) example.get(i).getDataList().get(0).getProducts();
//   // ArrayList<Variant> variants = (ArrayList<Variant>) products.get(0).getVariants();
//    Log.d("products", products.toString());
//
//    ProductAdapter productAdapter = new ProductAdapter(products,this);
//    recyclerView.setAdapter(productAdapter);
}





    public void setCardList() {
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

}
