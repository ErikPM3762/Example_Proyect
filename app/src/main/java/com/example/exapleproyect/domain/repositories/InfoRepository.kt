package com.example.exapleproyect.domain.repositories

import com.example.exapleproyect.data.ServiceApi
import com.example.exapleproyect.data.responses.CharacterResponse
import com.example.exapleproyect.data.responses.EpisodeResponse
import com.example.exapleproyect.utils.NetResult
import com.example.exapleproyect.utils.parse
import com.example.exapleproyect.utils.toNetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class InfoRepository  @Inject constructor(
    private val serviceApi : ServiceApi
){

    suspend fun requestEpisodes() : Flow<NetResult<EpisodeResponse>> = flow {
        emit(serviceApi.getEpisodes())

    }.catch { error -> emit(error.toNetworkResult()) }
        .map { res -> res.parse { it } }
        .flowOn(Dispatchers.IO)

    suspend fun requestCharacters(id: String) : Flow<NetResult<CharacterResponse>> = flow {
        emit(serviceApi.getCharacterDetails(id))

    }.catch { error -> emit(error.toNetworkResult()) }
        .map { res -> res.parse { it } }
        .flowOn(Dispatchers.IO)

}