package com.machina.spotify_clone.pager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/*
this class creating Fragment for each TabLayout item
this adapter act like RecyclerView.Adapter but populate fragment for each tab item
 */
class LibraryMusicPagerAdapter(fragment: Fragment)
    : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LibraryMusicPagerFragment(LibraryMusicPagerFragment.FRAGMENT_PLAYLIST)
            1 -> LibraryMusicPagerFragment(LibraryMusicPagerFragment.FRAGMENT_ARTIST)
            else -> LibraryMusicPagerFragment(LibraryMusicPagerFragment.FRAGMENT_ALBUM)
        }
    }

}