package com.machina.spotify_clone.recycler.search

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.machina.spotify_clone.R
import kotlin.random.Random

class SearchContentAdapter(private val size: Int): RecyclerView.Adapter<SearchContentVH>() {

    private val bgType = mutableListOf<Int>()

    init {
        repeat(size) {
            bgType.add(Random.nextInt(0, 4))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchContentVH {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.vh_search_content, parent, false)

        return SearchContentVH(view)
    }

    override fun onBindViewHolder(holder: SearchContentVH, position: Int) {
        holder.onBind(bgType[position])
    }

    override fun getItemCount(): Int {
        return size
    }
}

class SearchContentVH(view: View): RecyclerView.ViewHolder(view) {
    private val container: ConstraintLayout = view.findViewById(R.id.vh_search_content_container)
    private val imageView: ImageView = view.findViewById(R.id.vh_search_content_image)

    fun onBind(bgType: Int) {
        when (bgType) {
            0 -> {
                container.setBackgroundResource(R.drawable.bg_content_green_gradient)
                imageView.setImageResource(R.drawable.search_content_4)
            }
            1 -> {
                container.setBackgroundResource(R.drawable.bg_content_grey_gradient)
                imageView.setImageResource(R.drawable.search_content_1)
            }
            2 -> {
                container.setBackgroundResource(R.drawable.bg_content_lime_gradient)
                imageView.setImageResource(R.drawable.search_content_3)
            }
            else -> {
                container.setBackgroundResource(R.drawable.bg_content_orange)
                imageView.setImageResource(R.drawable.search_content_2)
            }
        }
    }
}