package com.machina.spotify_clone.fragment_page

import android.graphics.BitmapFactory
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.palette.graphics.Palette
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

    private val drawableId = R.drawable.yeji_suit

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPlaylistBinding.inflate(inflater, container, false)

        initiateUI()
        initiateRV()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_playlistFragment_to_homeFragment)
        }

        return binding.root
    }

    private fun initiateUI() {
        binding.fragmentPlaylistImg.setImageResource(drawableId)

        // create Bitmap to extract its color later on
        val bitMap = BitmapFactory.decodeResource(context?.resources, drawableId)

        // Palette is used to generate color based on the Bitmap
        // it can generate several color which some option can be null
        // Read more about Palette in official docs
        val paletteBuilder = Palette.Builder(bitMap).maximumColorCount(4)
        var palette = paletteBuilder.generate().getVibrantColor(0)
        if (palette == 0) palette = paletteBuilder.generate().getMutedColor(0)
        if (palette == 0) palette = paletteBuilder.generate().getDominantColor(0)

        // this color Array used to create GradientDrawable effect that later will
        // be applied to image header
        val color = IntArray(2)
        color[0] = palette
        color[1] = 0xFF121212.toInt()
        Log.d(TAG, "palette: $palette")

        // Generate GradientDrawable based on the color that has been extracted
        // from the Bitmap above with Palette
        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            color)
        gradientDrawable.cornerRadius = 0f

        binding.fragmentPlaylistAppbarLayout.background = gradientDrawable
    }

    private fun initiateRV() {
        val mActivity = activity as AppCompatActivity
        mActivity.apply {
            setSupportActionBar(binding.fragmentPlaylistToolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        val mLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        binding.fragmentPlaylistRecycler.apply {
            adapter = PagePlaylistAdapter()
            layoutManager = mLayoutManager
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_playlist, menu)
        Log.d(TAG, "onCreateOptionMenu")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.fragmentPlaylistToolbar.apply {
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener { findNavController().navigate(R.id.action_playlistFragment_to_homeFragment) }
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
                    else -> { false }
                }
            }
        }

        var scaleFactor: Float
        var alphaFactor: Float
        var fontFactor: Float
        val appBar = binding.fragmentPlaylistAppbarLayout
        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                    alphaFactor = 1 - abs(verticalOffset / appBarLayout.totalScrollRange.toFloat())
                    scaleFactor = 1 - (abs(verticalOffset/ appBarLayout.totalScrollRange.toFloat()) * 0.2f)
                    fontFactor = abs(verticalOffset / appBarLayout.totalScrollRange.toFloat())

                    updateToolbar(alphaFactor, scaleFactor, fontFactor)
//                     Log.d(TAG, "scaleFactor: $scaleFactor || alphaFactor: $alphaFactor || fontFactor: $fontFactor")
                })
    }

    private fun updateToolbar(alphaFactor: Float, scaleFactor: Float, fontFactor: Float) {
        binding.fragmentPlaylistImg.apply {
            scaleX = scaleFactor
            scaleY = scaleFactor
            alpha = alphaFactor
        }
        binding.fragmentPlaylistToolbarTitle.alpha = fontFactor
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    companion object {
        private const val TAG = "PlaylistFragment"
    }
}