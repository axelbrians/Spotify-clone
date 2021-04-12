package com.machina.spotify_clone.recycler.search

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.machina.spotify_clone.R
import com.machina.spotify_clone.databinding.VhSearchBarBinding
import com.machina.spotify_clone.databinding.RecyclerParentSearchContentBinding
import com.machina.spotify_clone.databinding.VhSearchGreetingBinding
import com.machina.spotify_clone.databinding.VhSearchHeaderBinding
import com.machina.spotify_clone.recycler.decoration.EqualSpacingItemDecoration
import com.machina.spotify_clone.recycler.decoration.StickyHeaderInterface
import com.machina.spotify_clone.recycler.listener.SearchBarClickListener
import kotlin.random.Random

class SearchAdapter(
        private val searchBarClickListener: SearchBarClickListener
): RecyclerView.Adapter<BaseSearchVH>(), StickyHeaderInterface {

    private val itemType = mutableListOf<Int>()

    init {
        itemType.apply {
            add(R.layout.vh_search_greeting)
            add(R.layout.vh_search_bar)
            add(R.layout.vh_search_header)
            add(R.layout.recycler_parent_search_content)
            add(R.layout.vh_search_header)
            add(R.layout.recycler_parent_search_content)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseSearchVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        when (viewType) {
            R.layout.vh_search_greeting -> {
                val binding = VhSearchGreetingBinding.inflate(
                        layoutInflater,
                        parent,
                        false)

                return SearchHeaderVH(binding)
            }
            R.layout.vh_search_bar -> {
                val binding = VhSearchBarBinding.inflate(
                        layoutInflater,
                        parent ,
                        false)

                return SearchBarVH(binding, searchBarClickListener)
            }
            R.layout.vh_search_header -> {
                val binding = VhSearchHeaderBinding.inflate(
                        layoutInflater,
                        parent,
                        false)

                return SearchContentHeader(binding)
            }
            else -> {
                val binding = RecyclerParentSearchContentBinding.inflate(
                        layoutInflater,
                        parent,
                        false)
                val itemDecoration = EqualSpacingItemDecoration(40)

                return SearchRecyclerVH(binding, itemDecoration)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseSearchVH, position: Int) {
        when (holder) {
            is SearchRecyclerVH -> {
                if (position == 3) {
                    val adapter = SearchContentAdapter(4)
                    val childLayoutManager = GridLayoutManager(
                        holder.binding.recyclerParentSearchRecycler.context,
                        2,
                        RecyclerView.VERTICAL,
                        false)
                    holder.onBind(adapter, childLayoutManager)
                }
                if (position > 3) {
                    val adapter = SearchContentAdapter(Random.nextInt(17, 47))

                    val childLayoutManager = GridLayoutManager(
                        holder.binding.recyclerParentSearchRecycler.context,
                        2,
                        RecyclerView.VERTICAL,
                        false)
                    holder.onBind(adapter, childLayoutManager)
                }
            }
            is SearchBarVH -> {
                holder.onBind()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return itemType[position]
    }

    override fun getItemCount(): Int {
        return itemType.size
    }

    /*
    below function is implementation for sticky header
     */
    override fun getHeaderPositionForItem(itemPosition: Int): Int {
        var position = itemPosition
        var headerPosition = 0
        do {
            if (this.isHeader(position)) {
                headerPosition = position
                break
            }
            position -= 1
        } while (position >= 0)
        return headerPosition
    }

    override fun getHeaderLayout(headerPosition: Int): Int {
        return R.layout.vh_search_bar
    }

    override fun bindHeaderData(header: View?, headerPosition: Int) {

    }

    override fun isHeader(itemPosition: Int): Boolean {
        return itemType[itemPosition] == 1
    }
}

class SearchHeaderVH(binding: VhSearchGreetingBinding): BaseSearchVH(binding.root)



class SearchBarVH(
        private val binding: VhSearchBarBinding,
        private val searchBarClickListener: SearchBarClickListener)
    : BaseSearchVH(binding.root) {

    fun onBind() {
        binding.vhSearchBarLinearLayout.setOnClickListener {
            searchBarClickListener.onSearchBarClick()
        }
    }

}


class SearchContentHeader(binding: VhSearchHeaderBinding): BaseSearchVH(binding.root)



class SearchRecyclerVH(
        val binding: RecyclerParentSearchContentBinding,
        itemDecoration: EqualSpacingItemDecoration)
    : BaseSearchVH(binding.root) {

    init { binding.recyclerParentSearchRecycler.addItemDecoration(itemDecoration) }

    fun onBind(
        mAdapter: SearchContentAdapter,
        mLayoutManager: LinearLayoutManager) {
        binding.recyclerParentSearchRecycler.apply {
            adapter = mAdapter
            layoutManager = mLayoutManager
        }
    }
}