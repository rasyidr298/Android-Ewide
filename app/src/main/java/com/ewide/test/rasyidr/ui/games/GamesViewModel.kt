package com.ewide.test.rasyidr.ui.games

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ewide.test.rasyidr.data.local.LocalDataSource
import com.ewide.test.rasyidr.data.remote.RemoteDataSource
import com.ewide.test.rasyidr.utilities.ApiResponse

class GamesViewModel(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): ViewModel() {

    fun getGames(
        title: String
    ): LiveData<ApiResponse<List<GameModel>>> {
        return remoteDataSource.getGames(
            title = title
        ).asLiveData()
    }

    fun insertFav(
        game: GameModel
    ) {
        localDataSource.insertFav(
            game = game
        )
    }

    fun getFav(
    ): LiveData<List<GameModel>> {
        return localDataSource.getFav()
            .asLiveData()
    }

    fun deleteFav(
        game: GameModel
    ) {
        localDataSource.deleteFav(
            game = game
        )
    }

    fun checkIsFav(
        gameId: String
    ): LiveData<GameModel?> {
        return localDataSource.checkIsFav(
            gameId = gameId
        ).asLiveData()
    }
}