package fr.llexows.customMobSpawner.commands;

import fr.llexows.customMobSpawner.SpawnerType;
import fr.llexows.customMobSpawner.Utils;
import fr.llexows.customMobSpawner.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Llexows.
 *
 * @version 0.1
 */
public final class GiveCommand extends PluginCommand {

    public GiveCommand() {
        super("give", "customspawner.give");
    }

    @Override
    public void execute(Player player, String[] args) {
        ItemStack spawner = new ItemStack(Material.MOB_SPAWNER);

        if(args.length >= 2){
            for(Player pl : Bukkit.getOnlinePlayers()){
                if(pl.getName().toLowerCase().equalsIgnoreCase(args[1].toLowerCase())){

                    /*
                    If the player send spawner type argument
                     */
                    if(args.length == 3){
                        String entity_type = args[2];
                        if(entity_type.contains("magmacube")){
                            entity_type = "lavaslime";
                        }
                        for(SpawnerType type : SpawnerType.values()){
                            if(type.getTypeName().equalsIgnoreCase(entity_type)){
                                ItemMeta meta = spawner.getItemMeta();
                                List<String> lore = new ArrayList<>();
                                lore.add("Type : §6" + entity_type.toUpperCase());
                                meta.setLore(lore);
                                spawner.setItemMeta(meta);
                                pl.getInventory().addItem(spawner);
                                Utils.sendMessage(pl, ConfigManager.getMessage("recieved-mob-spawner"));
                                return;
                            }
                        }
                        Utils.sendMessage(player, "§cCannot found entity type : §e" + args[2] + "§c ."); //Entity type not found
                        return;
                    }

                    pl.getInventory().addItem(spawner);
                    Utils.sendMessage(player, ConfigManager.getMessage("give-spawner-player").replace("%player%", pl.getName()));
                    Utils.sendMessage(pl, ConfigManager.getMessage("recieved-mob-spawner"));
                    return;
                }
            }
            Utils.sendMessage(player, ConfigManager.getMessage("player-not-found").replace("%player%", args[1]));
        }
    }
}
