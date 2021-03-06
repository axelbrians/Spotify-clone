package com.machina.spotify_clone

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import com.google.android.material.transition.platform.MaterialSharedAxis
import com.machina.spotify_clone.R
import com.machina.spotify_clone.databinding.FragmentDetailTrackBinding

class DetailTrackActivity: AppCompatActivity() {
    private lateinit var binding: FragmentDetailTrackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentDetailTrackBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpListener()
    }

    private fun setUpListener() {
        binding.fragmentDetailTrackSlider.apply {
            addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: Slider) {

                }

                override fun onStopTrackingTouch(slider: Slider) {
                    Log.d(TAG, "current sec of slider ${slider.value}")
                }

            })
            addOnChangeListener { slider, value, fromUser ->
                val currentTime = value.toInt()
                val second = currentTime % 60
                val minute = currentTime / 60
                var text = "$minute:$second"
                if (second < 10) {
                   text = "$minute:0$second"
                }

                binding.fragmentDetailTrackElapsed.text = text
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down)
    }

    companion object {
        private const val TAG = "dummyActivity"
    }
}