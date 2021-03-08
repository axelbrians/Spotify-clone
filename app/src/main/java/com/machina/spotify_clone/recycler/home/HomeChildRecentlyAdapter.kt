package com.machina.spotify_clone.recycler.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.machina.spotify_clone.R
import kotlin.random.Random

class HomeChildRecentlyAdapter: RecyclerView.Adapter<HomeBaseSmallVH>() {

    var count = 0
    private val itemType = mutableListOf<Int>()

    init {
        count = Random.nextInt(4, 9)
        repeat(count) {
            val number = Random.nextInt(1, 3)
            if (number == 1) itemType.add(R.layout.vh_square_small)
            else itemType.add(R.layout.vh_circle_small)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBaseSmallVH {
        when (viewType) {
            R.layout.vh_square_small -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(viewType, parent, false)

                return HomeSquareSmallVH(view)
            }
            else -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(viewType, parent, false)

                return HomeCircleSmallVH(view)
            }
        }
    }

    override fun onBindViewHolder(holder: HomeBaseSmallVH, position: Int) {
        when (holder) {
            is HomeSquareSmallVH -> { holder.onBind("Playlist name") }
            is HomeCircleSmallVH -> { holder.onBind("TAEYEON") }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return itemType[position]
    }

    override fun getItemCount(): Int {
        return count
    }
}

class HomeSquareSmallVH(view: View): HomeBaseSmallVH(view) {
    private val textView: TextView = view.findViewById(R.id.vh_square_small_text)

    fun onBind(text: String) {
        textView.text = text
    }
}

class HomeCircleSmallVH(view: View): HomeBaseSmallVH(view) {
    private val textView: TextView = view.findViewById(R.id.vh_circle_small_text)

    fun onBind(text: String) {
        textView.text = text
    }
}