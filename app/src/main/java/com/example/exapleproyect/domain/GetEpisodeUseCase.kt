package com.example.exapleproyect.domain

import com.example.exapleproyect.data.responses.EpisodeResponse
import com.example.exapleproyect.domain.repositories.InfoRepository
import com.example.exapleproyect.utils.NetResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEpisodeUseCase @Inject constructor(
    private val episodesRepository: InfoRepository
) {

    suspend operator fun invoke(): Flow<NetResult<EpisodeResponse>> = episodesRepository.requestEpisodes()

}