package fr.llexows.customMobSpawner.listeners;

import fr.llexows.customMobSpawner.Core;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by Llexows.
 * @version 1.0.1
 */
public class PlayerOpenGUIListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerInteract(PlayerInteractEvent e){
        final Player player = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            Block b = e.getClickedBlock();
            if(b.getType().equals(Material.MOB_SPAWNER)){
                if(player.hasPermission("customspawner.gui")) {
                    Core.getInstance().getSpawnerManager().openSelectionInventory(player, 1);
                    Core.getInstance().getSpawnerManager().getPlayerSpawner().put(player, (CreatureSpawner) b.getState());
                }
            }
        }
    }

}
