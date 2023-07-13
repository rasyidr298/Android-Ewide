package com.ewide.test.rasyidr.data.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("cheapest") val cheapest: String?,
    @SerializedName("cheapestDealID") val cheapestDealID: String?,
    @SerializedName("external") val `external`: String?,
    @SerializedName("gameID") val gameID: String?,
    @SerializedName("internalName") val internalName: String?,
    @SerializedName("steamAppID") val steamAppID: String?,
    @SerializedName("thumb") val thumb: String?
)