package com.yikuni.qcraftcore.core

object QGameManager {
    val games = mutableMapOf<QGame, MutableList<QMap>>()

    @JvmField
    var currentEditingMap: QMap? = null

    @JvmField
    var currentEditingGame: QGame? = null

    fun registerGame(game: QGame){
        games[game] = mutableListOf()
    }
    fun addMap(game: QGame, map: QMap){
        val qMapList = games[game] ?: return
        qMapList.add(map)
    }

    fun getCurrentEditingMap(game: QGame): QMap?{
        if (game != currentEditingGame) return null
        return currentEditingMap
    }

    fun getCurrentEditingGame(): QGame?{
        return currentEditingGame
    }


}