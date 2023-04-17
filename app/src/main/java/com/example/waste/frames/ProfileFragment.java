package com.example.waste.frames;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.waste.ExtraStuffs;
import com.example.waste.R;
import com.example.waste.database.DBHelper;
import com.example.waste.database.DBProfile;
import com.example.waste.database.Pojo;
import com.example.waste.database.ProfilePojo;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProfileFragment extends Fragment {

    MaterialCardView editProfile;
    TextView textView;
    ProfilePojo profilePojo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        editProfile = view.findViewById(R.id.editProfile);
        textView = view.findViewById(R.id.profileName);

        List<ProfilePojo> listNew = new ArrayList<>();

        listNew = readProfile(view.getContext());
        if (listNew.isEmpty()) {
        } else {
            textView.setText(listNew.get(listNew.size()-1).getName());
        }

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExtraStuffs.profileBottomSheet(view.getContext());
            }
        });


        return view;
    }

    public List<ProfilePojo> readProfile(Context context) {
        DBProfile dbHelper = new DBProfile(context);
        List<ProfilePojo> list = new ArrayList<>();
        Cursor cursor = dbHelper.readProfile();
        if (cursor.getCount() == 0) {
        } else {
            while (cursor.moveToNext()) {
                profilePojo = new ProfilePojo(cursor.getString(0),cursor.getString(1));
                list.add(profilePojo);
            }
        }
        return list;
    }
}