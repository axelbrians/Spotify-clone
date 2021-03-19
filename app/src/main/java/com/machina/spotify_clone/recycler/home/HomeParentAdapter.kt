package com.machina.spotify_clone.recycler.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.machina.spotify_clone.R
import com.machina.spotify_clone.databinding.RecyclerParentAlbumBinding
import com.machina.spotify_clone.databinding.RecyclerParentGridBinding
import com.machina.spotify_clone.databinding.RecyclerParentRecentlyBinding
import com.machina.spotify_clone.databinding.VhHomeFirstBinding
import com.machina.spotify_clone.recycler.decoration.EqualSpacingItemDecoration
import com.machina.spotify_clone.recycler.listener.ArtistClickListener
import com.machina.spotify_clone.recycler.listener.PlaylistClickListener

class HomeParentAdapter(
        private val playlistClickListener: PlaylistClickListener,
        private val artistClickListener: ArtistClickListener)
    : RecyclerView.Adapter<HomeParentBaseViewHolder>() {

    /*
    viewType here is hardcoded in init block,
    viewType represent the viewType for each of the items in this recycler
     */
    private val viewPool = RecyclerView.RecycledViewPool()
    private val itemType = mutableListOf<Int>()

    init {
        itemType.add(R.layout.vh_home_first)
        itemType.add(R.layout.recycler_parent_grid)
        itemType.add(R.layout.recycler_parent_recently)
        repeat(10) {
            itemType.add(R.layout.recycler_parent_album)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeParentBaseViewHolder {
        val itemDecoration = EqualSpacingItemDecoration(40)
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            R.layout.vh_home_first -> {
                val binding = VhHomeFirstBinding.inflate(inflater, parent, false)
                HomeFirstVH(binding)
            }
            R.layout.recycler_parent_grid -> {
                val binding = RecyclerParentGridBinding.inflate(inflater, parent, false)
                val decoration = EqualSpacingItemDecoration(24)
                RecyclerParentGrid(binding, decoration)
            }
            R.layout.recycler_parent_recently -> {
                val binding = RecyclerParentRecentlyBinding.inflate(inflater, parent, false)
                RecyclerParentRecently(binding, itemDecoration)
            }
            else -> {
                val binding = RecyclerParentAlbumBinding.inflate(inflater, parent, false)
                RecyclerParentAlbum(binding, itemDecoration)
            }
        }
    }

    override fun onBindViewHolder(holder: HomeParentBaseViewHolder, position: Int) {
        when (holder) {
            is HomeFirstVH -> {
                holder.onBind("Good afternoon")
                Log.d(TAG, "onBind Home")
            }
            is RecyclerParentGrid -> {
                val layoutManager = GridLayoutManager(
                        holder.recyclerView.context,
                        2,
                        RecyclerView.VERTICAL,
                        false)
                holder.onBind(layoutManager, HomeChildGridAdapter(playlistClickListener), viewPool)
            }
            is RecyclerParentRecently -> {
                val layoutManager = LinearLayoutManager(
                    holder.recyclerView.context,
                    RecyclerView.HORIZONTAL,
                    false)
                holder.onBind(layoutManager, HomeChildRecentlyAdapter(artistClickListener), viewPool)
            }
            is RecyclerParentAlbum -> {
                val layoutManager = LinearLayoutManager(
                    holder.recyclerView.context,
                    RecyclerView.HORIZONTAL,
                    false)
                holder.onBind(layoutManager, HomeChildAlbumAdapter(), viewPool)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (itemType[position]) {
            R.layout.vh_home_first -> { R.layout.vh_home_first }
            R.layout.recycler_parent_grid -> { R.layout.recycler_parent_grid }
            R.layout.recycler_parent_recently -> { R.layout.recycler_parent_recently }
            else -> { R.layout.recycler_parent_album }
        }
    }

    override fun getItemCount(): Int {
        return 13
    }

    companion object {
        private const val TAG = "HomeParentAdapter"
    }
}

class HomeFirstVH(binding: VhHomeFirstBinding): HomeParentBaseViewHolder(binding.root) {
    private val greeting: TextView = binding.vhHomeGreeting

    fun onBind(text: String) {
        greeting.text = text
    }
}

class RecyclerParentGrid(
        private val binding: RecyclerParentGridBinding,
        decoration: EqualSpacingItemDecoration)
    : HomeParentBaseViewHolder(binding.root){

    val recyclerView = binding.recyclerParentGridRecycler

    init { binding.recyclerParentGridRecycler.addItemDecoration(decoration) }

    fun onBind(mLayoutManager: GridLayoutManager,
               mAdapter: HomeChildGridAdapter,
               mViewPool: RecyclerView.RecycledViewPool) {
        binding.recyclerParentGridRecycler.apply {
            adapter = mAdapter
            layoutManager = mLayoutManager
            setRecycledViewPool(mViewPool)
        }
    }

}

class RecyclerParentAlbum(
        binding: RecyclerParentAlbumBinding,
        decoration: EqualSpacingItemDecoration): HomeParentBaseViewHolder(binding.root) {

    val recyclerView: RecyclerView = binding.recyclerParentRecyclerView
    init { recyclerView.addItemDecoration(decoration) }

    fun onBind(mLayoutManager: LinearLayoutManager,
               mAdapter: HomeChildAlbumAdapter,
               viewPool: RecyclerView.RecycledViewPool ) {
        recyclerView.apply {
            layoutManager = mLayoutManager
            adapter = mAdapter
            setRecycledViewPool(viewPool)
        }
    }
}

class RecyclerParentRecently(
        binding: RecyclerParentRecentlyBinding,
        decoration: EqualSpacingItemDecoration): HomeParentBaseViewHolder(binding.root) {

    val recyclerView: RecyclerView = binding.recyclerParentRecentlyRecycler
    init { recyclerView.addItemDecoration(decoration) }

    fun onBind(mLayoutManager: LinearLayoutManager,
               mAdapter: HomeChildRecentlyAdapter,
               mViewPool: RecyclerView.RecycledViewPool ) {
        recyclerView.apply {
            layoutManager = mLayoutManager
            adapter = mAdapter
            setRecycledViewPool(mViewPool)
        }
    }
}

