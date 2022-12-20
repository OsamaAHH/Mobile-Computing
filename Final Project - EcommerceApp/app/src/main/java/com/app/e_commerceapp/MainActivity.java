package com.app.e_commerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.app.e_commerceapp.models.Products;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    ShopFragment shopFragment = new ShopFragment();
    CategoryFragment categoryFragment = new CategoryFragment();
    CartFragment cartFragment = new CartFragment();
ArrayList<Products> productsArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.home:
                            getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                            return true;

                        case R.id.shop:
                            getSupportFragmentManager().beginTransaction().replace(R.id.container, shopFragment).commit();
                            return true;

                        case R.id.category:
                            getSupportFragmentManager().beginTransaction().replace(R.id.container, categoryFragment).commit();
                            return true;

                        case R.id.cart:
                            getSupportFragmentManager().beginTransaction().replace(R.id.container, cartFragment).commit();
                            return true;

                    }
                return false;
            }
        });


        OkHttpClient client = new OkHttpClient();

        Request get = new Request.Builder()
                .url("https://luxxedit.com/wp-json/wc/v3/products?consumer_key=ck_9560bf9c146bbd47c2a8a26ed768e6f4326e3b36&consumer_secret=cs_5be146f6179135689b4b7cf93afa8a4a98601cd1")
                .build();

        client.newCall(get).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    ResponseBody responseBody = response.body();
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    Log.i("data", responseBody.string());

                    JSONArray jsonArray  = new JSONArray(responseBody.string());
                    for(int i = 0; i < jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Products product = new Products();
                        product.parsingObject(jsonObject);
                        productsArrayList.add(product);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

}