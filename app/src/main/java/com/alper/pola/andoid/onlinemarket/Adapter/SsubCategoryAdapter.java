package com.alper.pola.andoid.onlinemarket.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alper.pola.andoid.onlinemarket.Model.Model1.SubCategory_;
import com.alper.pola.andoid.onlinemarket.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by shashank on 02-Aug-17.
 */


public class SsubCategoryAdapter extends RecyclerView.Adapter<SsubCategoryAdapter.ContactViewHolder> {
    Context context;
    private List<SubCategory_> categories = new ArrayList<>();

    public SsubCategoryAdapter(ArrayList<SubCategory_> categories, Context context) {
        this.context = context;
        this.categories = categories;

    }

    public void addItems(List<SubCategory_> items) {
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


        final SubCategory_ category = categories.get(position);
        holder.img.setText(category.getName());




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
