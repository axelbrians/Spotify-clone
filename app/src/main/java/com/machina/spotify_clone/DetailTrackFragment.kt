package com.machina.spotify_clone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.machina.spotify_clone.databinding.FragmentDetailTrackBinding

class DetailTrackFragment : Fragment() {
    private var _binding: FragmentDetailTrackBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailTrackBinding.inflate(inflater, container, false)
        return binding.root
    }

}