package models;
import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Movie {
  int  id;
  @SerializedName("poster_path")
  String poster;
  boolean adult;
  String overview;
  String release_date;
  String original_title;
  @SerializedName("original_language")
  String language;
  String title;
  String backdrop_path;
  double popularity;
  int vote_count;
  double vote_average;
  boolean video;

  public int getId() {
    return id;
  }

  public String getPoster() {
    return poster;
  }

  public boolean isAdult() {
    return adult;
  }

  public String getOverview() {
    return overview;
  }

  public String getRelease_date() {
    return release_date;
  }

  public String getOriginal_title() {
    return original_title;
  }

  public String getLanguage() {
    return language;
  }

  public String getTitle() {
    return title;
  }

  public String getBackdrop_path() {
    return backdrop_path;
  }

  public double getPopularity() {
    return popularity;
  }

  public int getVote_count() {
    return vote_count;
  }

  public double getVote_average() {
    return vote_average;
  }

  public boolean isVideo() {
    return video;
  }
}
