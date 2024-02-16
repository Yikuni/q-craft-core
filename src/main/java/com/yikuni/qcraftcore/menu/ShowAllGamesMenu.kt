package com.yikuni.qcraftcore.menu

import com.yikuni.mc.reflect.annotation.YikuniMenu
import com.yikuni.mc.reflect.common.Menu
import com.yikuni.mc.reflect.context.menu.MenuFacade
import com.yikuni.qcraftcore.core.QGameManager
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory

@YikuniMenu(value = "显示所有游戏菜单", size = 9)
class ShowAllGamesMenu: Menu() {
    override fun click(p0: InventoryClickEvent) {
        val displayName = p0.currentItem?.itemMeta?.displayName ?: return
        val qGame = QGameManager.games.keys.find { it.getGameName() == displayName } ?: return
        MenuFacade.open(p0.whoClicked as Player, "地图菜单", qGame)
    }

    override fun open(p0: Player, p1: Inventory, vararg p2: Any?) {
        QGameManager.games.keys.forEach {
            p1.addItem(it.getGameIcon())
        }
    }
}