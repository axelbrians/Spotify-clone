package com.machina.spotify_clone.recycler.library

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.machina.spotify_clone.R
import com.machina.spotify_clone.databinding.VhLibraryArtistBinding
import com.machina.spotify_clone.databinding.VhLibraryBarBinding
import com.machina.spotify_clone.databinding.VhLibraryHeaderBinding
import com.machina.spotify_clone.databinding.VhLibraryPlaylistBinding
import com.machina.spotify_clone.pager.LibraryMusicPagerFragment
import kotlin.random.Random

    /**  This Adapter Class is used in 3 pages of TabLayout
    *   @param fragmentType is used to determine which page this Adapter is attached
    */
class LibraryMusicAdapter(private val fragmentType: Int): RecyclerView.Adapter<BaseLibraryVH>() {

    private var count = Random.nextInt(5, 15)
    private val downloaded = mutableListOf<Boolean>()
    private val artistList = mutableListOf<String>()
    private val typeList = mutableListOf<Int>()
    private val artistsName = listOf("IU", "TAEYEON", "YEJI")
    private var lastIndex = 0


    init {
        typeList.add(R.layout.vh_library_bar)
        repeat(count - 1) {
            if (fragmentType == LibraryMusicPagerFragment.FRAGMENT_ARTIST) typeList.add(R.layout.vh_library_artist)
            else typeList.add(R.layout.vh_library_playlist)
            lastIndex = count - 1
        }

        if (fragmentType == LibraryMusicPagerFragment.FRAGMENT_ARTIST
                || fragmentType == LibraryMusicPagerFragment.FRAGMENT_ALBUM){

            val increase = Random.nextInt(5, 15)
            count += increase
            typeList.add(R.layout.vh_playlist_header)
            repeat(count - 1) {
                if (fragmentType == LibraryMusicPagerFragment.FRAGMENT_ARTIST) typeList.add(ARTIST_RECOMMENDED)
                else typeList.add(ALBUM_RECOMMENDED)
            }
        }

        repeat(count) {
            downloaded.add(Random.nextBoolean())
            artistList.add(artistsName[Random.nextInt(0, 3)])
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
            R.layout.vh_playlist_header -> {
                val binding = VhLibraryHeaderBinding.inflate(inflater, parent, false)
                LibraryHeaderVH(binding, fragmentType)
            }
            ARTIST_RECOMMENDED -> {
                val binding = VhLibraryArtistBinding.inflate(inflater, parent, false)
                LibraryArtistVH(binding, ARTIST_RECOMMENDED)
            }
            else -> {
                val binding = VhLibraryArtistBinding.inflate(inflater, parent, false)
                LibraryArtistVH(binding, 0)
            }
        }
    }

    private fun getAlbumVH(viewType: Int, inflater: LayoutInflater, parent: ViewGroup): BaseLibraryVH {
        return when (viewType) {
            R.layout.vh_library_bar -> {
                val binding = VhLibraryBarBinding.inflate(inflater, parent, false)
                LibraryBarVH(binding)
            }
            R.layout.vh_playlist_header -> {
                val binding = VhLibraryHeaderBinding.inflate(inflater, parent, false)
                LibraryHeaderVH(binding, fragmentType)
            }
            ALBUM_RECOMMENDED -> {
                val binding = VhLibraryPlaylistBinding.inflate(inflater, parent, false)
                LibraryPlaylistVH(binding, ALBUM_RECOMMENDED)
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
            is LibraryPlaylistVH -> { holder.onBind(downloaded[position], position, lastIndex) }
        }
    }

    private fun artistOnBind(holder: BaseLibraryVH, position: Int) {
        when (holder) {
            is LibraryArtistVH -> { holder.onBind(artistList[position], position, lastIndex) }
            is LibraryHeaderVH -> { holder.onBind() }
        }
    }

    private fun albumOnBind(holder: BaseLibraryVH, position: Int) {
        when (holder) {
            is LibraryPlaylistVH -> { holder.onBind(artistList[position], position, lastIndex) }
            is LibraryHeaderVH -> { holder.onBind() }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return typeList[position]
    }

    override fun getItemCount(): Int {
        return count
    }

    companion object {
        const val PLAYLIST = 11
        const val ALBUM = 12
        const val ALBUM_RECOMMENDED = 13
        const val ARTIST_RECOMMENDED = 14
        const val TAG = "LibraryMusicAdapter"
    }
}
