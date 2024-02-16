package com.yikuni.qcraftcore;

import com.yikuni.mc.reflect.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;

public final class QCraftCore extends JavaPlugin {
    public static boolean DEBUG = false;

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginLoader.run(QCraftCore.class);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static QCraftCore getInstance(){
        return JavaPlugin.getPlugin(QCraftCore.class);
    }
}
