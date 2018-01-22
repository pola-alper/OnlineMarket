package com.alper.pola.andoid.onlinemarket.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alper.pola.andoid.onlinemarket.Adapter.ImageAdapter;
import com.alper.pola.andoid.onlinemarket.Model.Model1.Category;
import com.alper.pola.andoid.onlinemarket.Model.Model1.Example;
import com.alper.pola.andoid.onlinemarket.R;
import com.alper.pola.andoid.onlinemarket.Service.RequestInterface;
import com.alper.pola.andoid.onlinemarket.Util.RecyclerItemClickListener;
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

public class MainActivity extends AppCompatActivity {
    private static final String baseurl = "https://www.zopnow.com/";
    ArrayList<Category> categories;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private CompositeDisposable mcompositeDisposable;
    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mcompositeDisposable = new CompositeDisposable();
        setCardList();
        loadJSON();

    }

    private void loadJSON() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Example.class, new Example.LocationsDeserializer())
                .create();
        RequestInterface requestInterface = new Retrofit.Builder()
                .baseUrl(baseurl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(RequestInterface.class);

        mcompositeDisposable.add(requestInterface.register()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(ArrayList<Example> example) {
        Log.d("request", example.toString());
        categories = (ArrayList<Category>) example.get(1).getDataList().get(0).getCategories();
        Log.d("categoreis", categories.toString());

        imageAdapter = new ImageAdapter(categories, this);
        recyclerView.setAdapter(imageAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(MainActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Category category = categories.get(position);
                Intent intent = new Intent(MainActivity.this, Sub_Category.class);
                intent.putExtra("subcategory", category);
                startActivity(intent);
            }
        }));

    }

    //imageAdapter= new ImageAdapter(categories, this);


//
//        Disposable disposable = Observable.fromArray(categroies).concatMap(Observable::just).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ArrayList>() {
//            @Override
//            public void accept(ArrayList arrayList) throws Exception {
//                Log.d("request",arrayList.toString());
//            }
//        });
//        compositeDisposable.add(disposable);
//
//
//
//
//        //mainview.paginator().onNext(pagenumber);


//
//    }

    private void handleError(Throwable error) {
        Log.d("request", error.getLocalizedMessage());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mcompositeDisposable.clear();
    }

    public void setCardList() {
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }
}
