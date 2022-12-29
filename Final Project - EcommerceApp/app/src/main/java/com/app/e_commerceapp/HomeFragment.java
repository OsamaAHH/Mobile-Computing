package com.app.e_commerceapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.e_commerceapp.adapters.ProductsAdapter;
import com.app.e_commerceapp.models.Products;

import java.util.ArrayList;


public class HomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Integer> intList = new ArrayList<>();
//        intList.add(R.drawable.hero_section);
//        intList.add(R.drawable.hero_section2);
//
//        RecyclerView recyclerView = view.findViewById(R.id.image_slider);
//        recyclerView.setAdapter(adapter);
        Products products = new Products();
        products.setProductImage("no link");
        products.setProductName("Osama");
        products.setProductPrice("123");
        Products products1 = new Products();
        products.setProductImage("no link");
        products.setProductName("Osama");
        products.setProductPrice("123");
        Products products2 = new Products();
        products.setProductImage("no link");
        products.setProductName("Osama");
        products.setProductPrice("123");
        ArrayList<Products> productsArrayList = new ArrayList<>();
        productsArrayList.add(products);
        productsArrayList.add(products1);
        productsArrayList.add(products2);

//        ProductsAdapter adapter = new ProductsAdapter(productsArrayList);
//        RecyclerView recyclerView = view.findViewById(R.id.hero_sec );
//        recyclerView.setAdapter(adapter);


    }

}