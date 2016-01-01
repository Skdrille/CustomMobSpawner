package fr.llexows.customMobSpawner;

import fr.llexows.customMobSpawner.managers.ConfigManager;
import fr.llexows.customMobSpawner.managers.SpawnerManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
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

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerInteract(PlayerInteractEvent e){
        final Player player = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            Block b = e.getClickedBlock();
            if(b.getType().equals(Material.MOB_SPAWNER)){
                if(player.isOp() || player.hasPermission("custommobspawner.use")){
                    spawnerManager.openSelectionInventory(player, 1);
                    playerSpawner.put(player, (CreatureSpawner) b.getState());
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerSelectType(InventoryClickEvent e){
        final Player player = (Player) e.getWhoClicked();
        if(e.getClickedInventory().equals(spawnerManager.getSpawnerTypeInventory(1)) || e.getClickedInventory().equals(spawnerManager.getSpawnerTypeInventory(2))){
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
                    //Check if the clicked egg is associed with a spawnertype icon
                    if(type.getEggIcon().equals(item)){
                        //If the monster type is disabled from the config
                        if(!type.isAvailable()){
                            Utils.sendMessage(player, "§cThis type in not available !");
                            return;
                        }
                        CreatureSpawner cs = playerSpawner.get(player);
                        spawnerManager.setSpawnerType(cs, type);
                        Utils.sendMessage(player, "Spawner type modified : §e" + type.getTypeName());
                        cs.update();
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerBreakSpawner(BlockBreakEvent e){
        final Player player = e.getPlayer();
        final Block b = e.getBlock();
        if(b.getType().equals(Material.MOB_SPAWNER)){
            if(player.getItemInHand() != null){
                ItemStack item = player.getItemInHand();
                if(item.getType().equals(Material.GOLD_PICKAXE) && item.getItemMeta().getDisplayName().equals("§b§l§nMagic Pickaxe")){
                    ItemStack spawner = new ItemStack(Material.MOB_SPAWNER);
                    player.setItemInHand(spawner);
                }else{
                    e.setCancelled(true);
                    Utils.sendMessage(player, ConfigManager.getMessage("cannot-break-spawner"));
                }
            }
        }
    }

}
