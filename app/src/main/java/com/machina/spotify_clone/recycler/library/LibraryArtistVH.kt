package com.machina.spotify_clone.recycler.library

import android.view.View
import com.machina.spotify_clone.R
import com.machina.spotify_clone.databinding.VhLibraryArtistBinding
import kotlin.random.Random

class LibraryArtistVH(private val binding: VhLibraryArtistBinding, private val viewType: Int)
    : BaseLibraryVH(binding.root) {

    fun onBind(artistName: String, position: Int, count: Int) {
        binding.vhLibraryArtistTitle.text = artistName
        binding.vhLibraryArtistPlus.visibility = View.INVISIBLE
        when (artistName) {
            "YEJI" -> { binding.vhLibraryArtistImg.setImageResource(R.drawable.yeji_suit) }
            "IU" -> { binding.vhLibraryArtistImg.setImageResource(R.drawable.iu_palette) }
            "TAEYEON" -> { binding.vhLibraryArtistImg.setImageResource(R.drawable.taeyeon_dp)}
        }

        if (position == count) {
            binding.vhLibraryArtistImg.setImageResource(R.color.grey_background)
            binding.vhLibraryArtistPlus.visibility = View.VISIBLE
            binding.vhLibraryArtistTitle.text = "Add artists"
        }

        resolveUI(viewType)
    }

    private fun resolveUI(viewType: Int) {
        if (viewType != LibraryMusicAdapter.ARTIST_RECOMMENDED) {
            binding.vhLibraryArtistSubtitle.visibility = View.GONE
            binding.vhLibraryArtistFollow.visibility = View.GONE
        } else {
            binding.vhLibraryArtistSubtitle.visibility = View.VISIBLE
            binding.vhLibraryArtistFollow.visibility = View.VISIBLE

            val count = Random.nextInt(3, 9)
            val subTitle = "$count songs"
            binding.vhLibraryArtistSubtitle.text = subTitle
        }
    }
}