package com.ewide.test.rasyidr.data.local

import com.ewide.test.rasyidr.data.local.room.AppDao
import com.ewide.test.rasyidr.ui.games.GameModel
import com.ewide.test.rasyidr.utilities.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LocalDataSource(
    private val appDao: AppDao
) {

    fun insertFav(game: GameModel) {
        appDao.insertFav(
            gameEntities = DataMapper.mapGameModToEnt(listOf(game)).first()
        )

        appDao.updateIsFav(
            gameID = game.gameID.toString()
        )
    }

    fun getFav(): Flow<List<GameModel>> {
        return flow {
            appDao.getFav().collect {
                emit(DataMapper.mapGameEntToMod(it))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun deleteFav(game: GameModel) {
        appDao.deleteFav(
            gameEntities = DataMapper.mapGameModToEnt(listOf(game)).first()
        )
    }

    fun checkIsFav(gameId: String): Flow<GameModel?> {
        return flow<GameModel?> {
            appDao.checkIsFav(gameId).collect {
                emit(DataMapper.mapGameEntToMod(listOf(it!!)).first())
            }
        }.flowOn(Dispatchers.IO)
    }
}