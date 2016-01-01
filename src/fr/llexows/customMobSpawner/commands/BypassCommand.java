package fr.llexows.customMobSpawner.commands;

import fr.llexows.customMobSpawner.Core;
import fr.llexows.customMobSpawner.Utils;
import fr.llexows.customMobSpawner.managers.ConfigManager;
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
    public void execute(Player player, String[] args) {
        if(!Core.getSpawnerManager().isBypass(player)){
            Core.getSpawnerManager().setBypass(player, true);
            Utils.sendMessage(player, ConfigManager.getMessage("bypass-enable"));
        }else{
            Core.getSpawnerManager().setBypass(player, false);
            Utils.sendMessage(player, ConfigManager.getMessage("bypass-disable"));
        }
    }
}
