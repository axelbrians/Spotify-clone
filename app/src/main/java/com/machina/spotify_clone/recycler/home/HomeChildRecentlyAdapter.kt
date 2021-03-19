package com.machina.spotify_clone.recycler.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.machina.spotify_clone.R
import com.machina.spotify_clone.databinding.VhCircleSmallBinding
import com.machina.spotify_clone.databinding.VhSquareSmallBinding
import com.machina.spotify_clone.recycler.listener.ArtistClickListener
import kotlin.random.Random

class HomeChildRecentlyAdapter(private val artistClickListener: ArtistClickListener): RecyclerView.Adapter<HomeBaseSmallVH>() {

    /*
    count is used as item count in this recycler
    itemType for each item that exist generated randomly inside init block
     */
    var count = 0
    private val itemType = mutableListOf<Int>()

    init {
        count = Random.nextInt(4, 9)
        repeat(count) {
            val number = Random.nextInt(1, 3)
            if (number == 1) itemType.add(R.layout.vh_square_small)
            else itemType.add(R.layout.vh_circle_small)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBaseSmallVH {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.vh_square_small -> {
                val binding = VhSquareSmallBinding.inflate(inflater, parent, false)
                HomeSquareSmallVH(binding)
            }
            else -> {
                val binding = VhCircleSmallBinding.inflate(inflater, parent, false)
                HomeCircleSmallVH(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: HomeBaseSmallVH, position: Int) {
        when (holder) {
            is HomeSquareSmallVH -> { holder.onBind("Playlist name") }
            is HomeCircleSmallVH -> { holder.onBind("TAEYEON", artistClickListener) }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return itemType[position]
    }

    override fun getItemCount(): Int {
        return count
    }
}

class HomeSquareSmallVH(private val binding: VhSquareSmallBinding): HomeBaseSmallVH(binding.root) {
    private val textView: TextView = binding.vhSquareSmallText

    fun onBind(text: String) {
        textView.text = text
    }
}

class HomeCircleSmallVH(private val binding: VhCircleSmallBinding): HomeBaseSmallVH(binding.root) {
    private val textView: TextView = binding.vhCircleSmallText

    fun onBind(text: String, listener: ArtistClickListener) {
        textView.text = text
        binding.vhCircleSmallContainer.setOnClickListener {
            listener.onArtistClick()
        }
    }
}