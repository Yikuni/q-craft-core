package com.yikuni.qcraftcore.util

import com.yikuni.qcraftcore.QCraftCore
import org.bukkit.Material
import de.tr7zw.nbtapi.NBTItem
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.ItemTag
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.chat.hover.content.Item
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType

val oreArray = arrayOf(
    Material.COAL_ORE,
    Material.DEEPSLATE_COAL_ORE,
    Material.IRON_ORE,
    Material.DEEPSLATE_IRON_ORE,
    Material.COPPER_ORE,
    Material.DEEPSLATE_COPPER_ORE,
    Material.GOLD_ORE,
    Material.DEEPSLATE_GOLD_ORE,
    Material.REDSTONE_ORE,
    Material.DEEPSLATE_REDSTONE_ORE,
    Material.EMERALD_ORE,
    Material.DEEPSLATE_EMERALD_ORE,
    Material.LAPIS_ORE,
    Material.DEEPSLATE_LAPIS_ORE,
    Material.DIAMOND_ORE,
    Material.DEEPSLATE_DIAMOND_ORE,
    Material.NETHER_GOLD_ORE,
    Material.NETHER_QUARTZ_ORE
)

fun getItemText(itemStack: ItemStack): TextComponent {
    val nbtItem = NBTItem(itemStack)
    val item = Item(itemStack.type.key.key, 1, ItemTag.ofNbt(nbtItem.toString()))
    val name: String
    val itemMeta = itemStack.itemMeta
    name = if (itemMeta!!.hasDisplayName()) {
        itemMeta.displayName
    } else {
        itemStack.type.key.key
    }
    return TextComponent(
        *ComponentBuilder().append("[").append(name)
            .event(HoverEvent(HoverEvent.Action.SHOW_ITEM, item)).append("]").create()
    )
}

fun createItem(name: String, material: Material, enchant: Enchantment? = null, level: Int = 0, lore: List<String>? = null, nbtMap: HashMap<NamespacedKey, String>? = null, count: Int = 1): ItemStack{
    val item = ItemStack(material, count)
    val itemMeta = item.itemMeta?: createItemMeta(material)!!
    itemMeta.setDisplayName(name)
    if (lore != null){
        itemMeta.lore = lore
    }
    if (enchant != null){
        itemMeta.addEnchant(enchant, level, true)
    }
    if (nbtMap != null){
        val container = itemMeta.persistentDataContainer
        nbtMap.forEach { (k, v) ->  container.set(k, PersistentDataType.STRING, v)}
    }
    item.itemMeta = itemMeta
    return item
}

fun createItem(name: String, material: Material, enchantMap: Map<Enchantment, Int>, lore: List<String>, nbtMap: Map<NamespacedKey, String>? = null, count: Int = 1): ItemStack{
    val item = ItemStack(material, count)
    val itemMeta = item.itemMeta?: createItemMeta(material)!!
    itemMeta.setDisplayName(name)
    itemMeta.lore = lore
    enchantMap.forEach { (k, v)->
        itemMeta.addEnchant(k, v, true)
    }
    if (nbtMap != null){
        val container = itemMeta.persistentDataContainer
        nbtMap.forEach { (k, v) ->  container.set(k, PersistentDataType.STRING, v)}
    }
    item.itemMeta = itemMeta
    return item
}

fun isOre(type: Material): Boolean{
    return type in arrayOf(
        Material.COAL_ORE,
        Material.DEEPSLATE_COAL_ORE,
        Material.IRON_ORE,
        Material.DEEPSLATE_IRON_ORE,
        Material.COPPER_ORE,
        Material.DEEPSLATE_COPPER_ORE,
        Material.GOLD_ORE,
        Material.DEEPSLATE_GOLD_ORE,
        Material.REDSTONE_ORE,
        Material.DEEPSLATE_REDSTONE_ORE,
        Material.EMERALD_ORE,
        Material.DEEPSLATE_EMERALD_ORE,
        Material.LAPIS_ORE,
        Material.DEEPSLATE_LAPIS_ORE,
        Material.DIAMOND_ORE,
        Material.DEEPSLATE_DIAMOND_ORE,
        Material.NETHER_GOLD_ORE,
        Material.NETHER_QUARTZ_ORE
    )
}

fun createItemMeta(material: Material): ItemMeta?{
    return QCraftCore.getInstance().server.itemFactory.getItemMeta(material)
}