package fr.llexows.customMobSpawner.listeners;

import fr.llexows.customMobSpawner.Core;
import fr.llexows.customMobSpawner.SpawnerType;
import fr.llexows.customMobSpawner.Utils;
import fr.llexows.customMobSpawner.managers.ConfigManager;
import fr.llexows.customMobSpawner.managers.SpawnerManager;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Llexows.
 *
 * @version 0.1
 */
public class PlayerSelectSpawnerTypeListener implements Listener {

    private SpawnerManager spawnerManager = Core.getInstance().getSpawnerManager();

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerSelectType(InventoryClickEvent e){
        final Player player = (Player) e.getWhoClicked();
        if(e.getClickedInventory().equals(spawnerManager.getSpawnerTypeInventory(1)) || e.getClickedInventory().equals(spawnerManager.getSpawnerTypeInventory(2))){
            e.setCancelled(true);
            if(e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR){
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
                            Utils.sendMessage(player, ConfigManager.getMessage("spawner-type-not-available"));
                            return;
                        }

                        if(!Core.getInstance().getEconomyManager().hasEnoughMoney(player, type.getPrice())){
                            Utils.sendMessage(player, ConfigManager.getMessage("no-enough-money"));
                            return;
                        }

                        CreatureSpawner cs = spawnerManager.getPlayerSpawner().get(player);
                        spawnerManager.setSpawnerType(cs, type);
                        Utils.sendMessage(player, ConfigManager.getMessage("spawner-type-changed").replace("%type%", type.getTypeName()));
                        cs.update();
                        if(type.getPrice() > 0){
                            Core.getInstance().getEconomyManager().takeMoney(player, type.getPrice());
                        }
                    }
                }
            }
        }
    }
}
