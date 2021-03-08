package com.machina.spotify_clone.recycler.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.machina.spotify_clone.R
import com.machina.spotify_clone.databinding.RecyclerParentGridBinding
import com.machina.spotify_clone.recycler.decoration.EqualSpacingItemDecoration
import kotlin.random.Random

class HomeParentAdapter: RecyclerView.Adapter<HomeParentBaseViewHolder>() {

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
        val itemDecoration = EqualSpacingItemDecoration(24)
        val inflater = LayoutInflater.from(parent.context)
        val context = parent.context
        return when (viewType) {
            R.layout.vh_home_first -> {
                val view = LayoutInflater
                    .from(context)
                    .inflate(viewType, parent, false)
                HomeFirstVH(view)
            }
            R.layout.recycler_parent_grid -> {
                val binding = RecyclerParentGridBinding.inflate(inflater, parent, false)
                val decoration = EqualSpacingItemDecoration(24)
                RecyclerHomeGridVH(binding, decoration)
            }
            R.layout.recycler_parent_recently -> {
                val view = LayoutInflater
                    .from(context)
                    .inflate(viewType, parent, false)
                RecyclerRecentlyVH(view, itemDecoration)
            }
            else -> {
                val view = LayoutInflater
                    .from(context)
                    .inflate(viewType, parent, false)
                RecyclerAlbumVH(view, itemDecoration)
            }
        }
    }

    override fun onBindViewHolder(holder: HomeParentBaseViewHolder, position: Int) {
        when (holder) {
            is HomeFirstVH -> {
                holder.onBind("Good afternoon")
                Log.d(TAG, "onBind Home")
            }
            is RecyclerHomeGridVH -> {
                val layoutManager = GridLayoutManager(
                        holder.rvContext,
                        2,
                        RecyclerView.VERTICAL,
                        false
                )
                holder.onBind(layoutManager, HomeChildGridAdapter(), viewPool)
            }
            is RecyclerRecentlyVH -> {
                val childLayoutManager = LinearLayoutManager(
                    holder.recyclerView.context,
                    RecyclerView.HORIZONTAL,
                    false)
                holder.onBind(childLayoutManager, HomeChildRecentlyAdapter(), viewPool)
            }
            is RecyclerAlbumVH -> {
                val childLayoutManager = LinearLayoutManager(
                    holder.recyclerView.context,
                    RecyclerView.HORIZONTAL,
                    false)
                holder.onBind(childLayoutManager, HomeChildAlbumAdapter(), viewPool)
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

class HomeFirstVH(view: View): HomeParentBaseViewHolder(view) {
    private val greeting: TextView = view.findViewById(R.id.vh_home_greeting)

    fun onBind(text: String) {
        greeting.text = text
    }
}

class RecyclerHomeGridVH(
        private val binding: RecyclerParentGridBinding,
        decoration: EqualSpacingItemDecoration)
    : HomeParentBaseViewHolder(binding.root){

    val rvContext get() = binding.recyclerParentGridRecycler.context

    init {
        binding.recyclerParentGridRecycler.addItemDecoration(decoration)
    }

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

class RecyclerAlbumVH(view: View, decoration: EqualSpacingItemDecoration): HomeParentBaseViewHolder(view) {

    val recyclerView: RecyclerView = view.findViewById(R.id.recycler_parent_recycler_view)
    init {
        recyclerView.addItemDecoration(decoration)
    }

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

class RecyclerRecentlyVH(view: View, decoration: EqualSpacingItemDecoration): HomeParentBaseViewHolder(view) {

    val recyclerView: RecyclerView = view.findViewById(R.id.recycler_parent_recently_recycler)
    init {
        recyclerView.addItemDecoration(decoration)
    }

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

