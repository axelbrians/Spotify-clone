package com.machina.spotify_clone.recycler.library

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.machina.spotify_clone.R
import com.machina.spotify_clone.databinding.VhLibraryArtistBinding
import com.machina.spotify_clone.databinding.VhLibraryBarBinding
import com.machina.spotify_clone.databinding.VhLibraryPlaylistBinding
import com.machina.spotify_clone.pager.LibraryMusicPagerFragment
import kotlin.random.Random

class LibraryMusicAdapter(private val fragmentType: Int): RecyclerView.Adapter<BaseLibraryVH>() {

    private val count = Random.nextInt(5, 26)
    private val downloaded = mutableListOf<Boolean>()
    private val artistList = mutableListOf<String>()
    private val dummyArtist = listOf("IU", "TAEYEON", "YEJI")

    init {
        repeat(count) { downloaded.add(Random.nextBoolean()) }
        repeat(count) { artistList.add(dummyArtist[Random.nextInt(0, 3)])}
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
            0 -> {
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
            0 -> {
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
            0 -> {
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
            is LibraryPlaylistVH -> { holder.onBind(downloaded[position]) }
        }
    }

    private fun artistOnBind(holder: BaseLibraryVH, position: Int) {
        when (holder) {
            is LibraryArtistVH -> { holder.onBind(artistList[position]) }
        }
    }

    private fun albumOnBind(holder: BaseLibraryVH, position: Int) {
        when (holder) {
            is LibraryPlaylistVH -> { holder.onBind(artistList[position]) }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> 0
            else -> 1
        }
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

    fun onBind(data: Any) {
        if (viewType == LibraryMusicAdapter.PLAYLIST) playlistBind(data as Boolean)
        else albumBind(data as String)
    }

    private fun playlistBind(downloaded: Boolean) {
        if (!downloaded) binding.vhLibraryPlaylistDownloaded.visibility = View.GONE
        else binding.vhLibraryPlaylistDownloaded.visibility = View.VISIBLE
    }

    private fun albumBind(artistName: String) {
        binding.vhLibraryPlaylistTitle.text = "Album name"
        binding.vhLibraryPlaylistArtist.text = artistName
        binding.vhLibraryPlaylistDownloaded.visibility = View.GONE
    }
}


class LibraryArtistVH(private val binding: VhLibraryArtistBinding)
    : BaseLibraryVH(binding.root) {

    fun onBind(artistName: String) {
        binding.vhLibraryArtistName.text = artistName
        when (artistName) {
            "YEJI" -> { binding.vhLibraryArtistImg.setImageResource(R.drawable.yeji_suit) }
            "IU" -> { binding.vhLibraryArtistImg.setImageResource(R.drawable.iu_palette) }
        }
    }
}

