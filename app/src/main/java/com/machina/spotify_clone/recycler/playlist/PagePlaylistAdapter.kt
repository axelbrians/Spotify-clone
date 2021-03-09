package com.machina.spotify_clone.recycler.playlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.machina.spotify_clone.R
import com.machina.spotify_clone.databinding.VhPlaylistHeaderBinding
import com.machina.spotify_clone.databinding.VhPlaylistItemBinding
import kotlin.random.Random

class PagePlaylistAdapter: RecyclerView.Adapter<PagePlaylistBaseVH>() {

    private var count = Random.nextInt(9, 24)
    private val viewType = mutableListOf<Int>()
    private val downloaded = mutableListOf<Boolean>()

    init {
        viewType.add(R.layout.vh_playlist_header)
        repeat(count - 1) { viewType.add(R.layout.vh_playlist_item) }
        repeat(count) { downloaded.add(Random.nextBoolean()) }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagePlaylistBaseVH {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            R.layout.vh_playlist_header -> {
                val binding = VhPlaylistHeaderBinding.inflate(inflater, parent, false)
                PlaylistHeaderVH(binding)
            }
            else -> {
                val binding = VhPlaylistItemBinding.inflate(inflater, parent, false)
                PlaylistItemVH(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: PagePlaylistBaseVH, position: Int) {
        when (holder) {
            is PlaylistItemVH -> { holder.onBind(downloaded[position]) }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewType[position]
    }

    override fun getItemCount(): Int {
        return count
    }
}

class PlaylistHeaderVH(binding: VhPlaylistHeaderBinding)
    : PagePlaylistBaseVH(binding.root)


class PlaylistItemVH(private val binding: VhPlaylistItemBinding)
    : PagePlaylistBaseVH(binding.root){

    fun onBind(downloaded: Boolean) {
        if (!downloaded) binding.vhPlaylistItemDownload.visibility = View.GONE
        else binding.vhPlaylistItemDownload.visibility = View.VISIBLE
    }
}