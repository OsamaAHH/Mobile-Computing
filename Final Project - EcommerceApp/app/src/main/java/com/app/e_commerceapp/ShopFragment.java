package com.app.e_commerceapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.e_commerceapp.adapters.ProductsAdapter;
import com.app.e_commerceapp.models.Products;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class ShopFragment extends Fragment {


    ArrayList<Products> productsArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shop, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);


        OkHttpClient client = new OkHttpClient();

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
        });

    }
}