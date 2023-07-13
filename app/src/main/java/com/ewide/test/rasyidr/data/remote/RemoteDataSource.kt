package com.ewide.test.rasyidr.data.remote

import com.ewide.test.rasyidr.ui.games.GameModel
import com.ewide.test.rasyidr.utilities.ApiResponse
import com.ewide.test.rasyidr.utilities.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

class RemoteDataSource(
    private val apiService: ApiService
) {
    fun getGames(title: String): Flow<ApiResponse<List<GameModel>>> {
        return flow {
            try {
                emit(ApiResponse.Loading)
                val response = apiService.getGames(
                    title = title
                )
                if (response.isSuccessful) {
                    emit(ApiResponse.Success(
                        data = DataMapper.mapGameResToMod(
                            input = response.body()!!
                        ))
                    )
                }else {
                    emit(ApiResponse.Error(
                        errorMessage = response.message())
                    )
                }
            } catch (e: IOException) {
                emit(ApiResponse.Error(
                    errorMessage = e.message.toString()
                ))
            }
        }.flowOn(Dispatchers.IO)
    }
}