package fr.llexows.customMobSpawner;

import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * Created by Llexows.
 *
 * @version 0.1
 */
public class PlayerListener implements Listener {

    private HashMap<Player, CreatureSpawner> playerSpawner = new HashMap<>();
    private SpawnerManager spawnerManager = Core.getSpawnerManager();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        final Player player = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            Block b = e.getClickedBlock();
            if(b.getType().equals(Material.MOB_SPAWNER)){
                if(player.isOp()){
                    spawnerManager.openSelectionInventory(player, 1);
                    playerSpawner.put(player, (CreatureSpawner) b.getState());
                }
            }
        }
    }

    @EventHandler
    public void onPlayerSelectType(InventoryClickEvent e){
        final Player player = (Player) e.getWhoClicked();
        if(e.getInventory().equals(spawnerManager.getSpawnerTypeInventory(1)) || e.getClickedInventory().equals(spawnerManager.getSpawnerTypeInventory(2))){
            e.setCancelled(true);
            if(e.getCurrentItem() != null){
                ItemStack item = e.getCurrentItem();
                String displayName = item.getItemMeta().getDisplayName().toLowerCase();

                /*
                If the player click to change the page
                 */
                if(displayName.contains("next") || displayName.contains("previous")){
                    //If contains next so we open page 2, if not we open page 1
                    spawnerManager.openSelectionInventory(player, (displayName.contains("next") ? 2 : 1));
                    return;
                }

                for(SpawnerType type : SpawnerType.values()){
                    if(type.getEggIcon().equals(item)){
                        CreatureSpawner cs = playerSpawner.get(player);
                        spawnerManager.setSpawnerType(cs, type);
                        player.sendMessage("Spawner type modified : §e" + type.getTypeName());
                        cs.update();
                    }
                }
            }
        }
    }

}
