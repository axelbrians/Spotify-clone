package com.machina.spotify_clone.bottom_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.machina.spotify_clone.R
import com.machina.spotify_clone.databinding.FragmentSearchBinding
import com.machina.spotify_clone.recycler.decoration.StickyHeaderItemDecoration
import com.machina.spotify_clone.recycler.search.SearchAdapter

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val mAdapter = SearchAdapter()
        val mLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val mStickyHeader = StickyHeaderItemDecoration(mAdapter)

        binding.fragmentSearchRecycler.apply {
            adapter = mAdapter
            layoutManager = mLayoutManager
            addItemDecoration(mStickyHeader)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}