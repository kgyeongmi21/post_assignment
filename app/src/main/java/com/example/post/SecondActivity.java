package com.example.post;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.post.databinding.ActivitySecondBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SecondActivity extends AppCompatActivity {
    ActivitySecondBinding binding;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    String now = sdf.format(new Date(System.currentTimeMillis()));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String message = intent.getStringExtra("EXTRA_MESSAGE");
        binding.textReceive.setText("" + message);
        String mess = intent.getStringExtra("NAME_TIME");
        binding.time.setText(mess + now);
        Intent intent1 = getIntent();
        String mess1 = intent1.getStringExtra("BitmapImage");
        binding.imageReceive.toString();

        binding.buttonExit.setOnClickListener(v -> finish());


    }


    public void finish() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
