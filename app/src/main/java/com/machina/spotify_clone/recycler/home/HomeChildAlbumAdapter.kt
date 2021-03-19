package com.machina.spotify_clone.recycler.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.machina.spotify_clone.R
import com.machina.spotify_clone.databinding.VhCircleBinding
import com.machina.spotify_clone.databinding.VhSquareBinding
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
        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            R.layout.vh_square -> {
                val binding = VhSquareBinding.inflate(inflater, parent, false)
                return SquareVH(binding)
            }
            else -> {
                val binding = VhCircleBinding.inflate(inflater, parent, false)
                return CircleVH(binding)
            }
        }

    }

    override fun onBindViewHolder(holder: HomeBaseVH, position: Int) {
        when (holder) {
            is SquareVH -> { holder.onBind("IU, BLACKPINK, TAEYEON, HEIZE, (G)I_DLE, SinB") }
            is CircleVH -> { holder.onBind("TAEYEON") }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return itemType[position]
    }

    override fun getItemCount(): Int {
        return count
    }

}

class SquareVH(binding: VhSquareBinding): HomeBaseVH(binding.root) {
    private val textView: TextView = binding.vhSquareText

    fun onBind(text: String) {
        textView.text = text
    }
}

class CircleVH(binding: VhCircleBinding): HomeBaseVH(binding.root) {
    private val textView: TextView = binding.vhCircleText

    fun onBind(text: String) {
        textView.text = text
    }
}