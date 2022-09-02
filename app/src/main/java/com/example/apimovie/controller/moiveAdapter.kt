package com.example.apimovie.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.apimovie.R
import com.example.apimovie.databinding.MovieReclerItemBinding
import com.example.apimovie.models.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoiveAdapter (val clickListener: MovieListhner):androidx.recyclerview.widget.ListAdapter<MoiveAdapter.DataItem,RecyclerView.ViewHolder>(MoiveDiffCallback()){
    private val ITEM_VIEW_TYPE_HEADER = 0
    private val ITEM_VIEW_TYPE_ITEM = 1
    private val adapterScope = CoroutineScope(Dispatchers.Default)
    class Viewholder(binding: MovieReclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: MovieReclerItemBinding
        val res = binding.image.context.resources
        init {
            this.binding = binding
        }

        fun bind(item: Movie, clickListener: MovieListhner) {
            binding.movie = item
            binding.executePendingBindings()
        }
    }
    class loadingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): loadingViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.loadingitem, parent, false)
                return loadingViewHolder(view)
            }
        }
    }

    class MoiveDiffCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }

    }
    sealed class DataItem {
        data class Movieitem(val movie: Movie) : DataItem() {
            override val id = movie.id.toLong()
        }

        object Header : DataItem() {
            override val id = Long.MIN_VALUE
        }

        abstract val id: Long
    }
    class MovieListhner(val clickListener: (movie: Movie) -> Unit) {
        fun onClick(movie: Movie) = clickListener(movie)
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            else -> ITEM_VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> loadingViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> Viewholder(
                MovieReclerItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
              is Viewholder->{
                    val item=getItem(position)as DataItem.Movieitem
                    holder.bind(item.movie,clickListener)
              }
        }
    }
    fun putloadingitm(){
        if(currentList.isNotEmpty()&&currentList.last() is DataItem.Header)
            return
        adapterScope.launch {
            val items = currentList+ listOf(DataItem.Header)
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }
    fun sumblist(list: List<Movie>){
        adapterScope.launch {
            val items =currentList.takeWhile { it is DataItem.Movieitem } +list.map { DataItem.Movieitem(it) }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }
}