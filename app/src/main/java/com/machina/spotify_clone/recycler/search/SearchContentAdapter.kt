package com.machina.spotify_clone.recycler.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.machina.spotify_clone.R
import com.machina.spotify_clone.databinding.VhSearchContentBinding
import kotlin.random.Random

class SearchContentAdapter(private val size: Int): RecyclerView.Adapter<SearchContentVH>() {

    private val itemType = mutableListOf<Int>()

    init {
        repeat(size) {
            itemType.add(Random.nextInt(0, 4))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchContentVH {
        val binding = VhSearchContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchContentVH(binding)
    }

    override fun onBindViewHolder(holder: SearchContentVH, position: Int) {
        holder.onBind(itemType[position])
    }

    override fun getItemCount(): Int {
        return size
    }
}

class SearchContentVH(binding: VhSearchContentBinding): RecyclerView.ViewHolder(binding.root) {
    private val container: ConstraintLayout = binding.vhSearchContentContainer
    private val imageView: ImageView = binding.vhSearchContentImage

    fun onBind(viewType: Int) {
        when (viewType) {
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