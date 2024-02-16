package com.yikuni.qcraftcore.sender

import com.yikuni.qcraftcore.QCraftCore
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.ComponentBuilder
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.logging.Logger

class PlayerToPlayerSender(private val from: String, private val to: Player): Sender{
    override fun info(msg: String) {
        primary(msg)
    }

    override fun warn(msg: String) {
        primary(msg)
    }

    override fun error(msg: String) {
        primary(msg)
    }

    override fun success(msg: String) {
        primary(msg)
    }

    override fun primary(msg: String) {
        to.sendMessage("${ChatColor.RED}[ ${ChatColor.WHITE}$from ${ChatColor.RED}-> ${ChatColor.WHITE}${to.name} ${ChatColor.RED}] ${ChatColor.WHITE}$msg")
    }

}
class CommandSenderSender(private val sender: CommandSender): Sender{
    private val log: Logger = QCraftCore.getInstance().logger
    override fun info(msg: String) {
        sender.sendMessage("${ChatColor.GRAY}$msg")
        log.info("Player ${sender.name} received msg: $msg")
    }

    override fun warn(msg: String) {
        sender.sendMessage("${ChatColor.YELLOW}$msg")
        log.info("Player ${sender.name} received msg: $msg")
    }

    override fun error(msg: String) {
        sender.sendMessage("${ChatColor.RED}$msg")
        log.info("Player ${sender.name} received msg: $msg")
    }

    override fun success(msg: String) {
        sender.sendMessage("${ChatColor.GREEN}$msg")
        log.info("Player ${sender.name} received msg: $msg")
    }

    override fun primary(msg: String) {
        sender.sendMessage("${ChatColor.BLUE}$msg")
        log.info("Player ${sender.name} received msg: $msg")
    }
}
/**
 * 玩家消息发送器
 */
class PlayerSender(private val player: Player): Sender {
    private val log: Logger = QCraftCore.getInstance().logger
    override fun info(msg: String) {
        player.sendMessage("${ChatColor.GRAY}$msg")
        log.info("Player ${player.name} received msg: $msg")
    }

    override fun warn(msg: String) {
        player.sendMessage("${ChatColor.YELLOW}$msg")
        log.info("Player ${player.name} received msg: $msg")
    }

    override fun error(msg: String) {
        player.sendMessage("${ChatColor.RED}$msg")
        log.info("Player ${player.name} received msg: $msg")
    }

    override fun success(msg: String) {
        player.sendMessage("${ChatColor.GREEN}$msg")
        log.info("Player ${player.name} received msg: $msg")
    }

    override fun primary(msg: String) {
        player.sendMessage("${ChatColor.BLUE}$msg")
        log.info("Player ${player.name} received msg: $msg")
    }
}



/**
 * 服务器消息发送器
 */
object ServerSender: Sender {
    override fun info(msg: String) {
        Bukkit.broadcastMessage("${ChatColor.DARK_GREEN}[Server] ${ChatColor.GRAY}$msg")
    }

    override fun warn(msg: String) {
        Bukkit.broadcastMessage("${ChatColor.DARK_GREEN}[Server] ${ChatColor.YELLOW}$msg")
    }

    override fun error(msg: String) {
        Bukkit.broadcastMessage("${ChatColor.DARK_GREEN}[Server] ${ChatColor.RED}$msg")
    }

    override fun success(msg: String) {
        Bukkit.broadcastMessage("${ChatColor.DARK_GREEN}[Server] ${ChatColor.GREEN}$msg")
    }

    override fun primary(msg: String) {
        Bukkit.broadcastMessage("${ChatColor.DARK_GREEN}[Server] ${ChatColor.BLUE}$msg")
    }
    fun chat(msg: String){
        Bukkit.broadcastMessage("${ChatColor.DARK_GREEN}[Server] ${ChatColor.WHITE}$msg")
    }
}

object ChatSender{
    fun send(comps: Array<out BaseComponent>){
        Bukkit.getServer().onlinePlayers.forEach {
            it.spigot().sendMessage(*comps)
        }
    }
}

class MultiSender(private val senders: Array<Sender>): Sender {
    override fun info(msg: String) {
        senders.forEach {
            it.info(msg)
        }
    }

    override fun warn(msg: String) {
        senders.forEach {
            it.warn(msg)
        }
    }

    override fun error(msg: String) {
        senders.forEach {
            it.error(msg)
        }
    }

    override fun success(msg: String) {
        senders.forEach {
            it.success(msg)
        }
    }

    override fun primary(msg: String) {
        senders.forEach {
            it.primary(msg)
        }
    }

}

class PlayerActionBarSender(private val player: Player): Sender {
    override fun info(msg: String) {
        send(msg, net.md_5.bungee.api.ChatColor.GRAY)
    }

    override fun warn(msg: String) {
        send(msg, net.md_5.bungee.api.ChatColor.YELLOW)
    }

    override fun error(msg: String) {
        send(msg, net.md_5.bungee.api.ChatColor.RED)
    }

    override fun success(msg: String) {
        send(msg, net.md_5.bungee.api.ChatColor.GREEN)
    }

    override fun primary(msg: String) {
        send(msg, net.md_5.bungee.api.ChatColor.BLUE)
    }

    private fun send(msg: String, color: net.md_5.bungee.api.ChatColor){
        val components = ComponentBuilder().append(msg).color(color).create()
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, *components)
    }


}

object ConsoleSender:Sender{
    override fun info(msg: String) {
        QCraftCore.getInstance().logger.info(msg)
    }

    override fun warn(msg: String) {
        Bukkit.broadcastMessage("${ChatColor.YELLOW}$msg")
        QCraftCore.getInstance().logger.warning(msg)
    }

    override fun error(msg: String) {
        QCraftCore.getInstance().logger.severe(msg)
    }

    override fun success(msg: String) {
        QCraftCore.getInstance().logger.info(msg)
    }

    override fun primary(msg: String) {
        QCraftCore.getInstance().logger.info(msg)
    }
}