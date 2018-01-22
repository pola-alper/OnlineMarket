package com.alper.pola.andoid.onlinemarket.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ContactViewHolder> {
    Context context;
    private List<Category> categories = new ArrayList<>();

    public ImageAdapter(ArrayList<Category> categories, Context context) {
        this.context = context;
        this.categories = categories;

    }

    public void addItems(List<Category> items) {
        this.categories.addAll(items);
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
   holder.img.setText(category.getName());

       // Glide.with(context).load(category.getImageUrl()).into(holder.img);

        Log.d("internt spped low", "onBindViewHolder: low ");


    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        protected TextView img;


        public ContactViewHolder(View v) {
            super(v);
            img = v.findViewById(R.id.wallpaper);

        }
    }
}
