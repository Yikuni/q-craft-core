package com.yikuni.qcraftcore.menu

import com.yikuni.mc.reflect.annotation.YikuniEvent
import com.yikuni.qcraftcore.QCraftCore
import com.yikuni.qcraftcore.core.QGameManager
import com.yikuni.qcraftcore.util.sender
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

@YikuniEvent
class EnterMapNameListener: Listener {
    companion object{
        var targetPlayer: Player? = null
    }
    @EventHandler
    fun onPlayerEnterMapName(event: AsyncPlayerChatEvent){
        if (event.player.name != targetPlayer?.name) return
        targetPlayer = null
        event.isCancelled = true
        val mapName = event.message

        val qGame = QGameManager.currentEditingGame?:return
        val qMap = qGame.createMap(mapName)
        QGameManager.currentEditingMap = qMap
        QGameManager.addMap(qGame, qMap)

        Bukkit.getScheduler().runTask(QCraftCore.getInstance(), Runnable {
            qGame.openCreateMapMenu(event.player)
        })

    }
}