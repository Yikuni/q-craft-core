package com.yikuni.qcraftcore.menu

import com.yikuni.mc.reflect.annotation.YikuniMenu
import com.yikuni.mc.reflect.common.Menu
import com.yikuni.mc.reflect.context.menu.MenuFacade
import com.yikuni.qcraftcore.command.QCraftCommand
import com.yikuni.qcraftcore.core.QGameManager
import com.yikuni.qcraftcore.util.createItem
import com.yikuni.qcraftcore.util.sender
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory

@YikuniMenu(value = "地图操作菜单", size = 9)
class MapOperationMenu: Menu() {

    override fun click(p0: InventoryClickEvent) {
        val player = p0.whoClicked as Player
        when(p0.currentItem?.type){
            Material.LAVA_BUCKET ->{
                val callback =  {
                    QGameManager.games[QGameManager.currentEditingGame]!!.removeIf {
                        it.mapName == ShowMapsMenu.selectedMapName
                    }
                    player.sender().success("成功删除地图")
                }
                MenuFacade.alert(player, callback)
            }
            Material.LEVER ->{
                QGameManager.currentEditingGame = ShowMapsMenu.selectedGame
                QGameManager.currentEditingMap = QGameManager.games[QGameManager.currentEditingGame]!!.find {
                    it.mapName == ShowMapsMenu.selectedMapName
                }
                QGameManager.currentEditingGame!!.openCreateMapMenu(player)
            }
            else ->{

            }
        }
    }

    override fun open(p0: Player, p1: Inventory, vararg p2: Any?) {
        // 删除，编辑，
        p1.setItem(2, createItem("删除", Material.LAVA_BUCKET))
        p1.setItem(6, createItem("编辑", Material.LEVER))
    }



}