package com.machina.spotify_clone.recycler.artist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.machina.spotify_clone.R
import com.machina.spotify_clone.databinding.*
import com.machina.spotify_clone.recycler.decoration.EqualSpacingItemDecoration
import kotlin.random.Random

class PageArtistAdapter(private val scale: Float): RecyclerView.Adapter<PageArtistBaseVH>() {

    private val data = createData()
    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageArtistBaseVH {
        val inflater = LayoutInflater.from(parent.context)
        val itemDecoration = EqualSpacingItemDecoration(40)
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
            R.layout.vh_artist_btn -> {
                val binding = VhArtistBtnBinding.inflate(inflater, parent, false)
                ArtistBtnVH(binding)
            }
            R.layout.recycler_parent_artist -> {
                val binding = RecyclerParentArtistBinding.inflate(inflater, parent, false)
                ArtistRecyclerVH(binding, itemDecoration)
            }
            else -> {
                val binding = VhArtistTrackBinding.inflate(inflater, parent, false)
                ArtistTrackVH(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: PageArtistBaseVH, position: Int) {
        when (holder) {
            is ArtistHeaderVH -> { holder.onBind(position, scale, data[position]) }
            is ArtistTrackVH -> { holder.onBind(position) }
            is ArtistPopularVH -> {  }
            is ArtistBtnVH -> { holder.onBind(data[position])}
            is ArtistRecyclerVH -> {
                val adapter = PageArtistChildAdapter()
                val layoutManager = LinearLayoutManager(holder.recyclerView.context, RecyclerView.HORIZONTAL, false)
                holder.onBind(layoutManager, adapter, viewPool)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].id
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun createData(): MutableList<ArtistPageData> {
        val data = mutableListOf<ArtistPageData>()

        data.apply {
            add(ArtistPageData(R.layout.vh_artist_header).apply { title = "Popular" })
            repeat(5) { add(ArtistPageData(R.layout.vh_artist_track)) }
            add(ArtistPageData(R.layout.vh_artist_header).apply { title = "Popular releases" })
            repeat(4) { add(ArtistPageData(R.layout.vh_artist_popular)) }
            add(ArtistPageData(R.layout.vh_artist_btn).apply { title = "SEE DISCOGRAPHY" })
            add(ArtistPageData(R.layout.vh_artist_header).apply { title = "Featuring LeeHi" })
            add(ArtistPageData(R.layout.recycler_parent_artist))
            add(ArtistPageData(R.layout.vh_artist_header).apply { title = "Fans also like" })
            add(ArtistPageData(R.layout.recycler_parent_artist))
            add(ArtistPageData(R.layout.vh_artist_header).apply { title = "Appears on" })
            add(ArtistPageData(R.layout.recycler_parent_artist))
            add(ArtistPageData(R.layout.vh_artist_header).apply { title = "Artist Playlists" })
            add(ArtistPageData(R.layout.recycler_parent_artist))
        }

        return data
    }
}

class ArtistHeaderVH(private val binding: VhArtistHeaderBinding): PageArtistBaseVH(binding.root) {

    fun onBind(position: Int, scale: Float, data: ArtistPageData) {

        // set up the padding of header, the first header to show up is have a larger top padding
        val btm = (7 * scale + 0.5f).toInt()
        val horizontal = (14 * scale + 0.5f).toInt()
        if (position < 13) {
            val top = (48 * scale + 0.5f).toInt()
            binding.vhArtistHeaderContainer.setPadding(horizontal, top, horizontal, btm)
        } else {
            val top = (60 * scale + 0.5f).toInt()
            binding.vhArtistHeaderContainer.setPadding(horizontal, top, horizontal, btm)
        }

        binding.vhArtistHeaderTitle.text = data.title
    }
}

class ArtistTrackVH(private val binding: VhArtistTrackBinding): PageArtistBaseVH(binding.root) {

    fun onBind(position: Int) {
        binding.vhArtistTrackNumber.text = "$position"
    }
}


class ArtistPopularVH(private val binding: VhArtistPopularBinding): PageArtistBaseVH(binding.root)

class ArtistBtnVH(binding: VhArtistBtnBinding): PageArtistBaseVH(binding.root) {
    private val roundBtn = binding.vhArtistBtn

    fun onBind(data: ArtistPageData) {
        roundBtn.text = data.title
    }
}

class ArtistRecyclerVH(
        private val binding: RecyclerParentArtistBinding,
        itemDecoration: EqualSpacingItemDecoration)
    : PageArtistBaseVH(binding.root) {

    val recyclerView = binding.recyclerParentArtistRecycler

    init {
        recyclerView.addItemDecoration(itemDecoration)
    }

    fun onBind(
            mLayoutManager: LinearLayoutManager,
            mAdapter: PageArtistChildAdapter,
            viewPool: RecyclerView.RecycledViewPool) {

        recyclerView.apply {
            adapter = mAdapter
            layoutManager = mLayoutManager
            setRecycledViewPool(viewPool)
        }
    }
}

data class ArtistPageData(val id: Int){
    var title: String ?= ""
    var subTitle: String ?= ""
}