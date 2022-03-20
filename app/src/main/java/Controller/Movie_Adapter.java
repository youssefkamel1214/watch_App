package Controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apimovie.databinding.MovieReclerItemBinding;

import org.chromium.base.task.TaskRunner;
import org.chromium.base.task.TaskRunnerImpl;
import org.chromium.base.task.TaskTraits;

import java.util.ArrayList;
import java.util.List;

import models.Movie;

public class Movie_Adapter extends RecyclerView.Adapter<Movie_Adapter.Viewholder>{
    ArrayList<Movie>movies;
    ArrayList<Boolean>hasimages;
    Maincontroller maincontroller;
    String images_path="https://image.tmdb.org/t/p/w500";
    public Movie_Adapter(ArrayList<Movie> movies,Maincontroller maincontroller) {
        this.movies = movies;
        hasimages=new ArrayList<Boolean>();
        this.maincontroller=maincontroller;
        for (int i=0;i<movies.size();i++)
            hasimages.add(true);
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(MovieReclerItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
          Movie movie=movies.get(position);
          holder.binding.cardview.setOnClickListener(view -> {
              maincontroller.move_to_egybest(movie);
          });
          holder.binding.title.setText( movie.getTitle());
          holder.binding.date.setText(movie.getRelease_date());
          holder.binding.overview.setText(movie.getOverview());
          if(hasimages.get(position)) {
              NetImage netImage = new NetImage(images_path + movie.getPoster(), holder.binding.image);
              netImage.executeWithTaskTraits(TaskTraits.THREAD_POOL_BEST_EFFORT);
              hasimages.set(position, false);
          }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static  class Viewholder extends RecyclerView.ViewHolder{
 public  final MovieReclerItemBinding binding;
        public Viewholder(MovieReclerItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
