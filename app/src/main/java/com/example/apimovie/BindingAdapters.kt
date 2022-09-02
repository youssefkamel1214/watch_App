package com.example.apimovie

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apimovie.controller.MoiveAdapter
import com.example.apimovie.models.Movie

@BindingAdapter("reclermovieimage")
fun bindimageview_movie(imageView: ImageView,movie: Movie){
    val images_path = "https://image.tmdb.org/t/p/w500"
    Glide.with(imageView.context).load(images_path+ movie.poster).placeholder(R.mipmap.ic_launcher).into(imageView)
    imageView.contentDescription=imageView.context.getString(R.string.imageviewcontentMovie,movie.title)
}
@BindingAdapter("loadingfirst")
fun bindprogressbarfirstloading(progressBar: ProgressBar,loading:Boolean){
    if (loading){
        progressBar.visibility=View.VISIBLE
    }
    else{
        progressBar.visibility=View.GONE
    }
}
@BindingAdapter("reclerfirstloading")
fun bindreclerfirsloading(recyclerView: RecyclerView,firstloading:Boolean){
    if (firstloading){
        recyclerView.visibility=View.GONE
    }else{
        recyclerView.visibility=View.VISIBLE
    }
}@BindingAdapter("reclerloading")
fun bindreclerloading(recyclerView: RecyclerView,loading:Boolean){
    if (loading){
        val adapter=recyclerView.adapter as MoiveAdapter
        adapter.putloadingitm()
    }
}
@BindingAdapter("reclerlist")
fun bindrelerlist(recyclerView: RecyclerView,movies: List<Movie>?){
    val adapter=recyclerView.adapter as MoiveAdapter
    movies?.let {
                 if(movies.isNotEmpty())
                      adapter.sumblist(it)
    }
}