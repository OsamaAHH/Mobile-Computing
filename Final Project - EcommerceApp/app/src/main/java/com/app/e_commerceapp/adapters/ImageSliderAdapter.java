package com.app.e_commerceapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.e_commerceapp.R;
import com.app.e_commerceapp.homeModels.ProductsModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ViewHolder> {

    ArrayList<ProductsModel> productsModelArrayList;
    Context context;


    public ImageSliderAdapter(ArrayList<ProductsModel> productsModelArrayList, Context context) {
        this.productsModelArrayList = productsModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_image_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context)
                .load(productsModelArrayList.get(position).getImages().get(0).getSrc())
                .placeholder(R.drawable.logo)
                .into(holder.productImage);

        holder.productPrice.setText(productsModelArrayList.get(position).getPrice()+"$");
        holder.productName.setText(productsModelArrayList.get(position).getName());










    }

    @Override
    public int getItemCount() {
        return productsModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView productPrice, productName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.ProductImage);
            productName = itemView.findViewById(R.id.ProdcutName);
            productPrice = itemView.findViewById(R.id.ProductPrice);


        }
    }


}

