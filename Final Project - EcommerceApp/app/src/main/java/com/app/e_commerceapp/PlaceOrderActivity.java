package com.app.e_commerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.e_commerceapp.roomdatabase.AppDatabase;
import com.app.e_commerceapp.roomdatabase.UserDao;

public class PlaceOrderActivity extends AppCompatActivity {

    TextView itemCountTv, totalRupeeCount;
    Button placeOrderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        itemCountTv = findViewById(R.id.itemCountTv);
        totalRupeeCount = findViewById(R.id.totalRupeeCount);
        placeOrderBtn = findViewById(R.id.placeOrderBtn);

        String itemCount = getIntent().getStringExtra("itemCount");
        String totalPrice = getIntent().getStringExtra("totalPrice");

        itemCountTv.setText(itemCount);
        totalRupeeCount.setText(totalPrice);

        placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppDatabase db = Room.databaseBuilder(PlaceOrderActivity.this,
                        AppDatabase.class, "room_db").allowMainThreadQueries().build();
                UserDao userDao = db.userDao();
                userDao.deleteallusers();
                Toast.makeText(PlaceOrderActivity.this, "Your Order have submitted successfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(PlaceOrderActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });





    }
}