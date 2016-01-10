package fr.llexows.customMobSpawner.commands;

import fr.llexows.customMobSpawner.Core;
import fr.llexows.customMobSpawner.Utils;
import fr.llexows.customMobSpawner.exceptions.CustomSpawnerException;
import fr.llexows.customMobSpawner.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Llexows.
 *
 * @version 0.1
 */
public class PickaxeCommand extends PluginCommand {

    public PickaxeCommand() {
        super("pickaxe", "customspawner.pickaxe");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        ItemStack magicPickaxe = null;

        try {
            magicPickaxe = Core.getInstance().getSpawnerManager().getMagicPickaxe();
        } catch (CustomSpawnerException e) {
            e.printStackTrace();
            e.warn(sender);
            return;
        }

        if(args.length == 2){
            Player target = Bukkit.getPlayer(args[1]);

            if(target == null){
                Utils.sendMessage(sender, ConfigManager.getMessage("player-not-found").replace("%player%", args[1]));
            }else{
                target.getInventory().addItem(magicPickaxe);
                Utils.sendMessage(target, ConfigManager.getMessage("received-magic-pickaxe"));
                Utils.sendMessage(sender, ConfigManager.getMessage("gave-magic-pickaxe").replace("%player%", target.getName()));
            }
        }
    }
}

