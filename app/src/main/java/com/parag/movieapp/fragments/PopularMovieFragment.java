package com.parag.movieapp.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parag.movieapp.ProgressUtil;
import com.parag.movieapp.R;
import com.parag.movieapp.Util;
import com.parag.movieapp.adapter.LatestMoviesAdapter;
import com.parag.movieapp.adapter.MovieAdapter;
import com.parag.movieapp.model.LatestMoviesResponse;
import com.parag.movieapp.model.LatestMoviesResult;
import com.parag.movieapp.model.Movie;
import com.parag.movieapp.model.MovieResponse;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PopularMovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PopularMovieFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<LatestMoviesResult> list = new ArrayList<>();
    RecyclerView recyclePopular;
    TextView tvEmpty;
    LatestMoviesAdapter adapter;

    int pageCount = 1;
    boolean isLoading = false;

    public PopularMovieFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PopularMovieFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PopularMovieFragment newInstance(String param1, String param2) {
        PopularMovieFragment fragment = new PopularMovieFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_popular_movie, container, false);
        recyclePopular = view.findViewById(R.id.recycle_popular);
        recyclePopular.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new LatestMoviesAdapter(list);
        recyclePopular.setAdapter(adapter);
        tvEmpty = view.findViewById(R.id.tvEmpty);
        callApi();

        recyclePopular.addOnScrollListener(new RecyclerView.OnScrollListener() {  //parag for pagination
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visiblePosition = ((GridLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();

                if (!isLoading && visiblePosition == (list.size() - 1)) {
                    isLoading = true;
                    callApi();
                }
            }
        });


        return view;
    }

    private void callApi() {
        ProgressUtil loader = new ProgressUtil(getContext());
        loader.show();
        Util.getPopularMovieApi(pageCount, new Util.OnMovieResponse() {
            @Override
            public void onSuccess(LatestMoviesResponse response) {
                loader.dismiss();
                if (response != null && response.getResults() != null && !response.getResults().isEmpty()) {
                    pageCount = response.getPage()+1;
                    int lastcount = list.size() -1;
                    list.addAll(response.getResults());
                    adapter.notifyItemRangeChanged(lastcount , list.size()-1);
                    isLoading = false;
                }
                else {
                    if (pageCount == 1) {
                        tvEmpty.setVisibility(View.VISIBLE);
                        recyclePopular.setVisibility(View.GONE);
                    }
                    isLoading = false;
                }
            }
            @Override
            public void onFaliure(String response) {
                loader.dismiss();
                isLoading = false;
                if (pageCount == 1) {
                    tvEmpty.setVisibility(View.VISIBLE);
                    recyclePopular.setVisibility(View.GONE);
                }
            }
        });
        ;
    }
}