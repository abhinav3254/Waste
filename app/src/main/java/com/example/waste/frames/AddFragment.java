package com.example.waste.frames;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.waste.ExtraStuffs;
import com.example.waste.R;
import com.example.waste.database.DBHelper;
import com.example.waste.database.Pojo;
import com.example.waste.databinding.FragmentAddBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class AddFragment extends Fragment {

//    FragmentAddBinding binding;
    Activity activity = this.getActivity();
    MaterialButton btn,save;
    TextView editDate;
    TextInputEditText title,price,description;
    List<Pojo> list;
    DBHelper dbHelper;
    Pojo pojo;
    String type;

    Spinner spinner;

    String[] listSpinner = {"food","fashion","grocery","dairy","education","refreshment","alcohol","subscription"};

    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
//        binding = FragmentAddBinding.inflate(getLayoutInflater());

        imageView = view.findViewById(R.id.imageAdd);
        btn = view.findViewById(R.id.btnDate);
        editDate = view.findViewById(R.id.editDate);
        save = view.findViewById(R.id.saveBtn);
        title = view.findViewById(R.id.titleText);
        price = view.findViewById(R.id.priceText);
        spinner = view.findViewById(R.id.spinner);

        final String[] valueSpinner = {""};

        // **************************** Spinner ****************************
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                valueSpinner[0] = listSpinner[i].toLowerCase().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter adapter = new ArrayAdapter(view.getContext(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item,listSpinner);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        description = view.findViewById(R.id.descriptionText);

        Animation animation = AnimationUtils.loadAnimation(view.getContext(),R.anim.animation_two);
        imageView.startAnimation(animation);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExtraStuffs.setDate(editDate);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t = title.getText().toString();
                String p = price.getText().toString();
                String d = editDate.getText().toString();
                String desc = description.getText().toString();
                if (t.isEmpty() || p.isEmpty()) {
                    Toast.makeText(view.getContext(), "Fields can't be epmty", Toast.LENGTH_SHORT).show();
                } else if (d.isEmpty()) {
                    ExtraStuffs.setDate(editDate);
                } else {
                    // save to database
                    pojo = new Pojo(t,p,desc,d,valueSpinner[0]);
                    dbHelper = new DBHelper(view.getContext());
                    boolean ans = dbHelper.addData(pojo);
                    if (ans) {
                        Toast.makeText(view.getContext(), "Added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(view.getContext(), "Error!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // **************************** Action Bar  ****************************

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Add");


        return view;
    }
}