package com.example.exapleproyect.ui.episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exapleproyect.data.responses.CharacterResponse
import com.example.exapleproyect.data.responses.Episode
import com.example.exapleproyect.domain.GetCharactersUseCase
import com.example.exapleproyect.domain.GetEpisodeUseCase
import com.example.exapleproyect.utils.NetResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    private val getEpisodesUseCase: GetEpisodeUseCase,
    private val getCharactersUseCase: GetCharactersUseCase
) :ViewModel() {

    private val _episodes = MutableLiveData<List<Episode>>()
    val episodes: LiveData<List<Episode>> get() = _episodes

    private val _characters = MutableLiveData<List<CharacterResponse>>()
    val characters: LiveData<List<CharacterResponse>> get() = _characters


    fun getEpisodesList() {
        viewModelScope.launch {
            getEpisodesUseCase.invoke().collect { result ->
                when (result) {
                    is NetResult.Success -> {
                        _episodes.value = result.data.results
                    }

                    else -> {
                        _episodes.value = emptyList()
                    }
                }
            }

        }
    }

    // Funcion para obtener la informacion de los personajes y almacenarlos en una lista
    // Esto en segundo plano ya que son muchso datos y tarda en regresar el total
    fun fetchCharacterDetails(episodeList: List<Episode>) {
        val charactersIds = HashSet<String>()
        for (episode in episodeList) {
            for (characterUrl in episode.characters) {
                val characterId = characterUrl.substringAfterLast("/")
                charactersIds.add(characterId)
            }
        }

        val characterList = mutableListOf<CharacterResponse>()
        viewModelScope.launch {
            for (characterId in charactersIds) {
                getCharactersUseCase(characterId).collect { result ->
                    when (result) {
                        is NetResult.Success -> {
                            characterList.add(result.data)
                        }
                        is NetResult.Error -> {
                           //Manejar error
                        }
                    }
                }
            }
            _characters.value = characterList
        }
    }

}