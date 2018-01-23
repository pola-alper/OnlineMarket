package com.alper.pola.andoid.onlinemarket.Activity.Sub_Category;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alper.pola.andoid.onlinemarket.Activity.MainActivity.MainActivity;
import com.alper.pola.andoid.onlinemarket.Activity.SsubCategory.SubSubCategory;
import com.alper.pola.andoid.onlinemarket.Adapter.SubCategoryAdapter;
import com.alper.pola.andoid.onlinemarket.Model.Model1.Category;
import com.alper.pola.andoid.onlinemarket.Model.Model1.SubCategory;
import com.alper.pola.andoid.onlinemarket.R;
import com.alper.pola.andoid.onlinemarket.Util.RecyclerItemClickListener;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class Sub_Category extends AppCompatActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.profile_image)
    CircleImageView circleImageView;
    private Category category;
    private String facebookId;
    private Intent intent;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub__category);
        ButterKnife.bind(this);
        setCardList();
        intent = getIntent();
        facebookId = intent.getStringExtra("facebookid");

        category = (Category) intent.getSerializableExtra("subcategory");
        SubCategoryAdapter subCategoryAdapter = new SubCategoryAdapter((ArrayList<SubCategory>) category.getSubCategories(), this);
        recyclerView.setAdapter(subCategoryAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                SubCategory subCategory = category.getSubCategories().get(position);
                intent = new Intent(Sub_Category.this, SubSubCategory.class);
                intent.putExtra("ssubcategory", subCategory);
                intent.putExtra("facebookid", facebookId);
                startActivity(intent);
            }
        }));

        setupToolBar(toolbar);

        Glide.with(this).load("https://graph.facebook.com/" + facebookId + "/picture?type=small").into(circleImageView);

    }

    public void setCardList() {
        recyclerView.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(this, 2);
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
