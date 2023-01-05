package com.app.e_commerceapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.app.e_commerceapp.databinding.ActivityProductDetailsBinding;
import com.app.e_commerceapp.homeModels.ProductsModel;
import com.app.e_commerceapp.interfaceCallBack.ModelCallBack;
import com.app.e_commerceapp.roomdatabase.AppDatabase;
import com.app.e_commerceapp.roomdatabase.User;
import com.app.e_commerceapp.roomdatabase.UserDao;
import com.bumptech.glide.Glide;

public class ProductDetailsActivity extends AppCompatActivity implements ModelCallBack {
    ProductsModel productsModels,model;
    Utils utils;
    ActivityProductDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        model = new ProductsModel();

        model = (ProductsModel)getIntent().getSerializableExtra("Model");
        setDataToUi(model);

        Log.d("setDataToUi", "setDataToUi:  model----  "+model.getName());

        utils = new Utils();
        if (utils.productsModels!=null){
            productsModels = new ProductsModel();
            productsModels = utils.productsModels;
            Log.d("setDataToUi", "setDataToUi:  ----  "+utils.productsModels.getName());

        }


        binding.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });

        binding.productDetailCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "room_db").allowMainThreadQueries().build();

                UserDao userDao = db.userDao();


                Log.d("aliyan", "onClick: "+ model.getId());

                Boolean check = userDao.is_exist(model.getId());

                if(check== false){

                    userDao.insertUser(new User(0, model.getName(),
                            model.getPrice(), model.getId(), model.getImages().get(0).getSrc(), 1));
                    Toast.makeText(ProductDetailsActivity.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                }
                else{

                    userDao.updateById(model.getId(),userDao.getUserFromId(model.getId()).getProductQuantity()+1);

                }


            }
        });





    }

    private void setDataToUi(ProductsModel productsModels) {

        binding.productDetailTitle.setText(productsModels.getName().toString());
        binding.productDetailPrice.setText("Price: "+productsModels.getPrice().toString()+"$");
        if (productsModels.getDescription()==null){
            binding.productDetailDescription.setVisibility(View.GONE);
//            binding.productDetailDescription.setText(Html.fromHtml(productsModels.getShortDescription().toString()));
        }else {
            binding.productDetailDescription.setText(Html.fromHtml(productsModels.getDescription().toString()));
        }
        Log.d("setDataToUi", "setDataToUi:    "+productsModels.getDescription());

        Glide.with(this)
                .load(productsModels.getImages().get(0).getSrc())
                .placeholder(R.drawable.logo)
                .into(binding.productDetailImg);

    }

    @Override
    public void onClickProduct(ProductsModel productsModels) {
        Log.d("setDataToUi", "setDataToUi:   39 "+productsModels.getName());

    }



}