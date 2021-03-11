package com.machina.spotify_clone.recycler.artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.machina.spotify_clone.R
import com.machina.spotify_clone.databinding.VhArtistHeaderBinding
import com.machina.spotify_clone.databinding.VhArtistPopularBinding
import com.machina.spotify_clone.databinding.VhArtistTrackBinding
import kotlin.random.Random

class PageArtistAdapter(private val scale: Float): RecyclerView.Adapter<PageArtistBaseVH>() {

    private val count = 11
    private val typeList = mutableListOf<Int>()


    init {
        typeList.add(R.layout.vh_artist_header)
        repeat(5) { typeList.add(R.layout.vh_artist_track)}
        typeList.add(R.layout.vh_artist_header)
        repeat(4) { typeList.add(R.layout.vh_artist_popular)}
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageArtistBaseVH {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            R.layout.vh_artist_header -> {
                val binding = VhArtistHeaderBinding.inflate(inflater, parent, false)
                ArtistHeaderVH(binding)
            }
            R.layout.vh_artist_track -> {
                val binding = VhArtistTrackBinding.inflate(inflater, parent, false)
                ArtistTrackVH(binding)
            }
            R.layout.vh_artist_popular -> {
                val binding = VhArtistPopularBinding.inflate(inflater, parent, false)
                ArtistPopularVH(binding)
            }
            else -> {
                val binding = VhArtistTrackBinding.inflate(inflater, parent, false)
                ArtistTrackVH(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: PageArtistBaseVH, position: Int) {
        when (holder) {
            is ArtistHeaderVH -> { holder.onBind(position, scale) }
            is ArtistTrackVH -> { holder.onBind(position) }
            is ArtistPopularVH -> { holder.onBind() }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return typeList[position]
    }

    override fun getItemCount(): Int {
        return count
    }
}

class ArtistHeaderVH(private val binding: VhArtistHeaderBinding): PageArtistBaseVH(binding.root) {

    fun onBind(position: Int, scale: Float) {
        val btm = (7 * scale + 0.5f).toInt()
        val horizontal = (14 * scale + 0.5f).toInt()
        if (position == 0) {
            val top = (48 * scale + 0.5f).toInt()
            binding.vhArtistHeaderContainer.setPadding(horizontal, top, horizontal, btm)
        } else {
            val top = (60 * scale + 0.5f).toInt()
            binding.vhArtistHeaderContainer.setPadding(horizontal, top, horizontal, btm)
        }
    }
}

class ArtistTrackVH(private val binding: VhArtistTrackBinding): PageArtistBaseVH(binding.root) {

    fun onBind(position: Int) {
        binding.vhArtistTrackNumber.text = "$position"
    }
}


class ArtistPopularVH(private val binding: VhArtistPopularBinding): PageArtistBaseVH(binding.root) {

    fun onBind() {

    }
}