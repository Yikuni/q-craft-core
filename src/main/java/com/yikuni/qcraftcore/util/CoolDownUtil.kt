package com.yikuni.qcraftcore.util

import org.bukkit.entity.Player

object CoolDownUtil {
    private val coolDownMap = HashMap<Player, HashMap<String, Long>>()
    // 毫秒, true: 过了冷却
    fun coolDown(player: Player, name: String, gap: Int): Boolean{
        var playerCoolDown = coolDownMap[player]
        if (playerCoolDown == null){
            playerCoolDown = HashMap()
            coolDownMap[player] = playerCoolDown
        }
        val lastTime = playerCoolDown[name]?:0
        val now = System.currentTimeMillis()
        playerCoolDown[name] = now
        return now - lastTime > gap
    }
}