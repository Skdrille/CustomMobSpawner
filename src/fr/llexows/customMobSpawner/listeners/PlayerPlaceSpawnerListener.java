package fr.llexows.customMobSpawner.listeners;

import fr.llexows.customMobSpawner.Core;
import fr.llexows.customMobSpawner.SpawnerType;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Llexows.
 *
 * @version 0.1
 */
public class PlayerPlaceSpawnerListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerPlaceSpawner(BlockPlaceEvent e){
        final Player player = e.getPlayer();
        final Block b = e.getBlock();
        final ItemStack item = player.getItemInHand();
        if(item.getType().equals(Material.MOB_SPAWNER) && item.getItemMeta().getLore() != null && item.getItemMeta().getLore().get(0).contains("Type :")){
            CreatureSpawner cs = (CreatureSpawner) b.getState();
            String[] datas = item.getItemMeta().getLore().get(0).split(" ");
            String type = datas[2].replace("§6", "");
            for(SpawnerType st : SpawnerType.values()){
                if(st.getTypeName().equalsIgnoreCase(type)){
                    Core.getInstance().getSpawnerManager().setSpawnerType(cs, st);
                }
            }
        }

    }

}
