package fr.llexows.customMobSpawner.commands;

import fr.llexows.customMobSpawner.Core;
import fr.llexows.customMobSpawner.Utils;
import fr.llexows.customMobSpawner.managers.ConfigManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Llexows.
 *
 * @version 0.1
 */
public class BypassCommand extends PluginCommand {

    public BypassCommand() {
        super("bypass", "customspawner.bypass");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if(!(sender instanceof Player)){
            Utils.sendMessage(sender, "Only players can use that command !");
            return;
        }

        Player player = (Player) sender;

        if(!Core.getInstance().getSpawnerManager().isBypass(player)){
            Core.getInstance().getSpawnerManager().setBypass(player, true);
            Utils.sendMessage(player, ConfigManager.getMessage("bypass-enable"));
        }else{
            Core.getInstance().getSpawnerManager().setBypass(player, false);
            Utils.sendMessage(player, ConfigManager.getMessage("bypass-disable"));
        }
    }
}
