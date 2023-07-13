package com.ewide.test.rasyidr.utilities

import com.ewide.test.rasyidr.data.local.GameEntities
import com.ewide.test.rasyidr.data.remote.response.GameResponse
import com.ewide.test.rasyidr.ui.games.GameModel


object DataMapper {
    fun mapGameResToMod(input: List<GameResponse>): List<GameModel> {
        return input.map {
            GameModel(
                cheapest = it.cheapest,
                cheapestDealID = it.cheapestDealID,
                external = it.external,
                gameID = it.gameID,
                internalName = it.internalName,
                steamAppID = it.steamAppID,
                thumb = it.thumb,
                isFav = false,
            )
        }
    }

    fun mapGameModToEnt(input: List<GameModel>): List<GameEntities> {
        return input.map {
            GameEntities(
                gameID = it.gameID?.toInt(),
                cheapest = it.cheapest,
                cheapestDealID = it.cheapestDealID,
                external = it.external,
                internalName = it.internalName,
                steamAppID = it.steamAppID,
                thumb = it.thumb,
                isFav = it.isFav,
            )
        }
    }

    fun mapGameEntToMod(input: List<GameEntities>): List<GameModel> {
        return input.map {
            GameModel(
                cheapest = it.cheapest,
                cheapestDealID = it.cheapestDealID,
                external = it.external,
                gameID = it.gameID.toString(),
                internalName = it.internalName,
                steamAppID = it.steamAppID,
                thumb = it.thumb,
                isFav = it.isFav,
            )
        }
    }
}