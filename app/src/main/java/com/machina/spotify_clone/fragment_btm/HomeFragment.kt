package com.machina.spotify_clone.fragment_btm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.machina.spotify_clone.R
import com.machina.spotify_clone.databinding.FragmentHomeBinding
import com.machina.spotify_clone.recycler.home.HomeParentAdapter
import com.machina.spotify_clone.recycler.listener.ArtistClickListener
import com.machina.spotify_clone.recycler.listener.PlaylistClickListener

class HomeFragment : Fragment(), PlaylistClickListener, ArtistClickListener {
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
            adapter = HomeParentAdapter(this@HomeFragment,
                    this@HomeFragment)
        }


        return binding.root
    }

    // handle click for opening PlaylistFragment
    override fun onPlaylistClick() {
        findNavController().navigate(R.id.action_homeFragment_to_playlistFragment)
    }

    // handle click for opening ArtistFragment
    override fun onArtistClick() {
        findNavController().navigate(R.id.action_homeFragment_to_artistFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}