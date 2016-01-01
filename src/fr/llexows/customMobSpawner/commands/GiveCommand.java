package fr.llexows.customMobSpawner.commands;

import fr.llexows.customMobSpawner.Utils;
import fr.llexows.customMobSpawner.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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

        if(args.length == 2){
            for(Player pl : Bukkit.getOnlinePlayers()){
                if(pl.getName().toLowerCase().equalsIgnoreCase(args[1].toLowerCase())){
                    pl.getInventory().addItem(spawner);
                    Utils.sendMessage(player, ConfigManager.getMessage("give-spawner-player").replace("%player%", pl.getName()));
                    return;
                }
            }

            Utils.sendMessage(player, ConfigManager.getMessage("player-not-found").replace("%player%", args[1]));
        }
    }
}
