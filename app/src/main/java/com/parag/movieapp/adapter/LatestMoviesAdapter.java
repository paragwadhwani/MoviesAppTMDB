package com.parag.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parag.movieapp.MovieDetailActivity;
import com.parag.movieapp.Util;
import com.parag.movieapp.databinding.ItemMovieBinding;
import com.parag.movieapp.model.LatestMoviesResponse;
import com.parag.movieapp.model.LatestMoviesResult;
import com.parag.movieapp.model.Movie;

import java.util.ArrayList;

public class LatestMoviesAdapter extends RecyclerView.Adapter<LatestMoviesAdapter.MyViewHolder> {
    Context context;
    ArrayList<LatestMoviesResult> list;

    public LatestMoviesAdapter(ArrayList<LatestMoviesResult> list) {
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
        }

        public void bind(LatestMoviesResult movie, int position) {

            binding.getRoot().setOnClickListener(view -> {
                Intent x = new Intent(binding.getRoot().getContext(), MovieDetailActivity.class);
                x.putExtra("movieId" , movie.getId());
                binding.getRoot().getContext().startActivity(x);
            });


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
