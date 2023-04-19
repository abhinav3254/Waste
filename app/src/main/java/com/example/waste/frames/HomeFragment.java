package com.example.waste.frames;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.waste.R;
import com.example.waste.database.Adapter;
import com.example.waste.database.DBHelper;
import com.example.waste.database.Pojo;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    Pojo pojo;

    ImageView emptyImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recycleView);

        emptyImage = view.findViewById(R.id.emptyImage);

        Adapter adapter = new Adapter(view.getContext(),readAll(view.getContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        // **************************** Action Bar  ****************************

//        ColorDrawable colorDrawable
//                = new ColorDrawable(Color.parseColor("#F9D949"));

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Home");
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(colorDrawable);


        return view;
    }

    public List<Pojo> readAll(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        List<Pojo> list = new ArrayList<>();
        Cursor cursor = dbHelper.readAll();
        if (cursor.getCount() == 0) {
            emptyImage.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                pojo = new Pojo(cursor.getString(0),cursor.getString(1),cursor.getString(4),cursor.getString(3),cursor.getString(2));
                list.add(pojo);
            }
            emptyImage.setVisibility(View.INVISIBLE);

        }
        return list;
    }
}