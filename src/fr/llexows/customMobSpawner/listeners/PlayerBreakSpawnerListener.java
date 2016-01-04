package fr.llexows.customMobSpawner.listeners;

import fr.llexows.customMobSpawner.Core;
import fr.llexows.customMobSpawner.Utils;
import fr.llexows.customMobSpawner.managers.ConfigManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Llexows.
 *
 * @version 0.1
 */
public class PlayerBreakSpawnerListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerBreakSpawner(BlockBreakEvent e){
        final Player player = e.getPlayer();
        final Block b = e.getBlock();
        if(b.getType().equals(Material.MOB_SPAWNER)){
            if(player.getItemInHand() != null){
                ItemStack item = player.getItemInHand();
                if(item.getType().equals(Material.GOLD_PICKAXE) && item.getItemMeta().getDisplayName().equals(ConfigManager.getConfig().getString("Global.magic-pickaxe-title").replace('&', '§'))){
                    ItemStack spawner = new ItemStack(Material.MOB_SPAWNER);
                    player.setItemInHand(spawner);
                }else{
                    if(!Core.getInstance().getSpawnerManager().isBypass(player)) {
                        e.setCancelled(true);
                        Utils.sendMessage(player, ConfigManager.getMessage("cannot-break-spawner"));
                    }
                }
            }
        }
    }

}
