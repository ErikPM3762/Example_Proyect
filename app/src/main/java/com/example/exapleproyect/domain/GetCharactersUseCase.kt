package com.example.exapleproyect.domain

import com.example.exapleproyect.data.responses.CharacterResponse
import com.example.exapleproyect.data.responses.EpisodeResponse
import com.example.exapleproyect.domain.repositories.InfoRepository
import com.example.exapleproyect.utils.NetResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: InfoRepository
) {

    suspend operator fun invoke(id: String): Flow<NetResult<CharacterResponse>> = repository.requestCharacters(id)

}