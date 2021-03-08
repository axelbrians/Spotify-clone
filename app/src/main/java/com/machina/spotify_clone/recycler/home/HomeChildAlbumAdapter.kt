package com.machina.spotify_clone.recycler.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.machina.spotify_clone.R
import kotlin.random.Random

class HomeChildAlbumAdapter: RecyclerView.Adapter<HomeBaseVH>() {

    var count = 0
    private val itemType = mutableListOf<Int>()

    init {
        count = 14
        var number: Int
        repeat(count) {
            number = Random.nextInt(1,3)
            if (number == 1) itemType.add(R.layout.vh_square)
            else itemType.add(R.layout.vh_circle)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBaseVH {
        when (viewType) {
            R.layout.vh_square -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(viewType, parent, false)

                return HomeSquareVH(view)
            }
            else -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(viewType, parent, false)

                return HomeCircleVH(view)
            }
        }

    }

    override fun onBindViewHolder(holder: HomeBaseVH, position: Int) {
        when (holder) {
            is HomeSquareVH -> { holder.onBind("IU, BLACKPINK, TAEYEON, HEIZE, (G)I_DLE, SinB") }
            is HomeCircleVH -> { holder.onBind("TAEYEON") }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return itemType[position]
    }

    override fun getItemCount(): Int {
        return count
    }

}

class HomeSquareVH(view: View): HomeBaseVH(view) {
    private val textView: TextView = view.findViewById(R.id.vh_square_text)

    fun onBind(text: String) {
        textView.text = text
    }
}

class HomeCircleVH(view: View): HomeBaseVH(view) {
    private val textView: TextView = view.findViewById(R.id.vh_circle_text)

    fun onBind(text: String) {
        textView.text = text
    }
}