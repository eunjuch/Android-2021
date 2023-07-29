package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.finalproject.databinding.ActivityMainBinding;
import com.example.finalproject.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    private ActivitySecondBinding binding;
    private int stopTag = 1;
    private long startTime;
    private long totalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime = System.currentTimeMillis();
                stopTag = 0;
            }
        });

        binding.stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long stopTime = System.currentTimeMillis();
                totalTime = (stopTime - startTime) / 1000;
                stopTag = 1;
            }
        });

    }

    public void returnMain(View view){
        finish();
    }

    @Override
    public void finish() {
        if(stopTag==0){
            Toast.makeText(this.getApplicationContext(),"you did not clicked stop button", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra("standard", totalTime);

        setResult(RESULT_OK, data);
        super.finish();
    }
}