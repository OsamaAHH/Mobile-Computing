package com.app.e_commerceapp.api;




import com.app.e_commerceapp.homeModels.ProductsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("products")
    Call<List<ProductsModel>> getProducts(@Query("consumer_key") String consumer_key,
                                         @Query("consumer_secret") String consumer_secret);

        }
