package com.machina.spotify_clone.recycler.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.machina.spotify_clone.R
import kotlin.random.Random

class HomeChildAlbumAdapter: RecyclerView.Adapter<HomeBaseViewHolder>() {

    var count = 0
    private val itemType = mutableListOf<Int>()

    init {
        count = Random.nextInt(5, 12)
        repeat(count) {
            itemType.add(Random.nextInt(1,3))
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBaseViewHolder {
        when (viewType) {
            1 -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.vh_square, parent, false)

                return HomeSquareVH(view)
            }
            else -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.vh_circle, parent, false)

                return HomeCircleVH(view)
            }
        }

    }

    override fun onBindViewHolder(holder: HomeBaseViewHolder, position: Int) {
        when (getItemViewType(position)) {
            1 -> {
                holder as HomeSquareVH
                holder.onBind("IU, BLACKPINK, TAEYEON, HEIZE, (G)I_DLE, SinB")
            }
            2 -> {
                holder as HomeCircleVH
                holder.onBind("TAEYEON")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return itemType[position]
    }

    override fun getItemCount(): Int {
        return count
    }

}

class HomeSquareVH(view: View): HomeBaseViewHolder(view) {
    private val textView: TextView = view.findViewById(R.id.vh_square_text)

    fun onBind(text: String) {
        textView.text = text
    }
}

class HomeCircleVH(view: View): HomeBaseViewHolder(view) {
    private val textView: TextView = view.findViewById(R.id.vh_circle_text)

    fun onBind(text: String) {
        textView.text = text
    }
}