package com.app.e_commerceapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.e_commerceapp.adapters.ImageSliderAdapter;
import com.app.e_commerceapp.adapters.ProductsAdapter;
import com.app.e_commerceapp.api.ApiClient;
import com.app.e_commerceapp.api.ApiInterface;

import com.app.e_commerceapp.homeModels.ProductsModel;
import com.app.e_commerceapp.models.Products;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    ArrayList<ProductsModel> productsModelArrayList;
    RecyclerView imageSliderRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, container, false);

        imageSliderRecyclerView = view.findViewById(R.id.image_slider);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        imageSliderRecyclerView.setLayoutManager(layoutManager);




        getProductsData();
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
//        ArrayList<Integer> intList = new ArrayList<>();
//        intList.add(R.drawable.hero_section);
//        intList.add(R.drawable.hero_section2);
//
//        RecyclerView recyclerView = view.findViewById(R.id.image_slider);
//        recyclerView.setAdapter(adapter);
//        Products products = new Products();
//        products.setProductImage("no link");
//        products.setProductName("Osama");
//        products.setProductPrice("123");
//        Products products1 = new Products();
//        products.setProductImage("no link");
//        products.setProductName("Osama");
//        products.setProductPrice("123");
//        Products products2 = new Products();
//        products.setProductImage("no link");
//        products.setProductName("Osama");
//        products.setProductPrice("123");
//        ArrayList<Products> productsArrayList = new ArrayList<>();
//        productsArrayList.add(products);
//        productsArrayList.add(products1);
//        productsArrayList.add(products2);

//        ProductsAdapter adapter = new ProductsAdapter(productsArrayList);
//        RecyclerView recyclerView = view.findViewById(R.id.hero_sec );
//        recyclerView.setAdapter(adapter);


    }

    private void getProductsData() {
        Log.d("TAG", "onResponse:     ");

        ApiInterface apiInterfaceForWeatherForecast = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<ProductsModel>> call = apiInterfaceForWeatherForecast.getProducts("ck_c3a81c6e427bc5bcbed8e86ab1e555e3f380c011",
                "cs_99597d4d3e8f04b27619ab31d605f22c9062ed52");
        call.enqueue(new Callback<List<ProductsModel>>() {
                    @Override
                    public void onResponse(Call<List<ProductsModel>> call, Response<List<ProductsModel>> response) {
                        if(response.isSuccessful()){

                            Log.d("TAG", "onResponse:  onResponse   "+response.message());

                            List<ProductsModel> productsModel = response.body();
                            Log.d("TAG", "onResponse:  onResponse   "+response.body());
                            productsModelArrayList = new ArrayList<>();

                            for(int i=0; i<productsModel.size(); i++){

                                productsModelArrayList.add(productsModel.get(i));

                            }


                            ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(productsModelArrayList, getActivity());
                            imageSliderRecyclerView.setAdapter(imageSliderAdapter);


                        }else
                        {
                            Toast.makeText(getActivity(), "Response not successful", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<List<ProductsModel>> call, Throwable t) {
                        Log.d("TAG", "onResponse:  onFailure   "+t.getLocalizedMessage());

                    }
                });

    }


}