package com.example.waste;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

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
        TextView id = bottomSheetDialog.findViewById(R.id.idAppBtm);
        TextView title = bottomSheetDialog.findViewById(R.id.titleBtm);
        TextView date = bottomSheetDialog.findViewById(R.id.dateBtm);
        TextView price = bottomSheetDialog.findViewById(R.id.priceBtm);
        TextView desc = bottomSheetDialog.findViewById(R.id.descBtm);

        id.setText(pojo.getId());
        title.setText(pojo.getTitle());
        date.setText(pojo.getDate());
        price.setText(pojo.getDate());
        desc.setText(pojo.getDesc());

        bottomSheetDialog.show();
    }
}
