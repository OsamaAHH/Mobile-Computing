package com.app.e_commerceapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.app.e_commerceapp.adapters.CartProductsAdapter;
import com.app.e_commerceapp.interfaceCallBack.CartCallBack;
import com.app.e_commerceapp.roomdatabase.AppDatabase;
import com.app.e_commerceapp.roomdatabase.User;
import com.app.e_commerceapp.roomdatabase.UserDao;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

public class CartFragment extends Fragment implements CartCallBack {

    RecyclerView productsInCartRecyclerView;
    TextView itemCountTv, totalRupeeCount, amountSubTotal, AmountTotalTv, noProductInCart;
    Button placeOrderBtn;
    ConstraintLayout mainLayout;

    private List<User> allUserList;
    int itemCount = 0;
    String totalPriceNow;
    double totalPrice = 0;

    CartFragment fragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_cart, container, false);


        productsInCartRecyclerView = view.findViewById(R.id.cartProductsRecycler);
        itemCountTv = view.findViewById(R.id.itemCountTv);
        totalRupeeCount = view.findViewById(R.id.totalRupeeCount);
        amountSubTotal = view.findViewById(R.id.amountSubTotal);
        AmountTotalTv = view.findViewById(R.id.AmountTotalTv);
        placeOrderBtn = view.findViewById(R.id.placeOrderBtn);
        mainLayout = view.findViewById(R.id.mainLayout);
        noProductInCart = view.findViewById(R.id.noProductInCartTv);

        productsInCartRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(requireActivity(),PlaceOrderActivity.class);
                intent.putExtra("itemCount","ITEMS ("+itemCount+")");
                intent.putExtra("totalPrice","$ "+totalPriceNow);
                startActivity(intent);


            }
        });



        getProducts();
        return view;


    }

    private void getProducts() {

        AppDatabase db = Room.databaseBuilder(getContext(),
                AppDatabase.class, "room_db").allowMainThreadQueries().build();

        UserDao userDao = db.userDao();
        allUserList = userDao.getallusers();

        if(allUserList.size()==0){
            mainLayout.setVisibility(View.GONE);
            noProductInCart.setVisibility(View.VISIBLE);
        }else{
            NumberFormat f = NumberFormat.getInstance(); // Gets a NumberFormat with the default locale, you can specify a Locale as first parameter (like Locale.FRENCH)
            totalPrice = 0.0;
            itemCount=0;
            for(int i=0; i<allUserList.size(); i++){
                int q = allUserList.get(i).getProductQuantity();
                double price = Double.parseDouble(allUserList.get(i).getProductPrice());
                itemCount = itemCount + q;
                if(q>1){
                    totalPrice = totalPrice +(price*q);
//                    totalPrice = totalPrice + (Double.valueOf(allUserList.get(i).getProductQuantity())*Double.valueOf(allUserList.get(i).getProductPrice()));
                }else {
//                    try {
                        totalPrice = totalPrice +price;
//                        totalPrice = (double) f.parse(totalPrice + allUserList.get(i).getProductPrice());
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
                }
            }

            String totalPriceNow = String.format("%.2f", totalPrice);
            itemCountTv.setText("ITEMS ("+itemCount+")");
            totalRupeeCount.setText("TOTAL : $"+ totalPriceNow);
            amountSubTotal.setText("$ "+totalPriceNow);
            AmountTotalTv.setText("$ "+totalPriceNow);

            mainLayout.setVisibility(View.VISIBLE);
            noProductInCart.setVisibility(View.GONE);
            CartProductsAdapter cartProductsAdapter = new CartProductsAdapter(allUserList, getActivity(), this::onClickProduct);
            productsInCartRecyclerView.setAdapter(cartProductsAdapter);

        }




    }

    @Override
    public void onClickProduct(boolean reloadFragment) {

        if(reloadFragment){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                getActivity().getSupportFragmentManager().beginTransaction().detach(this).commitNow();
                getActivity().getSupportFragmentManager().beginTransaction().attach(this).commitNow();
            } else {
                getActivity().getSupportFragmentManager().beginTransaction().detach(this).attach(this).commit();
            }
        }

    }

    @Override
    public void onPause() {
        super.onPause();
    }
}