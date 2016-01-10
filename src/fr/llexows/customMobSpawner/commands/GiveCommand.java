package fr.llexows.customMobSpawner.commands;

import fr.llexows.customMobSpawner.Core;
import fr.llexows.customMobSpawner.SpawnerType;
import fr.llexows.customMobSpawner.Utils;
import fr.llexows.customMobSpawner.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.CommandSender;
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
    public void execute(CommandSender sender, String[] args) {

        if(args.length >= 2){
            ItemStack spawner = null;
            for(Player pl : Bukkit.getOnlinePlayers()){
                if(pl.getName().toLowerCase().equalsIgnoreCase(args[1].toLowerCase())){

                    /*
                    If the player send spawner type argument
                     */
                    if(args.length >= 3){
                        String entity_type = args[2];
                        if(entity_type.toLowerCase().contains("magmacube")){
                            entity_type = "lavaslime";
                        }
                        for(SpawnerType type : SpawnerType.values()){
                            if(type.getTypeName().equalsIgnoreCase(entity_type)){
                                spawner = Core.getInstance().getSpawnerManager().createItemSpawnerWithSpecifiedType(type);
                                /*
                                If the player specify a spawner quantity
                                 */
                                if(args.length == 4){
                                    try{
                                        int amount = Integer.parseInt(args[3]);
                                        spawner.setAmount(amount);
                                    }catch(NumberFormatException e){
                                        Utils.sendMessage(sender, "§cInvalid spawner amount !");
                                        return;
                                    }
                                }

                                pl.getInventory().addItem(spawner);
                                Utils.sendMessage(sender, ConfigManager.getMessage("give-spawner-player").replace("%player%", pl.getName()).replace("%amount%", "" + spawner.getAmount()));
                                Utils.sendMessage(pl, ConfigManager.getMessage("recieved-mob-spawner"));
                                return;
                            }
                        }
                        Utils.sendMessage(sender, "§cCannot found entity type : §e" + args[2] + "§c ."); //Entity type not found
                        return;
                    }

                    spawner = new ItemStack(Material.MOB_SPAWNER);
                    pl.getInventory().addItem(spawner);
                    Utils.sendMessage(sender, ConfigManager.getMessage("give-spawner-player").replace("%player%", pl.getName()).replace("%amount%", "" + spawner.getAmount()));
                    Utils.sendMessage(pl, ConfigManager.getMessage("recieved-mob-spawner"));
                    return;
                }
            }
            Utils.sendMessage(sender, ConfigManager.getMessage("player-not-found").replace("%player%", args[1]));
        }
    }
}
