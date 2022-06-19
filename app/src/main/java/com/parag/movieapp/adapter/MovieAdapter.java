package com.parag.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parag.movieapp.MovieDetailActivity;
import com.parag.movieapp.Util;
import com.parag.movieapp.databinding.ItemMovieBinding;
import com.parag.movieapp.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    Context context;
    ArrayList<Movie> list;

    public MovieAdapter(ArrayList<Movie> list) {
//        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemMovieBinding binding;

        public MyViewHolder(@NonNull ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(view -> binding.getRoot().getContext().startActivity(new Intent(binding.getRoot().getContext(), MovieDetailActivity.class)));
        }

        public void bind(Movie movie, int position) {
            if (!TextUtils.isEmpty( movie.getPosterPath())) {
                Glide.with(binding.getRoot().getContext()).load(Util.baseImageUrl + movie.getPosterPath()).into(binding.img);
            }
            if (!TextUtils.isEmpty(movie.getTitle())) {
                binding.tvTitle.setText(movie.getTitle());
            }
            if (!TextUtils.isEmpty(movie.getReleaseDate())) {
                binding.tvDate.setText(movie.getReleaseDate());
            }
        }
    }
}
