package com.example.peoplerest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;

import com.example.peoplerest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.data.setMovementMethod(new ScrollingMovementMethod());
        RequestTask task = new RequestTask();
        task.finalTask = () -> {
            binding.data.setText(task.response.content);
        };
        task.execute();
    }
}