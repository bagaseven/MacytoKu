package com.example.asus.macytoku;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kd.dynamic.calendar.generator.ImageGenerator;

import java.util.Calendar;


public class Jadwal extends Activity {

        EditText mDateEditText;
        EditText mTimeEditText;
        Calendar mCurrentDate;

        Bitmap mGeneratedDateIcon;
        ImageGenerator mImageGenerator;
        ImageView mDisplayGeneratedImage;
        private Button btnpesan;
        private TextView tv_pesan;

        DatabaseReference pesananku;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_jadwal);

            pesananku = FirebaseDatabase.getInstance().getReference().child("pesananku");

            btnpesan = (Button) findViewById(R.id.btn_pesan);

            mImageGenerator = new ImageGenerator(this);
            mDateEditText = (EditText)findViewById(R.id.txtDateEntered);
            mDisplayGeneratedImage = (ImageView)findViewById(R.id.imageGen);
            mTimeEditText = (EditText)findViewById(R.id.txtTimeEntered);

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

            mTimeEditText.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Calendar mcurrentTime = Calendar.getInstance();
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(Jadwal.this, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            mTimeEditText.setText(selectedHour + ":" + selectedMinute);
                        }
                    }, hour, minute, true);//Yes 24 hour time
                    mTimePicker.setTitle("Select Time");
                    mTimePicker.show();
                }
            });
        }
    @Override
    protected void onStart() {
        super.onStart();

        pesananku.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String pesanan_saya = dataSnapshot.getValue(String.class);
                tv_pesan.setText(pesanan_saya);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        btnpesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pesananku.setValue("mantan");
            }
        });
    }
}

