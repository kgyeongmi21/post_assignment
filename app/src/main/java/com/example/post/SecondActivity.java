package com.example.post;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.post.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    ActivitySecondBinding binding;
    int selectYear, selectMonth, selectDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String message = intent.getStringExtra("EXTRA_MESSAGE");
        binding.textReceive.setText("" + message);
        ///binding.time.setText(getApplicationContext(),
               // Integer.toString(selectYear) + "년 " +
                //Integer.toString(selectMonth) + "월 " +
                //Integer.toString(selectDay) + "일 " +
                //Integer.toString(getCurrentHour()) + "시 " +
                //Integer.toString(getCurrentMinute()) + "분 ",
                //show());
    }
}
