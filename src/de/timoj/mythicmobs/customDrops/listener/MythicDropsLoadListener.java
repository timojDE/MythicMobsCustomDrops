package de.timoj.mythicmobs.customDrops.listener;

import de.timoj.mythicmobs.customDrops.CustomDropsExample;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitItemStack;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicDropLoadEvent;
import io.lumine.xikage.mythicmobs.drops.Drop;
import io.lumine.xikage.mythicmobs.drops.DropMetadata;
import io.lumine.xikage.mythicmobs.drops.IMultiDrop;
import io.lumine.xikage.mythicmobs.drops.LootBag;
import io.lumine.xikage.mythicmobs.drops.droppables.ItemDrop;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class MythicDropsLoadListener implements Listener {

    public MythicDropsLoadListener() {
        Bukkit.getPluginManager().registerEvents(this, CustomDropsExample.getInstance());
        CustomDropsExample.getLog().info("MythicDropLoadEvent registred");
    }

    @EventHandler
    public void onMythicDropLoad(MythicDropLoadEvent event) {
        if(event.getDropName().equalsIgnoreCase("exampleDropPlugin")) {
            Drop drop = new ItemsDrop(event.getConfig());
            event.register(drop);
        }
    }

    public static class ItemsDrop extends Drop implements IMultiDrop {

        private ItemStack item;

        public ItemsDrop(MythicLineConfig config) {
            super(config.getLine(), config);

            String type = config.getString(new String[] {"type", "t"}, dropVar);
            this.item = new ItemStack(Material.valueOf(type));
        }

        @Override
        public LootBag get(DropMetadata metadata) {
            LootBag loot = new LootBag(metadata);
            Drop drop = new ItemDrop(getLine(), (MythicLineConfig) getConfig(), new BukkitItemStack(item));
            loot.getLootTable().add(drop);

            return loot;
        }

    }

}
