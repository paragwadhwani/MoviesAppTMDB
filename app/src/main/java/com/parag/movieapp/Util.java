package com.parag.movieapp;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.parag.movieapp.model.LatestMoviesResponse;
import com.parag.movieapp.model.LatestMoviesResult;
import com.parag.movieapp.model.Movie;
import com.parag.movieapp.model.MovieDetailModel;
import com.parag.movieapp.model.MovieResponse;
import com.parag.movieapp.model.PopularMoviesResponse;

import org.json.JSONObject;

public class Util {
    private static String apiKey = "6873466499c921d1132c5ee891a80c90";
    private static String bearerToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2ODczNDY2NDk5YzkyMWQxMTMyYzVlZTg5MWE4MGM5MCIsInN1YiI6IjYyYWRkYjkyOTYwY2RlMDA2MWZlZjgyNSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.SOHMMInheK94u7zLZsNM5u4njNSSkjLi9-M8hTyPUIE";
    public static String baseUrl = "https://api.themoviedb.org";
    public static String baseImageUrl = "https://image.tmdb.org/t/p/w500";

    public static String moviesUrl ="/4/list/1";
    public static String popularMoviesUrl ="/3/movie/popular";
    public static String latestMoviesUrl ="/3/movie/upcoming";
//    public static String latestMoviesUrl ="/3/movie/latest"; //In latest there is only one movie object thats why i have used upcoming here

    public static String moviesDetailUrl ="/3/movie/";



    private static ANRequest.GetRequestBuilder getApi(String url) {
        return AndroidNetworking.get(baseUrl + url).addHeaders("Authorization", bearerToken);

    }

//    public static void getMovieApi(int pageCount,OnMovieResponse callBack) {
//        getApi(moviesUrl).addQueryParameter("page", "" + pageCount).addQueryParameter("api_key", apiKey).setPriority(Priority.IMMEDIATE).build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        showLog("onResponce " + response);
//                        if(callBack!=null) {
//                            callBack.onSuccess(new Gson().fromJson(response.toString(),MovieResponse.class));
//                        }
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        showLog("onError " + anError.getResponse());
//                        if(callBack!=null)
//                            callBack.onFaliure(""+anError.getErrorBody());
//                    }
//                });
//
//    }

    public static void getPopularMovieApi(int pageCount,OnMovieResponse callBack) {
        getApi(popularMoviesUrl).addQueryParameter("page", "" + pageCount).addQueryParameter("api_key", apiKey).setPriority(Priority.IMMEDIATE).build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        showLog("onResponce " + response);
                        if(callBack!=null) {
                            callBack.onSuccess(new Gson().fromJson(response.toString(), LatestMoviesResponse.class));
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        showLog("onError " + anError.getResponse());
                        if(callBack!=null)
                            callBack.onFaliure(""+anError.getErrorBody());
                    }
                });

    }


    public static void getLatestMovieApi(int pageCount,OnMovieResponse callBack) {
        getApi(latestMoviesUrl).addQueryParameter("page", "" + pageCount).addQueryParameter("api_key", apiKey).setPriority(Priority.IMMEDIATE).build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        showLog("onResponce " + response);
                        if(callBack!=null) {
                            callBack.onSuccess(new Gson().fromJson(response.toString(),LatestMoviesResponse.class));
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        showLog("onError " + anError.getResponse());
                        if(callBack!=null)
                            callBack.onFaliure(""+anError.getErrorBody());
                    }
                });

    }

    public static void getMovieDetails(int movieId,OnMovieDetailResponse callBack) {
        getApi(moviesDetailUrl + movieId).addQueryParameter("api_key", apiKey).setPriority(Priority.IMMEDIATE).build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        showLog("onResponce " + response);
                        if(callBack!=null) {
                            callBack.onSuccess(new Gson().fromJson(response.toString(),MovieDetailModel.class));
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        showLog("onError " + anError.getResponse());
                        if(callBack!=null)
                            callBack.onFaliure(""+anError.getErrorBody());
                    }
                });

    }

    private static final String TAG = "Util";

    public static void showLog(String message) {
        Log.e(TAG, "showLog: " + message);
    }

    public interface OnMovieResponse{
        void onSuccess(LatestMoviesResponse response);
        void onFaliure(String response);
    }

    public interface OnMovieDetailResponse{
        void onSuccess(MovieDetailModel response);
        void onFaliure(String response);
    }


}