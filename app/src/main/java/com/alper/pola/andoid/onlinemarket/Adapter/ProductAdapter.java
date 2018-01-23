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
import android.widget.Toast;

import com.alper.pola.andoid.onlinemarket.Model.Model2.Product;
import com.alper.pola.andoid.onlinemarket.Model.Model2.Variant;
import com.alper.pola.andoid.onlinemarket.R;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pola alper on 22-Jan-18.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ContactViewHolder> {
    Context context;
    private int quantity = 1;
    private int cartcount;
    private String facebookid;
    private List<Product> products = new ArrayList<>();

    public ProductAdapter(ArrayList<Product> Product, Context context, String facebookid) {
        this.context = context;
        this.products = Product;
        this.facebookid = facebookid;

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

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        DatabaseReference myRef = ref.child("message").child("users").child(facebookid).child("CartCount");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String Cartcount = dataSnapshot.getValue(String.class);
                if (Cartcount != null) {
                    cartcount = Integer.valueOf(Cartcount);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        final Product product = products.get(position);

        Glide.with(context).load(product.getVariants().get(0).getImages().get(0)).into(holder.productImage);
        holder.productPrice.setText(product.getVariants().get(0).getMrp().toString());
        holder.productName.setText(product.getName());

        holder.increment.setOnClickListener(view -> {
            quantity++;
            holder.quantity.setText(String.valueOf(quantity));
        });
        holder.decrement.setOnClickListener(view -> {

            if (quantity < 1) {
                holder.quantity.setText("1");
            } else {
                quantity--;
                holder.quantity.setText(String.valueOf(quantity));
            }

        });
        holder.addToCart.setOnClickListener(view -> {

            DatabaseReference c1v2 = FirebaseDatabase.getInstance().getReference().child("message").child("users").child(facebookid);

            cartcount++;
            Map<String, Object> hopperUpdates = new HashMap<>();
            hopperUpdates.put("CartCount", String.valueOf(cartcount));
            c1v2.updateChildren(hopperUpdates);
        });


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
        protected Button addToCart;

        public ContactViewHolder(View v) {
            super(v);
            productImage = v.findViewById(R.id.product_iv);
            productName = v.findViewById(R.id.productname_tv);
            productPrice = v.findViewById(R.id.productprice_tv);
            quantity = v.findViewById(R.id.quantity_tv);
            increment = v.findViewById(R.id.increment_btn);
            decrement = v.findViewById(R.id.decrement_btn);
            addToCart = v.findViewById(R.id.addtocart_btn);


        }
    }
}
