package com.yikuni.qcraftcore.menu

import com.yikuni.mc.reflect.annotation.YikuniMenu
import com.yikuni.mc.reflect.common.Menu
import com.yikuni.mc.reflect.context.menu.MenuFacade
import com.yikuni.qcraftcore.core.QGameManager
import com.yikuni.qcraftcore.util.sender
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory

@YikuniMenu(value = "创建关卡菜单", size = 9)
class CreateGameMenu: Menu() {
    override fun click(p0: InventoryClickEvent) {
        val qGame = QGameManager.games.keys.find { it.getGameIcon().type == p0.currentItem?.type }?:return
        QGameManager.currentEditingGame = qGame
        val player = p0.whoClicked as Player
        EnterMapNameListener.targetPlayer = player
        player.sender().primary("请在聊天栏输入新建地图的名称")
        player.closeInventory()
    }

    override fun open(p0: Player, p1: Inventory, vararg p2: Any?) {
        for (game in QGameManager.games.keys){
            p1.addItem(game.getGameIcon())
        }
    }
}