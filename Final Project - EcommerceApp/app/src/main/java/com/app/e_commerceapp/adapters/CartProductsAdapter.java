package com.app.e_commerceapp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.app.e_commerceapp.R;
import com.app.e_commerceapp.interfaceCallBack.CartCallBack;
import com.app.e_commerceapp.roomdatabase.AppDatabase;
import com.app.e_commerceapp.roomdatabase.User;
import com.app.e_commerceapp.roomdatabase.UserDao;
import com.bumptech.glide.Glide;

import java.util.List;

public class CartProductsAdapter extends RecyclerView.Adapter<CartProductsAdapter.ViewHolder> {

    List<User> users;
    Context context;
    CartCallBack cartCallBack;

    public CartProductsAdapter(List<User> users, Context context, CartCallBack cartCallBack) {
        this.users = users;
        this.context = context;
        this.cartCallBack = cartCallBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.productCartTitle.setText(users.get(position).getProductName());
        holder.productCartPrice.setText("$ "+users.get(position).getProductPrice());
        holder.productCartQnt.setText("Qnt = "+users.get(position).getProductQuantity());

        Glide.with(context).load(users.get(position).getProductImage()).into(holder.cartProductImage);

        holder.deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppDatabase db = Room.databaseBuilder(context,
                        AppDatabase.class, "room_db").allowMainThreadQueries().build();
                UserDao userDao = db.userDao();
                // this is to delete the data from room database
                userDao.deleteById(users.get(position).getProductId());
                // this is to remove the data form array list
                users.remove(position);
                // update the fresh list of array list data
                notifyDataSetChanged();

                cartCallBack.onClickProduct(true);



            }
        });



    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView productCartTitle, productCartQnt, productCartPrice;
        ImageView cartProductImage, deleteProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            productCartTitle = itemView.findViewById(R.id.productCartTitle);
            productCartQnt = itemView.findViewById(R.id.productCartQnt);
            productCartPrice = itemView.findViewById(R.id.productCartPrice);
            cartProductImage = itemView.findViewById(R.id.cartProductImage);
            deleteProduct = itemView.findViewById(R.id.deleteProduct);
        }
    }
}
