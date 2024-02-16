package com.yikuni.qcraftcore.command

import com.yikuni.mc.reflect.annotation.YikuniCommand
import com.yikuni.mc.reflect.context.menu.MenuFacade
import com.yikuni.qcraftcore.QCraftCore
import com.yikuni.qcraftcore.core.QGameManager
import com.yikuni.qcraftcore.util.sender
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@YikuniCommand("qcraft")
class QCraftCommand: CommandExecutor {
    override fun onCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        if (p0 !is Player) return false
        if (p3.isEmpty()){
            MenuFacade.open(p0, "qcraft总菜单")
        }else if(p3[0] == "debug"){
            QCraftCore.DEBUG = !QCraftCore.DEBUG
            p0.sender().success("DEBUG设置为${QCraftCore.DEBUG}")
        }
        return true
    }
}