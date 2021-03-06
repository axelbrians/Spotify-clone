package com.machina.spotify_clone.bottom_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.machina.spotify_clone.databinding.FragmentHomeBinding
import com.machina.spotify_clone.recycler.decoration.EqualSpacingItemDecoration
import com.machina.spotify_clone.recycler.home.HomeChildAlbumAdapter
import com.machina.spotify_clone.recycler.home.HomeChildRecentlyAdapter
import com.machina.spotify_clone.recycler.home.HomeParentAdapter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val mLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.fragmentHomeRecyclerView.apply {
            layoutManager = mLayoutManager
            adapter = HomeParentAdapter()
        }


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}