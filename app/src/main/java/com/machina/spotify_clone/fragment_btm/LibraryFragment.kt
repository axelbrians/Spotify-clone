package com.machina.spotify_clone.fragment_btm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.machina.spotify_clone.databinding.FragmentLibraryBinding
import com.machina.spotify_clone.pager.LibraryMusicPagerAdapter

class LibraryFragment : Fragment() {
    private var _binding: FragmentLibraryBinding? = null
    private val binding get() = _binding!!

    // list of string to be displayed in tab layout
    private val listTitle = listOf("Playlists", "Artist", "Albums")

    private lateinit var pagerAdapter: LibraryMusicPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // creating adapter for TabLayout and attach it to ViewPager
        pagerAdapter = LibraryMusicPagerAdapter(this)
        binding.fragmentLibraryViewPager.adapter = pagerAdapter

        // attach TabLayout and ViewPager, this function need to be called after
        // TabLayout adapter above is attached
        TabLayoutMediator(
                binding.fragmentLibraryTablayout,
                binding.fragmentLibraryViewPager
        ) { tab, position ->
            tab.text = listTitle[position]
        }.attach()
    }




    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}