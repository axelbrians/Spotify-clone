package com.machina.spotify_clone.fragment_page

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.machina.spotify_clone.R
import com.machina.spotify_clone.databinding.FragmentArtistBinding
import com.machina.spotify_clone.recycler.artist.PageArtistAdapter
import kotlin.math.abs

class ArtistFragment: Fragment() {

    private var _binding: FragmentArtistBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentArtistBinding.inflate(inflater, container, false)

        initiateUI()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_artistFragment_to_homeFragment)
        }

        return binding.root
    }


    private fun initiateUI() {
        setHasOptionsMenu(true)

        val mActivity = activity as AppCompatActivity
        mActivity.apply {
            setSupportActionBar(binding.fragmentArtistToolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        val scale = resources.displayMetrics.density
        val mLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.fragmentArtistRecycler.apply {
            layoutManager = mLayoutManager
            adapter = PageArtistAdapter(scale)
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // this apply block set up the necessary for Toolbar in a fragment
        binding.fragmentArtistToolbar.apply {
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener { findNavController().navigate(R.id.action_artistFragment_to_homeFragment) }
            setOnMenuItemClickListener{
                when (it.itemId) {
                    R.id.menu_playlist_more -> {
                        true
                    }
                    R.id.menu_playlist_add -> {
                        true
                    }
                    else -> { false }
                }
            }
        }

        /*
        below set of code is responsible to create effect on image header section in fragment
        creating fading, and scaling effect when scrolled up
         */
        var scaleFactor: Float // constant for scaling image in toolbar
        var alphaFactor: Float // constant used for determine alpha of an image in toolbar
        var fontFactor: Float // constant user for changing alpha of a textview in toolbar
        val appBar = binding.fragmentArtistAppbarLayout
        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            alphaFactor = 0.65f - (abs(verticalOffset / appBarLayout.totalScrollRange.toFloat()) * 0.65f)
            scaleFactor = 1 - (abs(verticalOffset/ appBarLayout.totalScrollRange.toFloat()) * 0.09f) + 0.17f

            // get the percentage of scrolling offset
            fontFactor = abs(verticalOffset / appBarLayout.totalScrollRange.toFloat())

            // change font factor to only scaling between 0 to 1.0 after reaching half of its scrollRange
            fontFactor = if (fontFactor < 0.5) { 0f }
            else abs((verticalOffset + (appBarLayout.totalScrollRange.toFloat()/2)) / (appBarLayout.totalScrollRange.toFloat() / 2))


            updateImg(alphaFactor, scaleFactor, fontFactor)
        })
    }

    /**
     * this function called when the header section is scrolled up to update the view
     * and creating some effect within each pixel scrolled
     */
    private fun updateImg(alphaFactor: Float, scaleFactor: Float, fontFactor: Float) {
        binding.fragmentArtistImg.apply {
            scaleX = scaleFactor
            scaleY = scaleFactor
            alpha = alphaFactor
        }
        binding.fragmentArtistToolbarTitle.alpha = fontFactor
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_detail_track, menu)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val TAG = "ArtisFragment"
    }
}