package com.machina.spotify_clone.recycler.artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.machina.spotify_clone.R
import com.machina.spotify_clone.databinding.VhArtistCircleBinding
import com.machina.spotify_clone.databinding.VhArtistSquareBinding
import kotlin.random.Random

class PageArtistChildAdapter: RecyclerView.Adapter<PageArtistBaseVH>() {

    private val count = Random.nextInt(6, 14)
    private val typeList = mutableListOf<Int>()


    init {
        repeat(count) { typeList.add(R.layout.vh_artist_square)}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageArtistBaseVH {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            R.layout.vh_artist_square -> {
                val binding = VhArtistSquareBinding.inflate(inflater, parent, false)
                PageArtistSquareVH(binding)
            }
            else -> {
                val binding = VhArtistCircleBinding.inflate(inflater, parent, false)
                PageArtistCircleVH(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: PageArtistBaseVH, position: Int) {

    }

    override fun getItemCount(): Int {
        return count
    }

    override fun getItemViewType(position: Int): Int {
        return typeList[position]
    }

}

class PageArtistCircleVH(binding: VhArtistCircleBinding): PageArtistBaseVH(binding.root)

class PageArtistSquareVH(binding: VhArtistSquareBinding): PageArtistBaseVH(binding.root)

