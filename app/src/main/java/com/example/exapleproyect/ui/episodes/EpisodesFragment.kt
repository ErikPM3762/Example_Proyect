package com.example.exapleproyect.ui.episodes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.exapleproyect.R
import com.example.exapleproyect.databinding.FragmentEpisodesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodesFragment : Fragment() {
    private lateinit var binding: FragmentEpisodesBinding
    private val viewModel: DataViewModel by viewModels()
    private lateinit var episodeAdapter: EpisodeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEpisodesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getEpisodesList()
        setupRecyclerView()
        observer()
        setupSearchView()

        Glide.with(this).asGif().load(R.drawable.animation___1712723283193).into(binding.loaderImageview)
    }

    private fun setupRecyclerView() {
        episodeAdapter = EpisodeAdapter(emptyList())
        binding.recyclerViewEpisodes.apply {
            adapter = episodeAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observer() {
        viewModel.episodes.observe(viewLifecycleOwner) {
            binding.loaderImageview.visibility = View.GONE
            episodeAdapter.setData(it)

            /*
            Despues de haber obtenido la lista de episodios podemos iterar los personajes
            viewModel.fetchCharacterDetails(it)
            */
        }

        viewModel.characters.observe(viewLifecycleOwner){
           // Aqui trae la info de los personajes despues de iterar con cada ID
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                episodeAdapter.filter.filter(newText)
                return true
            }
        })
    }

}