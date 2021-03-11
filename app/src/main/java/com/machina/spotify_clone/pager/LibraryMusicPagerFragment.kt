package com.machina.spotify_clone.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.machina.spotify_clone.databinding.PagerFragmentMusicBinding
import com.machina.spotify_clone.recycler.library.LibraryMusicAdapter

class LibraryMusicPagerFragment(private val fragmentType: Int): Fragment() {
    private var _binding: PagerFragmentMusicBinding? = null

    private val binding get() = _binding!!
    private lateinit var mLayoutManager: LinearLayoutManager

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        _binding = PagerFragmentMusicBinding.inflate(inflater, container, false)

        val mAdapter = LibraryMusicAdapter(fragmentType)
        mLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        binding.pagerFragmentMusicRecycler.apply {
            adapter = mAdapter
            layoutManager = mLayoutManager
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mLayoutManager.scrollToPosition(1)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val FRAGMENT_PLAYLIST = 101
        const val FRAGMENT_ARTIST = 102
        const val FRAGMENT_ALBUM = 103
    }
}