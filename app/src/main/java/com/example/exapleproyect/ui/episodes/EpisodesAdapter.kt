package com.example.exapleproyect.ui.episodes

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.exapleproyect.data.responses.Episode
import com.example.exapleproyect.databinding.ItemEpisodeBinding
import java.util.Locale

class EpisodeAdapter(private var episodes: List<Episode>) : RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>(),
    Filterable {

    private var filteredEpisodes: List<Episode> = episodes

    inner class EpisodeViewHolder(private val binding: ItemEpisodeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(episode: Episode) {
            binding.tvEpisodeName.text = episode.name
            binding.tvEpisodeNumber.text = episode.episode
            binding.tvAirDate.text = episode.airDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val binding = ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val currentEpisode = filteredEpisodes[position]
        holder.bind(currentEpisode)
    }

    override fun getItemCount() = filteredEpisodes.size

    fun setData(newEpisodes: List<Episode>) {
        episodes = newEpisodes
        filter.filter("")
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                val filterPattern = constraint.toString().lowercase(Locale.ROOT).trim()
                filteredEpisodes = if (filterPattern.isEmpty()) {
                    episodes
                } else {
                    episodes.filter { episode ->
                        episode.name.lowercase(Locale.ROOT).contains(filterPattern)
                                || episode.episode.toLowerCase().contains(filterPattern)
                    }
                }
                filterResults.values = filteredEpisodes
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                notifyDataSetChanged()
            }
        }
    }
}
