package com.app.e_commerceapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.app.e_commerceapp.ProductDetailsActivity;
import com.app.e_commerceapp.R;
import com.app.e_commerceapp.Utils;
import com.app.e_commerceapp.homeModels.ProductsModel;
import com.app.e_commerceapp.interfaceCallBack.ModelCallBack;
import com.app.e_commerceapp.models.Products;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    ModelCallBack modelCallBack;
    ArrayList<ProductsModel> productsModelArrayList;
    Context context;


    public ProductsAdapter(ArrayList<ProductsModel> productsModelArrayList, Context context, ModelCallBack modelCallBack) {
        this.productsModelArrayList = productsModelArrayList;
        this.context = context;
        this.modelCallBack = modelCallBack;
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

        holder.productPrice.setText("$ "+productsModelArrayList.get(position).getPrice()+"$");
        holder.productName.setText(productsModelArrayList.get(position).getName());

        holder.productImageItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          /*      Intent intent = new Intent(context, ProductDetailsActivity.class);
                Utils utils = new Utils();
                Log.d("setDataToUi", "setDataToUi:  click 64 "+productsModelArrayList.get(position).getName());

                utils.productsModels = productsModelArrayList.get(position);
                context.startActivity(intent);*/
                modelCallBack.onClickProduct(productsModelArrayList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView productPrice, productName;
        ConstraintLayout productImageItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.ProductImage);
            productName = itemView.findViewById(R.id.ProdcutName);
            productPrice = itemView.findViewById(R.id.ProductPrice);
            productImageItem = itemView.findViewById(R.id.productImageItem);


        }
    }


}
