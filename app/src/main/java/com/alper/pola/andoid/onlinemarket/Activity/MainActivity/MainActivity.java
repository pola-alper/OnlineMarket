package com.alper.pola.andoid.onlinemarket.Activity.MainActivity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.alper.pola.andoid.onlinemarket.Activity.LoginActivity.LoginActivity;
import com.alper.pola.andoid.onlinemarket.Activity.Sub_Category.Sub_Category;
import com.alper.pola.andoid.onlinemarket.Adapter.CategoryAdapter;
import com.alper.pola.andoid.onlinemarket.Model.Model1.Category;
import com.alper.pola.andoid.onlinemarket.Model.Model1.Example;
import com.alper.pola.andoid.onlinemarket.Model.Model3.User;
import com.alper.pola.andoid.onlinemarket.R;
import com.alper.pola.andoid.onlinemarket.Service.RequestInterface;
import com.alper.pola.andoid.onlinemarket.Util.RecyclerItemClickListener;
import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static String cartCount;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.profile_image)
    CircleImageView circleImageView;
    private String baseUrl = "https://www.zopnow.com/";
    private ArrayList<Category> categories;
    private CompositeDisposable mCompositeDisposable;
    private CategoryAdapter categoryAdapter;
    private String facebookId;
    private String facebookEmail;
    private String facebookName;
    private Intent intent;
    private DatabaseReference ref;
    private DatabaseReference myRef;
    private DatabaseReference refget;
    private DatabaseReference myRefget;
    private Category category;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupToolBar(toolbar);
        mCompositeDisposable = new CompositeDisposable();
        intent = getIntent();
        facebookId = intent.getStringExtra("facebookid");
        facebookName = intent.getStringExtra("facebookname");
        facebookEmail = intent.getStringExtra("facebookemail");
        Glide.with(this).load("https://graph.facebook.com/" + facebookId + "/picture?type=small").into(circleImageView);

        setCardList();
        loadJSON();
        ref = FirebaseDatabase.getInstance().getReference();
        myRef = ref.child("message").child("users");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (!dataSnapshot.hasChild(facebookId)) {
                    Log.d("exist", "userexist");
                    Map<String, User> users = new HashMap<>();
                    users.put(facebookId, new User(facebookEmail, facebookName));
                    myRef.setValue(users);
                } else {
                    refget = FirebaseDatabase.getInstance().getReference();
                    myRefget = refget.child("message").child("users").child(facebookId).child("CartCount");

                    myRefget.addValueEventListener(new ValueEventListener() {
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
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupmenu(circleImageView);
            }
        });
    }

    private void loadJSON() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Example.class, new Example.LocationsDeserializer())
                .create();
        RequestInterface requestInterface = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(RequestInterface.class);

        mCompositeDisposable.add(requestInterface.register()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));

    }

    private void handleResponse(ArrayList<Example> example) {
        Log.d("request", example.toString());
        categories = (ArrayList<Category>) example.get(1).getDataList().get(0).getCategories();
        Log.d("categoreis", categories.toString());

        categoryAdapter = new CategoryAdapter(categories, this);
        recyclerView.setAdapter(categoryAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(MainActivity.this, (view, position) -> {
            category = categories.get(position);
            intent = new Intent(MainActivity.this, Sub_Category.class);
            intent.putExtra("subcategory", category);
            intent.putExtra("facebookid", facebookId);

            startActivity(intent);
        }));

    }


    private void handleError(Throwable error) {
        Log.d("request", error.getLocalizedMessage());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }

    public void setCardList() {
        recyclerView.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
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

    private void popupmenu(CircleImageView circleImageView) {
        PopupMenu popupMenu = new PopupMenu(this, circleImageView);
        popupMenu.getMenuInflater().inflate(R.menu.dots_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(menuItem -> {

            switch (menuItem.getItemId()) {

                case R.id.signout:
                    LoginManager loginManager = LoginManager.getInstance();
                    loginManager.logOut();
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
            }
            return false;
        });
        popupMenu.show();
    }

    @Override
    public void onBackPressed() {
        LoginManager loginManager = LoginManager.getInstance();
        loginManager.logOut();
        super.onBackPressed();
    }
}
