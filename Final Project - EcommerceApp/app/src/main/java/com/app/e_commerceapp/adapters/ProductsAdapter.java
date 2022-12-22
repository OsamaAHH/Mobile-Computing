package com.app.e_commerceapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.e_commerceapp.R;
import com.app.e_commerceapp.models.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.RecyclerViewHolder> {

    private final List<Products> sensors;
    public CallBack mCallBack;

    public interface CallBack {
        void onItemClick(Products sensor);
    }

    public ProductsAdapter(List<Products> sensors) {
        this.sensors = sensors;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list_item, viewGroup, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int i) {
//        holder.PImage.setText(sensors.get(i).getProductImage());
        holder.PName.setText(sensors.get(i).getProductName());
        holder.PPrice.setText(sensors.get(i).getProductPrice());
//        holder.cell.setOnClickListener(v -> mCallBack.onItemClick(sensors.get(i)));
    }

    @Override
    public int getItemCount() {
        return sensors.size();
    }

    public void setOnClickedListener(CallBack mCallback) {
        this.mCallBack = mCallback;
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private final ImageView PImage;
        private final TextView PName;
        private final TextView PPrice;
        private final Button Pbtn;

        RecyclerViewHolder(View view) {
            super(view);
            PImage = view.findViewById(R.id.ProductImage);
            PName = view.findViewById(R.id.ProdcutName);
            PPrice = view.findViewById(R.id.ProductPrice);
            Pbtn = view.findViewById(R.id.AddToCartBtn);
        }
    }

}
