package com.ewide.test.rasyidr.utilities

import com.ewide.test.rasyidr.ui.games.GameModel

interface OnItemClicked {
    fun onFavoriteClick(data: GameModel) {}
}