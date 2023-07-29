package com.example.finalproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finalproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> resultLauncher;
    private final int base_sentence_length = 119;
    private long returnTime = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){
                            Intent data = result.getData();

                            returnTime = data.getExtras().getLong("standard");
                        }
                    }
                }
        );

        binding.enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myString = binding.editTextMultiLine.getText().toString();
                int length = myString.length();

                long speachTime = length / (base_sentence_length / returnTime);

                int minute = (int)(speachTime / 60);
                int second = (int)(speachTime - minute * 60);
                binding.textView2.setText(minute + " m " + second + " s");
            }
        });
    }

    public void checkTime(View view){
        Intent intent = new Intent(this, SecondActivity.class);

        resultLauncher.launch(intent);
    }


}