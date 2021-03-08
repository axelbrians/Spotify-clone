package com.machina.spotify_clone.recycler.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.machina.spotify_clone.databinding.VhHomeGridBinding
import kotlin.random.Random

class HomeChildGridAdapter: RecyclerView.Adapter<HomeGridVH>() {

    private val count = Random.nextInt(4, 7)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeGridVH {
        val inflater = LayoutInflater.from(parent.context)

        val binding = VhHomeGridBinding.inflate(inflater, parent, false)
        return HomeGridVH(binding)
    }

    override fun onBindViewHolder(holder: HomeGridVH, position: Int) {

    }

    override fun getItemCount(): Int {
        return count
    }


}

class HomeGridVH(binding: VhHomeGridBinding): RecyclerView.ViewHolder(binding.root)