package com.example.waste.frames;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.waste.ExtraStuffs;
import com.example.waste.R;
import com.example.waste.database.DBHelper;
import com.example.waste.database.ProfilePojo;
import com.google.android.material.card.MaterialCardView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    MaterialCardView editProfile,deleteAllBtn;
    TextView textView,totalExpenseProfile,totalIncomeProfile,incomePercentage,expensePercentage;
    ProfilePojo profilePojo;

    PieChart pieChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        editProfile = view.findViewById(R.id.editProfile);
        textView = view.findViewById(R.id.profileName);
        totalExpenseProfile = view.findViewById(R.id.totalExpenseProfile);
        totalIncomeProfile = view.findViewById(R.id.totalIncomeProfile);
        incomePercentage = view.findViewById(R.id.incomePercentage);
        expensePercentage = view.findViewById(R.id.expensePercentage);

        deleteAllBtn = view.findViewById(R.id.deleteAllBtn);

        pieChart = view.findViewById(R.id.pieChart);

        // **************** fetching profile data ****************

        List<ProfilePojo> listNew = new ArrayList<>();

        listNew = readProfile(view.getContext());
        if (listNew.isEmpty()) {
            DBHelper dbProfile = new DBHelper(view.getContext());
            profilePojo = new ProfilePojo(textView.getText().toString(),"0");
            dbProfile.addProfileData(profilePojo);
        } else {
            textView.setText(listNew.get(listNew.size()-1).getName());
        }

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExtraStuffs.profileBottomSheet(view.getContext());
            }
        });

        Double ans = getAllExpenses(view.getContext());
        totalExpenseProfile.setText(ans+"");

        // **************************** pie chart work  ****************************
        graphShow(view.getContext());


        // **************************** percentage work  ****************************
        getPercent(totalIncomeProfile.getText().toString(),totalExpenseProfile.getText().toString());

        // **************************** Action Bar  ****************************

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Profile");

        // **************************** Delete All  ****************************

        deleteAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogue(view.getContext());
            }
        });


        return view;
    }

    public List<ProfilePojo> readProfile(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        List<ProfilePojo> list = new ArrayList<>();
        Cursor cursor = dbHelper.readProfile();
        if (cursor.getCount() == 0) {
        } else {
            while (cursor.moveToNext()) {
                profilePojo = new ProfilePojo(cursor.getString(0),cursor.getString(1),cursor.getString(2));
                list.add(profilePojo);
            }
        }
        return list;
    }

    // **************************** get all expenses  ****************************

    public Double getAllExpenses (Context context) {
        DBHelper dbHelper = new DBHelper(context);
        List<Double> list = new ArrayList<>();
        Cursor cursor = dbHelper.getAllExpenses();

        if (cursor.getCount() == 0) {

        } else {
            while (cursor.moveToNext()) {
                Double value = Double.parseDouble(cursor.getString(0));
                list.add(value);
            }
        }
        Double sum = 0.0;
        for (Double values: list)
            sum = sum+values;

        return sum;
    }

    // **************************** pie chart work  ****************************

    public void graphShow (Context context) {
        pieChart.addPieSlice(
                new PieModel(
                        "Income",
                        (float) Double.parseDouble(totalIncomeProfile.getText().toString()),
                        Color.parseColor("#ffa527")
                )
        );
        pieChart.addPieSlice(
                new PieModel(
                        "Expense",
                        (float) Double.parseDouble(totalExpenseProfile.getText().toString()),
                        Color.parseColor("#62CDFF")
                )
        );

        Animation animation = AnimationUtils.loadAnimation(context,R.anim.animation_two);
        pieChart.startAnimation(animation);

        pieChart.startAnimation();

    }

    // **************************** percentage work  ****************************

    public void getPercent (String inc,String exp) {

        Log.d("find","inc :-> "+inc+" exp -> "+exp);

        double income = Double.parseDouble(inc);
        double expense = Double.parseDouble(exp);

        Log.d("find2","inc ->"+income+" exp "+expense);

        expense = expense/income;

        double v1 = Math.round(expense*100);
        double v2 = Math.round(100-v1);


        expensePercentage.setText(v1+"%");
        incomePercentage.setText(v2+"%");
    }

    public void alertDialogue (Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure to delete all ?");

        builder.setTitle("Delete All");

        builder.setCancelable(false);

        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            DBHelper dbHelper = new DBHelper(context);
            dbHelper.deleteAll();
        });

        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}