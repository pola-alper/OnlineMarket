package com.alper.pola.andoid.onlinemarket.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alper.pola.andoid.onlinemarket.Adapter.ProductAdapter;
import com.alper.pola.andoid.onlinemarket.Model.Model2.Example2;
import com.alper.pola.andoid.onlinemarket.Model.Model1.SubCategory_;
import com.alper.pola.andoid.onlinemarket.Model.Model2.Product;
import com.alper.pola.andoid.onlinemarket.R;
import com.alper.pola.andoid.onlinemarket.Service.RequestInterface;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductActivity extends AppCompatActivity {
    private static final String baseurl = "https://www.zopnow.com/";
    public static String cartCount;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    int postion = 1;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.profile_image)
    CircleImageView circleImageView;
    private SubCategory_ subCategory_;
    private CompositeDisposable mCompositeDisposable;
    private String url;
    private String facebookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);

        setCardList();
        Intent intent = getIntent();
        facebookId = intent.getStringExtra("facebookid");

        subCategory_ = (SubCategory_) intent.getSerializableExtra("products");
        url = subCategory_.getUrl() + ".json";
        mCompositeDisposable = new CompositeDisposable();
        Log.d("url", url);
        loadJSON();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRef = ref.child("message").child("users").child(facebookId).child("CartCount");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String Cartcount = dataSnapshot.getValue(String.class);
                if (Cartcount != null) {
                    cartCount = Cartcount;
                    invalidateOptionsMenu();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        setupToolBar(toolbar);

        Glide.with(this).load("https://graph.facebook.com/" + facebookId + "/picture?type=small").into(circleImageView);

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

        mCompositeDisposable.add(requestInterface.registerelse(url)
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
        mCompositeDisposable.clear();
    }

    private void handleResponse(ArrayList<Example2> example) {
        Log.d("request", example.toString());

        if (example.get(postion).getDataList().get(0).getProducts() == null) {
            postion = 2;
        }
        ArrayList<Product> products = (ArrayList<Product>) example.get(postion).getDataList().get(0).getProducts();

        Log.d("productsize", String.valueOf(example.get(postion).getDataList().get(0).getProducts().size()));
        Log.d("products", products.toString());

        ProductAdapter productAdapter = new ProductAdapter(products, this, facebookId);
        recyclerView.setAdapter(productAdapter);


    }


    public void setCardList() {
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cartmenu, menu);

        if (!Objects.equals(cartCount, null)) {
            MenuItem item = menu.findItem(R.id.action_cart);
            MenuItemCompat.setActionView(item, R.layout.actionbar_badge_layout);
            RelativeLayout notifCount = (RelativeLayout) item.getActionView();
            setCount(notifCount);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setCount(RelativeLayout notifCount) {
        TextView tv = notifCount.findViewById(R.id.actionbar_notifcation_textview);
        notifCount.setVisibility(View.INVISIBLE);
        if (!Objects.equals(cartCount, "0")) {
            tv.setVisibility(View.VISIBLE);
            tv.setText(cartCount);

        }

    }

    private void setupToolBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setSubtitle("");

    }




}
