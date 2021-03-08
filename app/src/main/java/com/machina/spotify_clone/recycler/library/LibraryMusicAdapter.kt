package com.machina.spotify_clone.recycler.library

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.machina.spotify_clone.R
import com.machina.spotify_clone.databinding.VhLibraryArtistBinding
import com.machina.spotify_clone.databinding.VhLibraryBarBinding
import com.machina.spotify_clone.databinding.VhLibraryPlaylistBinding
import com.machina.spotify_clone.pager.LibraryMusicPagerFragment
import org.w3c.dom.Text
import kotlin.random.Random

class LibraryMusicAdapter(private val fragmentType: Int): RecyclerView.Adapter<BaseLibraryVH>() {

    private val count = Random.nextInt(5, 26)
    private val downloaded = mutableListOf<Boolean>()
    private val artistList = mutableListOf<String>()
    private val typeList = mutableListOf<Int>()
    private val dummyArtist = listOf("IU", "TAEYEON", "YEJI")


    init {
        typeList.add(R.layout.vh_library_bar)
        repeat(count) { downloaded.add(Random.nextBoolean()) }
        repeat(count) { artistList.add(dummyArtist[Random.nextInt(0, 3)])}
        repeat(count - 1) {
            if (fragmentType == LibraryMusicPagerFragment.FRAGMENT_ARTIST) typeList.add(R.layout.vh_library_artist)
            else typeList.add(R.layout.vh_library_playlist)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseLibraryVH {
        val inflater = LayoutInflater.from(parent.context)

        return when (fragmentType) {
            LibraryMusicPagerFragment.FRAGMENT_PLAYLIST -> {
                getPlaylistVH(viewType, inflater, parent)
            }
            LibraryMusicPagerFragment.FRAGMENT_ARTIST -> {
                getArtistVH(viewType, inflater, parent)
            }
            else -> {
                getAlbumVH(viewType, inflater, parent)
            }
        }
    }

    private fun getPlaylistVH(viewType: Int, inflater: LayoutInflater, parent: ViewGroup): BaseLibraryVH {
        return when (viewType) {
            R.layout.vh_library_bar -> {
                val binding = VhLibraryBarBinding.inflate(inflater, parent, false)
                LibraryBarVH(binding)
            }
            else -> {
                val binding = VhLibraryPlaylistBinding.inflate(inflater, parent, false)
                LibraryPlaylistVH(binding, PLAYLIST)
            }
        }
    }

    private fun getArtistVH(viewType: Int, inflater: LayoutInflater, parent: ViewGroup): BaseLibraryVH {
        return when (viewType) {
            R.layout.vh_library_bar -> {
                val binding = VhLibraryBarBinding.inflate(inflater, parent, false)
                LibraryBarVH(binding)
            }
            else -> {
                val binding = VhLibraryArtistBinding.inflate(inflater, parent, false)
                LibraryArtistVH(binding)
            }
        }
    }

    private fun getAlbumVH(viewType: Int, inflater: LayoutInflater, parent: ViewGroup): BaseLibraryVH {
        return when (viewType) {
            R.layout.vh_library_bar -> {
                val binding = VhLibraryBarBinding.inflate(inflater, parent, false)
                LibraryBarVH(binding)
            }
            else -> {
                val binding = VhLibraryPlaylistBinding.inflate(inflater, parent, false)
                LibraryPlaylistVH(binding, ALBUM)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseLibraryVH, position: Int) {
        when (fragmentType) {
            LibraryMusicPagerFragment.FRAGMENT_PLAYLIST -> {
                playlistOnBind(holder, position)
            }
            LibraryMusicPagerFragment.FRAGMENT_ARTIST -> {
                artistOnBind(holder, position)
            }
            else -> {
                albumOnBind(holder, position)
            }
        }
    }

    private fun playlistOnBind(holder: BaseLibraryVH, position: Int) {
        when (holder) {
            is LibraryPlaylistVH -> { holder.onBind(downloaded[position], position) }
        }
    }

    private fun artistOnBind(holder: BaseLibraryVH, position: Int) {
        when (holder) {
            is LibraryArtistVH -> { holder.onBind(artistList[position], position, count) }
        }
    }

    private fun albumOnBind(holder: BaseLibraryVH, position: Int) {
        when (holder) {
            is LibraryPlaylistVH -> { holder.onBind(artistList[position], position) }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return typeList[position]
    }

    override fun getItemCount(): Int {
        return count
    }

    companion object {
        const val PLAYLIST = 1
        const val ALBUM = 2
    }
}


class LibraryBarVH(binding: VhLibraryBarBinding)
    : BaseLibraryVH(binding.root)


class LibraryPlaylistVH(
        private val binding: VhLibraryPlaylistBinding,
        private val viewType: Int)
    : BaseLibraryVH(binding.root) {

    private val playlistImg: ImageView = binding.vhLibraryPlaylistImg
    private val playlistIcon: ImageView = binding.vhLibraryPlaylistPlus
    private val btmContainer: LinearLayout = binding.vhLibraryPlaylistBtmContainer
    private val artistName: TextView = binding.vhLibraryPlaylistArtist

    fun onBind(data: Any, position: Int) {
        if (viewType == LibraryMusicAdapter.PLAYLIST) playlistBind(data as Boolean, position)
        else albumBind(data as String)
    }

    private fun playlistBind(downloaded: Boolean, position: Int) {
        if (!downloaded) binding.vhLibraryPlaylistDownloaded.visibility = View.GONE
        else binding.vhLibraryPlaylistDownloaded.visibility = View.VISIBLE

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

    private fun albumBind(artist: String) {
        binding.vhLibraryPlaylistTitle.text = "Album name"
        playlistImg.setImageResource(R.drawable.album_cover_hylt)
        binding.vhLibraryPlaylistArtist.text = artist
        binding.vhLibraryPlaylistDownloaded.visibility = View.GONE
    }
}


class LibraryArtistVH(private val binding: VhLibraryArtistBinding)
    : BaseLibraryVH(binding.root) {

    fun onBind(artistName: String, position: Int, count: Int) {
        binding.vhLibraryArtistName.text = artistName
        binding.vhLibraryArtistPlus.visibility = View.INVISIBLE
        when (artistName) {
            "YEJI" -> { binding.vhLibraryArtistImg.setImageResource(R.drawable.yeji_suit) }
            "IU" -> { binding.vhLibraryArtistImg.setImageResource(R.drawable.iu_palette) }
            "TAEYEON" -> { binding.vhLibraryArtistImg.setImageResource(R.drawable.taeyeon_dp)}
        }
        if (position == count - 1) {
            binding.vhLibraryArtistImg.setImageResource(R.color.grey_background)
            binding.vhLibraryArtistPlus.visibility = View.VISIBLE
            binding.vhLibraryArtistName.text = "Add artists"
        }
    }
}

