package de.timoj.mythicmobs.customDrops;

import de.timoj.mythicmobs.customDrops.listener.MythicDropsLoadListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class CustomDropsExample extends JavaPlugin {

    private static Logger log;
    private static CustomDropsExample instance;

    @Override
    public void onEnable() {
        log = Bukkit.getLogger();
        instance = this;

        log.info("Example plugin loaded");

        new MythicDropsLoadListener();
    }

    @Override
    public void onDisable() {
        log.info("Example plugin disabled");
    }

    public static Logger getLog() {
        return log;
    }

    public static CustomDropsExample getInstance() {
        return instance;
    }
}
