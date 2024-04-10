package com.example.exapleproyect.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.navigation.fragment.findNavController
import com.example.exapleproyect.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var scaleAnimation: ScaleAnimation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageScaleConfig()
        setOnClickListener()


    }

    private fun setOnClickListener(){
        binding.btnEpisodes.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToEpisodesFragment())
        }
    }

    private fun imageScaleConfig(){
        val imageView = binding.background

        scaleAnimation = ScaleAnimation(
            1f, 1.2f,
            1f, 1.2f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        ).apply {
            duration = 1000
            repeatCount = Animation.INFINITE
            repeatMode = Animation.REVERSE
        }

        imageView.startAnimation(scaleAnimation)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.background.clearAnimation()
    }
}

