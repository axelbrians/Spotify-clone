package com.machina.spotify_clone.fragment_page

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.machina.spotify_clone.R
import com.machina.spotify_clone.databinding.FragmentPlaylistBinding
import com.machina.spotify_clone.recycler.decoration.EqualSpacingItemDecoration
import com.machina.spotify_clone.recycler.playlist.PagePlaylistAdapter
import kotlin.math.abs

class PlaylistFragment: Fragment() {

    private var _binding: FragmentPlaylistBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPlaylistBinding.inflate(inflater, container, false)

        val mActivity = activity as AppCompatActivity
        mActivity.apply {
            setSupportActionBar(binding.fragmentPlaylistToolbar)
            supportActionBar?.title = ""
        }
        setHasOptionsMenu(true)

        val mLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val decoration = EqualSpacingItemDecoration(40)

        binding.fragmentPlaylistRecycler.apply {
            adapter = PagePlaylistAdapter()
            layoutManager = mLayoutManager
            addItemDecoration(decoration)
        }



        return binding.root
    }

    private fun updateImage(offset: Float, scaleFactor: Float) {
        binding.fragmentPlaylistImg.apply {
            scaleX = scaleFactor
            scaleY = scaleFactor
            alpha = offset
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_playlist, menu)
        Log.d(TAG, "onCreateOptionMenu")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.fragmentPlaylistToolbar.apply {
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener { view ->
                findNavController().navigate(R.id.action_playlistFragment_to_homeFragment)
            }
            setOnMenuItemClickListener{
                when (it.itemId) {
                    R.id.menu_playlist_more -> {
                        Log.d(TAG, "more clicked")
                        true
                    }
                    R.id.menu_playlist_add -> {
                        Log.d(TAG, "add clicked")
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }

        var scaleFactor = 0.0f
        var alphaFactor = 0.0f
        binding.fragmentPlaylistAppbarLayout
                .addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                    alphaFactor = 1 - abs(verticalOffset / appBarLayout.totalScrollRange.toFloat())
                    scaleFactor = 1 - (abs(verticalOffset/ appBarLayout.totalScrollRange.toFloat()) * 0.2f)

                    updateImage(alphaFactor, scaleFactor)
//                    Log.d(TAG, "percentage: $scaleFactor || ratio: $alphaFactor")
                })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    companion object {
        private const val TAG = "PlaylistFragment"
    }
}