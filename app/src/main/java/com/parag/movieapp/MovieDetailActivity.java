package com.parag.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;

import com.bumptech.glide.Glide;
import com.parag.movieapp.adapter.LatestMoviesAdapter;
import com.parag.movieapp.databinding.ActivityHomeBinding;
import com.parag.movieapp.databinding.ActivityMovieDetailBinding;
import com.parag.movieapp.model.LatestMoviesResponse;
import com.parag.movieapp.model.MovieDetailModel;

public class MovieDetailActivity extends AppCompatActivity {
    ActivityMovieDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        callApi();

        binding.ivBack.setOnClickListener(View -> {
            finish();
        });
    }


    private void callApi() {

        ProgressUtil loader= new ProgressUtil(this);
        loader.show();
        Util.getMovieDetails(getIntent().getIntExtra("movieId",338953), new Util.OnMovieDetailResponse() {
            @Override
            public void onSuccess(MovieDetailModel response) {
                if(response!=null){
                    setData(response);
                }
                loader.dismiss();
            }

            @Override
            public void onFaliure(String response) {

            }
        }); ;
    }

    private void setData(MovieDetailModel response){
        if (!TextUtils.isEmpty( response.getPosterPath())) {
            Glide.with(binding.getRoot().getContext()).load(Util.baseImageUrl + response.getPosterPath()).into(binding.ivMoviePoster);
            Glide.with(binding.getRoot().getContext()).load(Util.baseImageUrl + response.getPosterPath()).into(binding.ivMoviePosterBackground);
        }
        if (!TextUtils.isEmpty(response.getTitle())) {
            String text = "<font color=#FFFFFF>"+response.getTitle()+" </font> <font color=#8C8C8C>("+response.getReleaseDate().substring(0,4)+")</font>";
            binding.tvTitle.setText(Html.fromHtml(text));
        }
        if (!TextUtils.isEmpty(response.getReleaseDate())) {
            binding.tvDate.setText(response.getReleaseDate());
            binding.tvLaguage.setText(" ("+response.getOriginalLanguage().toUpperCase()+")");
        }
        if (!TextUtils.isEmpty( response.getHomepage())) {
            binding.tvWebsite.setClickable(true);
            binding.tvWebsite.setText(response.getHomepage());

            binding.tvWebsite.setOnClickListener(View ->startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(response.getHomepage()))));

            binding.ivShare.setOnClickListener(View -> {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, response.getHomepage());
                startActivity(Intent.createChooser(shareIntent, "Share To:"));
            });
        }
        if (!TextUtils.isEmpty( response.getOverview())) {
            binding.tvOverview.setText(response.getOverview());
        }
        if (!TextUtils.isEmpty( response.getTagline())) {
            binding.tvTagline.setText(response.getTagline());
        }
        if (!TextUtils.isEmpty(""+response.getBudget())) {
            binding.tvBudget.setText("  ₹  "+response.getBudget());
        }
        if (!TextUtils.isEmpty(""+response.getPopularity())) {
            binding.tvPopularity.setText(""+response.getPopularity());
        }
        if (!TextUtils.isEmpty( response.getReleaseDate())) {
            binding.tvreleaseDate.setText(response.getReleaseDate());
        }
        if (!TextUtils.isEmpty(""+ response.getRevenue())) {
            binding.tvrevenue.setText("  ₹  "+response.getRevenue());
        }
        if (!TextUtils.isEmpty( ""+response.getVoteAverage())) {
            binding.tvvoteAverage.setText(""+response.getVoteAverage());
        }



    }
}