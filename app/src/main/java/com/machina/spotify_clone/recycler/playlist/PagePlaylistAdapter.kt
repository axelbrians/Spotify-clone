package com.machina.spotify_clone.recycler.playlist

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.machina.spotify_clone.R
import com.machina.spotify_clone.databinding.VhPlaylistHeaderBinding
import com.machina.spotify_clone.databinding.VhPlaylistItemBinding
import com.machina.spotify_clone.databinding.VhPlaylistReommendationBinding
import kotlin.random.Random

class PagePlaylistAdapter: RecyclerView.Adapter<PagePlaylistBaseVH>() {

    private var count = Random.nextInt(9, 24)
    private val viewType = mutableListOf<Int>()
    private val downloaded = mutableListOf<Boolean>()

    init {
        viewType.add(R.layout.vh_playlist_header)
        repeat(count - 2) { viewType.add(R.layout.vh_playlist_item) }
        repeat(count) { downloaded.add(Random.nextBoolean()) }
        viewType.add(R.layout.vh_playlist_reommendation)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagePlaylistBaseVH {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            R.layout.vh_playlist_header -> {
                val binding = VhPlaylistHeaderBinding.inflate(inflater, parent, false)
                PlaylistHeaderVH(binding)
            }
            R.layout.vh_playlist_item -> {
                val binding = VhPlaylistItemBinding.inflate(inflater, parent, false)
                PlaylistItemVH(binding)
            }
            else -> {
                val binding = VhPlaylistReommendationBinding.inflate(inflater, parent, false)
                PlaylistRecommendationVH(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: PagePlaylistBaseVH, position: Int) {
        when (holder) {
            is PlaylistItemVH -> { holder.onBind(downloaded[position]) }
            is PlaylistRecommendationVH -> { holder.onBind() }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (viewType[position]) {
            R.layout.vh_playlist_header -> { R.layout.vh_playlist_header }
            R.layout.vh_playlist_item -> { R.layout.vh_playlist_item }
            else -> { R.layout.vh_playlist_reommendation }
        }
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

class PlaylistRecommendationVH(private val binding: VhPlaylistReommendationBinding)
    : PagePlaylistBaseVH(binding.root) {

    private val arrowToggle = binding.vhPlaylistRecommendationArrow
    private val title = binding.vhPlaylistRecommendationTitle
    private val subTitle = binding.vhPlaylistRecommendationSubtitle
    private val listContainer = binding.vhPlaylistRecommendationLinear
    private val rotate = RotateAnimation(360f, 180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f).apply {
        duration = 250
        fillAfter = true
    }
    private val counterRotate = RotateAnimation(180f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f).apply {
        duration = 250
        fillAfter = true
    }

    fun onBind() {
        arrowToggle.setOnClickListener { toggleRecommended() }
        title.setOnClickListener { toggleRecommended() }
    }

    private fun toggleRecommended() {
        if (listContainer.visibility == View.VISIBLE) {
            arrowToggle.startAnimation(counterRotate)
            val color = Color.parseColor("#A7A7A7")
            title.setTextColor(color)
            subTitle.visibility = View.INVISIBLE
            listContainer.visibility = View.GONE
        } else {
            arrowToggle.startAnimation(rotate)
            val color = Color.parseColor("#FFFFFF")
            title.setTextColor(color)
            subTitle.visibility = View.VISIBLE
            listContainer.visibility = View.VISIBLE
        }
    }
}