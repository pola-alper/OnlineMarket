package com.alper.pola.andoid.onlinemarket.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alper.pola.andoid.onlinemarket.Model.Model1.Category;
import com.alper.pola.andoid.onlinemarket.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by shashank on 02-Aug-17.
 */


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ContactViewHolder> {
    Context context;
    private List<Category> categories = new ArrayList<>();

    public CategoryAdapter(ArrayList<Category> categories, Context context) {
        this.context = context;
        this.categories = categories;

    }


    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.row, parent, false);

        return new ContactViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final ContactViewHolder holder, int position) {


        final Category category = categories.get(position);
        holder.categoryName.setText(category.getName());


    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        protected TextView categoryName;


        public ContactViewHolder(View v) {
            super(v);
            categoryName = v.findViewById(R.id.wallpaper);

        }
    }
}
