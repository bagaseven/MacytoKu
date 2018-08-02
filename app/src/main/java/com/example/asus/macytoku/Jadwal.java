package com.example.asus.macytoku;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.kd.dynamic.calendar.generator.ImageGenerator;

import java.util.Calendar;


public class Jadwal extends Activity {

        EditText mDateEditText;
        Calendar mCurrentDate;
        Bitmap mGeneratedDateIcon;
        ImageGenerator mImageGenerator;
        ImageView mDisplayGeneratedImage;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_jadwal);

            mImageGenerator = new ImageGenerator(this);
            mDateEditText = (EditText)findViewById(R.id.txtDateEntered);
            mDisplayGeneratedImage = (ImageView)findViewById(R.id.imageGen);

            mImageGenerator.setIconSize(50, 50);
            mImageGenerator.setDateSize(30);
            mImageGenerator.setMonthSize(10);

            mImageGenerator.setDatePosition(42);
            mImageGenerator.setMonthPosition(14);

            mImageGenerator.setDateColor(Color.parseColor("#3c6eaf"));
            mImageGenerator.setMonthColor(Color.WHITE);

            mDateEditText.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    mCurrentDate = Calendar.getInstance();
                    int year = mCurrentDate.get(Calendar.YEAR);
                    int month = mCurrentDate.get(Calendar.MONTH);
                    int day = mCurrentDate.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog mDatePicker = new DatePickerDialog(Jadwal.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                            mDateEditText.setText(selectedDay+"-"+selectedMonth+"-"+selectedYear);
                            mCurrentDate.set(selectedDay, selectedMonth, selectedYear);
                        }
                    }, year, month, day);
                    mDatePicker.show();
                }
            });

        }
    }
