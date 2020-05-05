package com.htueko.android.demots.adapter

import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.htueko.android.demots.R
import com.htueko.android.demots.data.model.MovieModel

class MovieRecyclerViewAdapter(
    private var movies: List<MovieModel>
) : RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun updateMovies(movies: List<MovieModel>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val card: CardView = itemView.findViewById(R.id.card_item)

        private val poster: ImageView = itemView.findViewById(R.id.imv_item)
        private val title: TextView = itemView.findViewById(R.id.tv_title_item)
        private val overview: TextView = itemView.findViewById(R.id.tv_overview_item)
        private val rating: RatingBar = itemView.findViewById(R.id.ratingBar_item)

        fun bind(movie: MovieModel) {
            title.text = movie.title
            overview.text = movie.overview
            overview.movementMethod = ScrollingMovementMethod()
            val rate = movie.rating
            rating.rating = movie.rating
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
                .transform(CenterCrop())
                .into(poster)
        }

    }
}
