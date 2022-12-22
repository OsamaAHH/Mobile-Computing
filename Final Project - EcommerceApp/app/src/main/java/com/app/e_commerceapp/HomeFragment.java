package com.app.e_commerceapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class HomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
    recyclerView.setHasFixedSize(true)
    recycler.layoutManager = GridLayoutManager(context this, 2, GridLayoutManager.VERTICAL, reverseLayout: false)

}