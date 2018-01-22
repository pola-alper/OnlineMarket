package com.alper.pola.andoid.onlinemarket.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alper.pola.andoid.onlinemarket.Adapter.SsubCategoryAdapter;
import com.alper.pola.andoid.onlinemarket.Model.Model1.SubCategory;
import com.alper.pola.andoid.onlinemarket.Model.Model1.SubCategory_;
import com.alper.pola.andoid.onlinemarket.R;
import com.alper.pola.andoid.onlinemarket.Util.RecyclerItemClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubSubCategory extends AppCompatActivity {
    SubCategory subCategory;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_sub_category);
        ButterKnife.bind(this);
        setCardList();
        Intent intent=getIntent();
        subCategory = (SubCategory) intent.getSerializableExtra("ssubcategory");
        SsubCategoryAdapter subCategoryAdapter=new SsubCategoryAdapter((ArrayList<SubCategory_>) subCategory.getSubCategories(),this);
        recyclerView.setAdapter(subCategoryAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                SubCategory_ subCategory_ = subCategory.getSubCategories().get(position);
                Intent intent =new Intent(SubSubCategory.this,ProductActivity.class);
                intent.putExtra("products", subCategory_);
                startActivity(intent);
            }
        }));
    }
    public void setCardList() {
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }
}
