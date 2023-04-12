package com.example.waste.frames;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.waste.R;
import com.example.waste.database.Adapter;
import com.example.waste.database.DBHelper;
import com.example.waste.database.Pojo;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    Pojo pojo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recycleView);

        Adapter adapter = new Adapter(view.getContext(),readAll(view.getContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    public List<Pojo> readAll(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        List<Pojo> list = new ArrayList<>();
        Cursor cursor = dbHelper.readAll();
        if (cursor.getCount() == 0) {

        } else {
            while (cursor.moveToNext()) {
                pojo = new Pojo(cursor.getString(0),cursor.getString(1),cursor.getString(4),cursor.getString(3),cursor.getString(2));
                list.add(pojo);
            }
        }
        return list;
    }
}