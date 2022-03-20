package com.example.apimovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.apimovie.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Controller.JsonplaceHolderApi;
import Controller.Maincontroller;
import Controller.Movie_Adapter;
import models.Movie;
import models.Result;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Maincontroller {
    private ActivityMainBinding binding;
    final  String base_url="https://api.themoviedb.org/3/movie/";
    final  String egybest_url="https://giga.egybest.org/explore/?q=";
    private Movie_Adapter movie_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        Retrofit retrofit=new Retrofit.Builder().
                baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonplaceHolderApi jsonplaceHolderApi=retrofit.create(JsonplaceHolderApi.class);
            Call<Result> call=jsonplaceHolderApi.get_Result(
                    "1762cd01582de4936e7349f8e6e78b7a",1);
            call.enqueue(new Callback<models.Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    if (response.isSuccessful()){
                         movie_adapter=new Movie_Adapter((ArrayList)response.body().getMovies(),MainActivity.this);
                         binding.rcycler.setAdapter(movie_adapter);
                         binding.rcycler.setVisibility(View.VISIBLE);
                         binding.loading.setVisibility(View.GONE);
                    }
                    else{
                        System.out.println(call.request().toString());
                    }
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                System.err.println(t.getMessage());
                }
            });

    }

    @Override
    public void move_to_egybest(Movie movie) {
        String url=movie.getTitle();
        url=url.toLowerCase();
        url=url.replace(' ', '-');
        url=url.replace(":", "");
        url+="-"+movie.getRelease_date().substring(0, 4);
        url=egybest_url+url;
        System.out.println(url);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}