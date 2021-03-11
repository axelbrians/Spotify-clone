package com.machina.spotify_clone.recycler.library

import com.machina.spotify_clone.databinding.VhLibraryHeaderBinding
import com.machina.spotify_clone.pager.LibraryMusicPagerFragment

class LibraryHeaderVH(val binding: VhLibraryHeaderBinding, private val fragmentType: Int)
    : BaseLibraryVH(binding.root) {

    private val title = listOf("Recommended artist", "Recommended albums")
    private val subTitle = listOf("Based on the songs and albums you like.", "Based on the songs you like.")

    fun onBind() {
        if (fragmentType == LibraryMusicPagerFragment.FRAGMENT_ARTIST) {
            binding.vhLibraryHeaderTitle.text = title[0]
            binding.vhLibraryHeaderSubtitle.text = subTitle[0]
        } else {
            binding.vhLibraryHeaderTitle.text = title[1]
            binding.vhLibraryHeaderSubtitle.text = subTitle[1]
        }
    }

}