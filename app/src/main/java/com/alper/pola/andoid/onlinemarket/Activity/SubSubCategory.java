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

import com.alper.pola.andoid.onlinemarket.Adapter.SsubCategoryAdapter;
import com.alper.pola.andoid.onlinemarket.Model.Model1.SubCategory;
import com.alper.pola.andoid.onlinemarket.Model.Model1.SubCategory_;
import com.alper.pola.andoid.onlinemarket.R;
import com.alper.pola.andoid.onlinemarket.Util.RecyclerItemClickListener;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class SubSubCategory extends AppCompatActivity {
    SubCategory subCategory;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    String facebookId;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.profile_image)
    CircleImageView circleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_sub_category);
        ButterKnife.bind(this);
        setCardList();
        Intent intent = getIntent();
        facebookId = intent.getStringExtra("facebookid");

        subCategory = (SubCategory) intent.getSerializableExtra("ssubcategory");
        SsubCategoryAdapter subCategoryAdapter = new SsubCategoryAdapter((ArrayList<SubCategory_>) subCategory.getSubCategories(), this);
        recyclerView.setAdapter(subCategoryAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, (view, position) -> {
            SubCategory_ subCategory_ = subCategory.getSubCategories().get(position);
            Intent intent1 = new Intent(SubSubCategory.this, ProductActivity.class);
            intent1.putExtra("products", subCategory_);
            intent1.putExtra("facebookid", facebookId);
            startActivity(intent1);
        }));
        setupToolBar(toolbar);

        Glide.with(this).load("https://graph.facebook.com/" + facebookId + "/picture?type=small").into(circleImageView);


    }

    public void setCardList() {
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cartmenu, menu);

        if (!Objects.equals(MainActivity.cartCount, null)) {
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
        if (!Objects.equals(MainActivity.cartCount, "0")) {
            tv.setVisibility(View.VISIBLE);
            tv.setText(MainActivity.cartCount);

        }

    }

    @Override
    public void onResume() {  // After a pause OR at startup
        super.onResume();

        invalidateOptionsMenu();


    }

    private void setupToolBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setSubtitle("");

    }


}
