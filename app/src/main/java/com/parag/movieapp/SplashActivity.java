package com.parag.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.parag.movieapp.databinding.ActivitySplashBinding;


public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;
    private final int secondsDelayed = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        manageSession();

        AndroidNetworking.enableLogging();
        AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);

    }

    private void manageSession() {
        AndroidNetworking.initialize(getApplicationContext());
        new Handler().postDelayed(() -> {
            startActivity(new Intent(binding.getRoot().getContext(),
                    HomeActivity.class));
            finishAffinity();
        }, secondsDelayed * 1000L);
    }
}