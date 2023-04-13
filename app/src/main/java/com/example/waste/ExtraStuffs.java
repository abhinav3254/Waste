package com.example.waste;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.waste.database.DBHelper;
import com.example.waste.database.Pojo;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ExtraStuffs {
    static Calendar calendar = Calendar.getInstance();
    static int year = calendar.get(Calendar.YEAR);
    static int month = calendar.get(Calendar.MONTH);
    static int date = calendar.get(Calendar.DATE);
    public static void setDate(TextView editText) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(editText.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                editText.setText(day+"/"+(month+1)+"/"+year);
            }
        },year,month,date);
        datePickerDialog.show();
    }

    public static void showBottomSheet(Pojo pojo, Context context) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet);
        TextView title = bottomSheetDialog.findViewById(R.id.titleBtm);
        TextView date = bottomSheetDialog.findViewById(R.id.dateBtm);
        TextView price = bottomSheetDialog.findViewById(R.id.priceBtm);
        TextView desc = bottomSheetDialog.findViewById(R.id.descBtm);
        MaterialButton deleteBtn = bottomSheetDialog.findViewById(R.id.btmDeleteBtn);
        DBHelper db = new DBHelper(context);

        title.setText(pojo.getTitle());
        date.setText(pojo.getDate());
        price.setText(pojo.getPrice());
        desc.setText(pojo.getDesc());

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to Delete ?");
                builder.setTitle("Delete !");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    db.deleteOne(pojo);
                });

                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        bottomSheetDialog.show();
    }
}
