package com.yikuni.qcraftcore.menu

import com.yikuni.mc.reflect.annotation.YikuniMenu
import com.yikuni.mc.reflect.common.Menu
import com.yikuni.mc.reflect.context.menu.MenuFacade
import com.yikuni.qcraftcore.core.QGameManager
import com.yikuni.qcraftcore.util.sender
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

@YikuniMenu(value = "qcraft总菜单", size = 9)
class MainMenu: Menu() {
    override fun click(p0: InventoryClickEvent) {
        val player = p0.whoClicked as Player
        when(p0.currentItem?.type){
            Material.CLOCK ->{
                MenuFacade.open(player, "创建关卡菜单")
            }
            Material.NETHER_STAR ->{
                val qGame = QGameManager.currentEditingGame
                if (qGame == null){
                    player.sender().warn("当前没有正在编辑的地图")
                    player.closeInventory()
                    return
                }
                qGame.openCreateMapMenu(player)
            }
            Material.MAP ->{
                MenuFacade.open(player,"显示所有游戏菜单")
            }
            else ->{
            }
        }
    }

    override fun open(p0: Player, p1: Inventory, vararg p2: Any?) {
        // 创建游戏菜单
        val createGame = ItemStack(Material.CLOCK)
        val itemMeta = createGame.itemMeta
        itemMeta?.setDisplayName("创建关卡")
        createGame.setItemMeta(itemMeta)

        // 显示所有游戏菜单
        val showGames = ItemStack(Material.MAP)
        val itemMeta1 = showGames.itemMeta
        itemMeta1?.setDisplayName("显示所有游戏")
        showGames.setItemMeta(itemMeta1)

        // 进入当前正在进行的会话
        val enterSession = ItemStack(Material.NETHER_STAR)
        val itemMeta2 = enterSession.itemMeta
        itemMeta2?.setDisplayName("继续编辑地图")
        enterSession.setItemMeta(itemMeta2)

        p1.addItem(createGame)
        p1.addItem(showGames)
        p1.addItem(enterSession)
    }
}