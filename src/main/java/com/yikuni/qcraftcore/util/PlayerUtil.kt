package com.yikuni.qcraftcore.util

import com.yikuni.qcraftcore.sender.PlayerActionBarSender
import com.yikuni.qcraftcore.sender.PlayerSender
import com.yikuni.qcraftcore.sender.Sender
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack


fun onlinePlayerNames():MutableList<String>{
    val names = mutableListOf<String>()
    Bukkit.getOnlinePlayers().forEach {
        names.add(it.name)
    }
    return names
}

fun isPlayerLookingAt(player: Player, entity: Entity, acc: Double = 0.97):Boolean{
    val eye = player.eyeLocation
    val vector = Location(entity.world, entity.location.x, entity.location.y + 1, entity.location.z).toVector().subtract(eye.toVector())
    return vector.normalize().dot(eye.direction) > acc
}

fun playerLookingAtPlayer(player: Player, r: Double): Player?{
    val nearbyEntities = player.getNearbyEntities(r, r, r)
    nearbyEntities.forEach {
        if (it is Player && isPlayerLookingAt(player, it)) return it
    }
    return null
}

fun Player.addItem(item: ItemStack){
    val map = this.inventory.addItem(item)
    if (map.isNotEmpty()){
        map.forEach { (_, u) ->
            this.world.dropItem(this.location, u)
        }
    }
}

fun Player.tp(location: Location){
    teleport(
        Location(
            location.world,
            location.x,
            location.y,
            location.z,
            this.eyeLocation.yaw, this.eyeLocation.pitch
        )
    )
}




fun Player.giveItem(itemStack: ItemStack): Boolean{
    val item = inventory.addItem(itemStack)
    if (item.isNotEmpty()){
        item.forEach { (_, itemStack) ->
            world.dropItem(location, itemStack)
        }
        return false
    }
    return true
}

fun Player.sender(): Sender {
    return PlayerSender(this)
}

fun Player.actionBarSender(): Sender{
    return PlayerActionBarSender(this)
}