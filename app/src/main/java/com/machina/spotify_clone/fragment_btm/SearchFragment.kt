package com.machina.spotify_clone.fragment_btm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.machina.spotify_clone.DetailTrackActivity
import com.machina.spotify_clone.databinding.FragmentSearchBinding
import com.machina.spotify_clone.recycler.decoration.StickyHeaderItemDecoration
import com.machina.spotify_clone.recycler.listener.SearchBarClickListener
import com.machina.spotify_clone.recycler.search.SearchAdapter

class SearchFragment
    : Fragment(), SearchBarClickListener {
    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val mAdapter = SearchAdapter(this)
        val mLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val mStickyHeader = StickyHeaderItemDecoration(mAdapter, this, binding.fragmentSearchRecycler)

        binding.fragmentSearchRecycler.apply {
            adapter = mAdapter
            layoutManager = mLayoutManager
            addItemDecoration(mStickyHeader)
        }
        return binding.root
    }

    // implement searchBar click action
    override fun onSearchBarClick() {
        Log.d(TAG, "Search bar clicked")
        val intent = Intent(context, DetailTrackActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    companion object {
        private const val TAG = "SearchFragment"
    }
}