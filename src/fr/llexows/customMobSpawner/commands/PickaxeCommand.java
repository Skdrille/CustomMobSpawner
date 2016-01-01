package fr.llexows.customMobSpawner.commands;

import fr.llexows.customMobSpawner.Utils;
import fr.llexows.customMobSpawner.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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
    public void execute(Player player, String[] args) {
        ItemStack pickaxe = new ItemStack(Material.getMaterial(ConfigManager.getConfig().getString("Global.magic-pickaxe-material").toUpperCase()));
        ItemMeta meta = pickaxe.getItemMeta();
        pickaxe.setDurability((short) 10000);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setDisplayName(Utils.format(ConfigManager.getConfig().getString("Global.magic-pickaxe-title")));
        List<String> lore = Arrays.asList(Utils.format(ConfigManager.getConfig().getString("Global.magic-pickaxe-desc")));
        meta.setLore(lore);
        pickaxe.setItemMeta(meta);

        if(args.length == 2){
            Player target = Bukkit.getPlayer(args[1]);

            if(target == null){
                Utils.sendMessage(player, ConfigManager.getMessage("player-not-found").replace("%player%", args[1]));
            }else{
                target.getInventory().addItem(pickaxe);
                Utils.sendMessage(target, ConfigManager.getMessage("received-magic-pickaxe"));
                Utils.sendMessage(player, ConfigManager.getMessage("gave-magic-pickaxe").replace("%player%", target.getName()));
            }
        }
    }
}

