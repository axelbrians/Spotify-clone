package com.machina.spotify_clone.recycler.library

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.machina.spotify_clone.R
import com.machina.spotify_clone.databinding.VhLibraryPlaylistBinding

class LibraryPlaylistVH(
        private val binding: VhLibraryPlaylistBinding,
        private val viewType: Int)
    : BaseLibraryVH(binding.root) {

    private val playlistImg: ImageView = binding.vhLibraryPlaylistImg
    private val playlistIcon: ImageView = binding.vhLibraryPlaylistPlus
    private val btmContainer: LinearLayout = binding.vhLibraryPlaylistBtmContainer
    private val artistName: TextView = binding.vhLibraryPlaylistArtist

    fun onBind(data: Any, position: Int, lastIndex: Int) {
        if (viewType == LibraryMusicAdapter.PLAYLIST) playlistBind(data as Boolean, position)
        else albumBind(data as String, position, lastIndex)
    }

    private fun playlistBind(downloaded: Boolean, position: Int) {
        binding.vhLibraryPlaylistDownloaded.visibility =
                if (!downloaded) View.GONE
                else View.VISIBLE

        binding.vhLibraryPlaylistHeart.visibility = View.GONE

        when (position) {
            1 -> {
                playlistIcon.apply {
                    setImageResource(R.drawable.ic_baseline_add_24)
                    layoutParams.apply { height = 88; width = 88 }
                    visibility = View.VISIBLE
                    requestLayout()
                }
                playlistImg.setImageResource(R.color.grey_background)
                btmContainer.visibility = View.GONE
            }
            2 -> {
                playlistIcon.apply {
                    setImageResource(R.drawable.ic_heart_filled)
                    layoutParams.apply { height = 48; width = 48 }
                    visibility = View.VISIBLE
                    requestLayout()
                }
                playlistImg.setImageResource(R.drawable.bg_playlist_liked)
                artistName.text = "97 songs"
            }
            else -> {
                playlistImg.setImageResource(R.drawable.album_cover_bbibbi)
                playlistIcon.visibility = View.INVISIBLE
                btmContainer.visibility = View.VISIBLE
                artistName.text = "by Axel"
            }
        }
    }

    private fun albumBind(artist: String, position: Int, lastIndex: Int) {
        binding.vhLibraryPlaylistTitle.text = "Album name"
        playlistImg.setImageResource(R.drawable.album_cover_hylt)
        binding.vhLibraryPlaylistArtist.text = artist
        binding.vhLibraryPlaylistDownloaded.visibility = View.GONE

        binding.vhLibraryPlaylistHeart.visibility =
                if (position > lastIndex) View.VISIBLE
                else View.GONE
    }
}