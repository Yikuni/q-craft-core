package com.yikuni.qcraftcore.menu

import com.yikuni.mc.reflect.annotation.YikuniMenu
import com.yikuni.mc.reflect.common.Menu
import com.yikuni.mc.reflect.context.menu.MenuFacade
import com.yikuni.qcraftcore.core.QGame
import com.yikuni.qcraftcore.core.QGameManager
import com.yikuni.qcraftcore.util.createItem
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory

@YikuniMenu(value = "地图菜单", size = 9)
class ShowMapsMenu: Menu() {
    companion object{
        var selectedGame: QGame? = null
        var selectedMapName: String = ""
    }
    override fun click(p0: InventoryClickEvent) {
        selectedMapName = p0.currentItem?.itemMeta?.displayName?:return
        MenuFacade.open(p0.whoClicked as Player, "地图操作菜单")
    }

    override fun open(p0: Player, p1: Inventory, vararg p2: Any?) {
        if (p2.isEmpty() || p2[0] !is QGame) return
        val qGame = p2[0] as QGame
        selectedGame = qGame
        QGameManager.games[qGame]!!.forEach {
            p1.addItem(createItem(it.mapName, Material.MAP))
        }
    }
}