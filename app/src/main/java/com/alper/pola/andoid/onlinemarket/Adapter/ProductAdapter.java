package com.alper.pola.andoid.onlinemarket.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alper.pola.andoid.onlinemarket.Model.Model2.Product;
import com.alper.pola.andoid.onlinemarket.Model.Model2.Variant;
import com.alper.pola.andoid.onlinemarket.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pola alper on 22-Jan-18.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ContactViewHolder> {
    Context context;
    private List<Product> products = new ArrayList<>();
    //  private List<Variant> variants = new ArrayList<>();

    public ProductAdapter(ArrayList<Product> Product, Context context) {
        this.context = context;
        this.products = Product;

    }


    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.rowproduct, parent, false);

        return new ContactViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final ContactViewHolder holder, int position) {


        final Product product = products.get(position);
      // final Variant variant=  variants.get(position);

        //  holder.img.setText(products.get(position).getName());
      //  List <Variant> variants = product.getVariants();


        Glide.with(context).load(product.getVariants().get(0).getImages().get(0)).into(holder.productImage);
      holder.productPrice.setText(product.getVariants().get(0).getMrp().toString());
        holder.productName.setText(product.getName());
        holder.quantity.setText("1");


    }

    @Override
    public int getItemCount() {
        return products.size();

    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        protected ImageView productImage;
        protected TextView productName;
        protected TextView productPrice;
        protected TextView quantity;
        protected Button increment;
        protected Button decrement;

        public ContactViewHolder(View v) {
            super(v);
            productImage = v.findViewById(R.id.product_iv);
            productName = v.findViewById(R.id.productname_tv);
            productPrice = v.findViewById(R.id.productprice_tv);
            quantity = v.findViewById(R.id.quantity_tv);
            increment = v.findViewById(R.id.increment_btn);
            decrement = v.findViewById(R.id.decrement_btn);


        }
    }
}
