package com.app.e_commerceapp;

import android.content.Intent;
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
import com.app.e_commerceapp.interfaceCallBack.ModelCallBack;
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


                            ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(productsModelArrayList, getActivity(), new ModelCallBack() {
                                @Override
                                public void onClickProduct(ProductsModel productsModels) {
                                    Intent intent = new Intent(requireActivity(),ProductDetailsActivity.class);
                                    Utils utils = new Utils();
                                    Log.d("setDataToUi", "setDataToUi:  click  "+productsModels.getName());
                                    intent.putExtra("Model",productsModels);
                                    utils.productsModels = productsModels;
                                    startActivity(intent);
                                }
                            });
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