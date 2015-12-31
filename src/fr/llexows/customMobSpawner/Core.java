package fr.llexows.customMobSpawner;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

/**
 * Created by Llexows.
 *
 * @version 0.1
 */
public class Core extends JavaPlugin {

    private static Core instance;
    private static SpawnerManager spawnerManager;

    @Override
    public void onEnable() {
        instance = this;
        spawnerManager = new SpawnerManager(this);
        spawnerManager.init();
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getCommand("customspawner").setExecutor(new PluginCommands(this));
    }

    @Override
    public void onDisable() {

    }

    public static Core getInstance(){
        return instance;
    }

    public static SpawnerManager getSpawnerManager(){
        return spawnerManager;
    }
}
