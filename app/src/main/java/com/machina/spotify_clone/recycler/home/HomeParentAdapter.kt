package com.machina.spotify_clone.recycler.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.machina.spotify_clone.R
import com.machina.spotify_clone.recycler.decoration.EqualSpacingItemDecoration
import kotlin.random.Random

class HomeParentAdapter: RecyclerView.Adapter<HomeParentBaseViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()
    private val itemType = mutableListOf<Int>()

    init {
        itemType.add(0)
        itemType.add(2)
        repeat(10) {
            itemType.add(2)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeParentBaseViewHolder {
        val itemDecoration = EqualSpacingItemDecoration(24, RecyclerView.HORIZONTAL)
        when (viewType) {
            0 -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.vh_home_first, parent, false)

                Log.d(TAG, "HomeFirstVH")

                return HomeFirstVH(view)
            }
            1 -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.recycler_parent_recently, parent, false)

                Log.d(TAG, "RecyclerRecentylyVH")

                return RecyclerRecentlyVH(view, itemDecoration)
            }
            else -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.recycler_parent_album, parent, false)

                Log.d(TAG, "RecyclerAlbumVH")

                return RecyclerAlbumVH(view, itemDecoration)
            }
        }
    }

    override fun onBindViewHolder(holder: HomeParentBaseViewHolder, position: Int) {
        when (getItemViewType(position)) {
            0 -> {
                holder as HomeFirstVH
                holder.onBind("Good afternoon")
                Log.d(TAG, "onBind Home")
            }
            1 -> {
                holder as RecyclerRecentlyVH
                val childLayoutManager = LinearLayoutManager(
                        holder.recyclerView.context,
                RecyclerView.HORIZONTAL,
                false)

                holder.onBind(childLayoutManager, HomeChildRecentlyAdapter(), viewPool)
                Log.d(TAG, "onBind Recently")
            }
            2 -> {
                holder as RecyclerAlbumVH
                val childLayoutManager = LinearLayoutManager(
                    holder.recyclerView.context,
                    RecyclerView.HORIZONTAL,
                    false)
                holder.onBind(childLayoutManager, HomeChildAlbumAdapter(), viewPool)
                Log.d(TAG, "onBind Album")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return itemType[position]
    }

    override fun getItemCount(): Int {
        return 12
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

class RecyclerAlbumVH(view: View, decoration: EqualSpacingItemDecoration): HomeParentBaseViewHolder(view) {

    val recyclerView: RecyclerView = view.findViewById(R.id.recycler_parent_recycler_view)
    init {
        recyclerView.addItemDecoration(decoration)
    }

    fun onBind(mLayoutManager: LinearLayoutManager,
               mAdapter: HomeChildAlbumAdapter,
               mViewPool: RecyclerView.RecycledViewPool ) {
        recyclerView.apply {
            layoutManager = mLayoutManager
            adapter = mAdapter
            setRecycledViewPool(mViewPool)
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

