package com.app.e_commerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.e_commerceapp.adapters.ImageSliderAdapter;
import com.app.e_commerceapp.adapters.ProductsAdapter;
import com.app.e_commerceapp.api.ApiClient;
import com.app.e_commerceapp.api.ApiInterface;
import com.app.e_commerceapp.homeModels.ProductsModel;
import com.app.e_commerceapp.interfaceCallBack.ModelCallBack;
import com.app.e_commerceapp.models.Products;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShopFragment extends Fragment {


    RecyclerView productRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_shop, container, false);



        productRecyclerView = view.findViewById(R.id.product_recycler);

        LinearLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        productRecyclerView.setLayoutManager(layoutManager);




        getProductsData();






        return view;




    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);


      /*  OkHttpClient client = new OkHttpClient();
        Request get = new Request.Builder()
                .url("https://esonikglobal.info/wp-json/wc/v3/products?consumer_key=ck_c3a81c6e427bc5bcbed8e86ab1e555e3f380c011&consumer_secret=cs_99597d4d3e8f04b27619ab31d605f22c9062ed52")
                .build();
        client.newCall(get).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }
                    String responseBody = response.body().string();
                    getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            // Stuff that updates the UI
                            Log.i("data", responseBody);

                            JSONArray jsonArray = null;
                            try {
                                jsonArray = new JSONArray(responseBody);

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    Products product = new Products();
                                    product.parsingObject(jsonObject);
                                    productsArrayList.add(product);
                                }

                                ProductsAdapter adapter = new ProductsAdapter(productsArrayList,getActivity());
                                RecyclerView recyclerView = view.findViewById(R.id.product_recycler);
                                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
                                recyclerView.setLayoutManager(mLayoutManager);
                                recyclerView.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });*/





    }


    private void getProductsData() {
        Log.d("TAG", "onResponse:     ");

        ApiInterface apiInterfaceForWeatherForecast = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        retrofit2.Call<List<ProductsModel>> call = apiInterfaceForWeatherForecast.getProducts("ck_c3a81c6e427bc5bcbed8e86ab1e555e3f380c011",
                "cs_99597d4d3e8f04b27619ab31d605f22c9062ed52");
        call.enqueue(new Callback<List<ProductsModel>>() {
            @Override
            public void onResponse(retrofit2.Call<List<ProductsModel>> call, Response<List<ProductsModel>> response) {
                if(response.isSuccessful()){

                    Log.d("TAG", "onResponse:  onResponse   "+response.message());

                    List<ProductsModel> productsModel = response.body();
                    Log.d("TAG", "onResponse:  onResponse   "+response.body());
//                    productsModelArrayList = new ArrayList<>();

//                    for(int i=0; i<productsModel.size(); i++){
//                        productsModelArrayList.add(productsModel.get(i));
//                    }

                    setDataToRecycler(response.body());


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

    private void setDataToRecycler(List<ProductsModel> body) {
        ProductsAdapter productsAdapter = new ProductsAdapter((ArrayList<ProductsModel>) body, getActivity(), new ModelCallBack() {
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
        productRecyclerView.setAdapter(productsAdapter);
    }
}