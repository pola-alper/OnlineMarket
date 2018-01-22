package com.alper.pola.andoid.onlinemarket.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alper.pola.andoid.onlinemarket.Adapter.SubCategoryAdapter;
import com.alper.pola.andoid.onlinemarket.Model.Model1.Category;
import com.alper.pola.andoid.onlinemarket.Model.Model1.SubCategory;
import com.alper.pola.andoid.onlinemarket.R;
import com.alper.pola.andoid.onlinemarket.Util.RecyclerItemClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Sub_Category extends AppCompatActivity {
    Category category;
@BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub__category);
        ButterKnife.bind(this);
        setCardList();
        Intent intent=getIntent();
        category = (Category) intent.getSerializableExtra("subcategory");
        SubCategoryAdapter subCategoryAdapter=new SubCategoryAdapter((ArrayList<SubCategory>) category.getSubCategories(),this);
        recyclerView.setAdapter(subCategoryAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                SubCategory subCategory = category.getSubCategories().get(position);
                Intent intent =new Intent(Sub_Category.this,SubSubCategory.class);
                intent.putExtra("ssubcategory", subCategory);
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
