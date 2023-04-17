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
import com.example.waste.database.DBProfile;
import com.example.waste.database.ProfilePojo;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
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

        List<ProfilePojo> list = new ArrayList<>();



        if (list.isEmpty()) {
            Toast.makeText(view.getContext(), "Empty", Toast.LENGTH_SHORT).show();
        } else {
            list = readProfile(view.getContext());
            textView.setText(list.get(0).getName());
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
        DBProfile dbProfile = new DBProfile(context);
        List<ProfilePojo> list = new ArrayList<>();
        Cursor cursor = dbProfile.readProfile();
        if (cursor.getCount() == 0) {
        } else {
            while (cursor.moveToNext()) {
                profilePojo = new ProfilePojo(cursor.getString(0), cursor.getString(1));
                list.add(profilePojo);
            }
        }
        return list;
    }
}