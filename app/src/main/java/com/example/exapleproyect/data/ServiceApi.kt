package com.example.exapleproyect.data

import com.example.exapleproyect.data.responses.CharacterResponse
import com.example.exapleproyect.data.responses.EpisodeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceApi {

    @GET("episode")
    suspend fun getEpisodes(): Response<EpisodeResponse>

    @GET("character/{characterId}")
    suspend fun getCharacterDetails(@Path("characterId") characterId: String): Response<CharacterResponse>
}