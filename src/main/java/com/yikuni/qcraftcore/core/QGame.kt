package com.yikuni.qcraftcore.core

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

// Only Object Class could implement QGame
interface QGame {

    fun onStart()
    fun onEnd()
    fun getCreateMapMenuName(): String
    fun getGameName(): String
    fun getGameIcon(): ItemStack
    fun createMap(name: String): QMap
    fun openCreateMapMenu(player: Player)
}
