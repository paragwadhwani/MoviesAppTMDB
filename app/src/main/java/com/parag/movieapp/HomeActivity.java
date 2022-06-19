package com.parag.movieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.androidnetworking.AndroidNetworking;
import com.google.android.material.tabs.TabLayout;
import com.parag.movieapp.adapter.MovieAdapter;
import com.parag.movieapp.databinding.ActivityHomeBinding;
import com.parag.movieapp.databinding.ActivitySplashBinding;
import com.parag.movieapp.fragments.LatestMovieFragment;
import com.parag.movieapp.fragments.PopularMovieFragment;
import com.parag.movieapp.model.Movie;
import com.parag.movieapp.model.MovieResponse;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupView();
    }


    TabLayout tabLayout;
    ViewPager viewPager;

    private void setupView() {

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), this, tabLayout);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    class MyAdapter extends FragmentPagerAdapter {

        Context context;
        TabLayout tabLayout;

        public MyAdapter(@NonNull FragmentManager fm, Context context, TabLayout tabLayout) {
            super(fm);
            this.context = context;
            this.tabLayout = tabLayout;
        }


        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment;

            switch (position) {
                case 1: {
                    fragment = new PopularMovieFragment();
                    return fragment;
                }
                default: {
                    fragment = new LatestMovieFragment();
                    return fragment;
                }
            }
        }

        @Override
        public int getCount() {
            return tabLayout.getTabCount();
        }
    }
}