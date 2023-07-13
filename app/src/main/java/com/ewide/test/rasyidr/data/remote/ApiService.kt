package com.ewide.test.rasyidr.data.remote

import com.ewide.test.rasyidr.data.remote.response.GameResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("games")
    suspend fun getGames(
        @Query("title") title: String
    ): Response<List<GameResponse>>
}