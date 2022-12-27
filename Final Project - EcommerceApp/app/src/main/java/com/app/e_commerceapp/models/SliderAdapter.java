package com.app.e_commerceapp.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.e_commerceapp.R;

import java.util.List;



public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.RecyclerViewHolder> {

    private final List<String> images;
    public CallBack mCallBack;

    public interface CallBack {
        void onItemClick(Products sensor);
    }

    public SliderAdapter(List<String> image) {
        this.images = image;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list_item, viewGroup, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int i) {
        holder.PName.setText(images.get(i).getProductName());
//        holder.cell.setOnClickListener(v -> mCallBack.onItemClick(sensors.get(i)));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public void setOnClickedListener(CallBack mCallback) {
        this.mCallBack = mCallback;
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private final ImageView PImage;

        RecyclerViewHolder(View view) {
            super(view);
            PImage = view.findViewById(R.id.hero_sec);
        }
    }

}
