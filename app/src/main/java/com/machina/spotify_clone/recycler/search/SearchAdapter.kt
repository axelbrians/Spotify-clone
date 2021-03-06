package com.machina.spotify_clone.recycler.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.machina.spotify_clone.R
import com.machina.spotify_clone.recycler.decoration.EqualSpacingItemDecoration
import kotlin.random.Random

class SearchAdapter: RecyclerView.Adapter<BaseSearchVH>() {

    private val itemType = mutableListOf(0, 1, 2, 3, 2, 3)
    private val count = 6


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseSearchVH {
        when (viewType) {
            0 -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.vh_search_greeting, parent, false)

                return SearchHeaderVH(view)
            }
            1 -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.vh_search_bar, parent, false)

                return SearchBarVH(view)
            }
            2 -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.vh_search_header, parent, false)

                return SearchContentHeader(view)
            }
            else -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.recycler_parent_search_content, parent, false)
                val itemDecoration = EqualSpacingItemDecoration(40)

                return SearchRecyclerVH(view, itemDecoration)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseSearchVH, position: Int) {
        when (holder) {
            is SearchRecyclerVH -> {
                if (position == 3) {
                    val adapter = SearchContentAdapter(4)
                    val childLayoutManager = GridLayoutManager(
                        holder.recyclerView.context,
                        2,
                        RecyclerView.VERTICAL,
                        false
                    )
                    holder.onBind(adapter, childLayoutManager)
                }
                if (position > 3) {
                    val adapter = SearchContentAdapter(Random.nextInt(7, 12))

                    val childLayoutManager = GridLayoutManager(
                        holder.recyclerView.context,
                        2,
                        RecyclerView.VERTICAL,
                        false
                    )
                    holder.onBind(adapter, childLayoutManager)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> 0
            1 -> 1
            2 -> 2
            3 -> 3
            4 -> 2
            else -> 3
        }
    }

    override fun getItemCount(): Int {
        return count
    }
}

class SearchHeaderVH(view: View): BaseSearchVH(view)

class SearchBarVH(view: View): BaseSearchVH(view)

class SearchContentHeader(view: View): BaseSearchVH(view)

class SearchRecyclerVH(view: View, itemDecoration: EqualSpacingItemDecoration): BaseSearchVH(view) {
    val recyclerView: RecyclerView = view.findViewById(R.id.recycler_parent_search_recycler)

    init {
        recyclerView.addItemDecoration(itemDecoration)
    }

    fun onBind(
        mAdapter: SearchContentAdapter,
        mLayoutManager: LinearLayoutManager) {
        recyclerView.apply {
            adapter = mAdapter
            layoutManager = mLayoutManager
        }
    }
}