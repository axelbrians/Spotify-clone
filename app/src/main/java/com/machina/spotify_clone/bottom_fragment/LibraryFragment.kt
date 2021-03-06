package com.machina.spotify_clone.bottom_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.machina.spotify_clone.R
import com.machina.spotify_clone.databinding.FragmentLibraryBinding

class LibraryFragment : Fragment() {
    private var _binding: FragmentLibraryBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }
}