package com.app.e_commerceapp.adapters;



import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.e_commerceapp.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder>{
    private ArrayList<User> usersList;

    public recyclerAdapter(ArrayList<user>userList){
        this.usersList = userList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nameTxt;

        public MyViewHolder(final view view){
            super(view);
            nameTxt = view.findViewById(R.id.first);
        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, attachToRoot: false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}


public class ProductAdapters {
}
