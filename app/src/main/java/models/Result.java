package models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    int page;
    int total_results;

    public List<Movie> getMovies() {
        return movies;
    }

    int total_pages;
    @SerializedName("results")
     List<Movie> movies;
    public int getPage() {
        return page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

}
