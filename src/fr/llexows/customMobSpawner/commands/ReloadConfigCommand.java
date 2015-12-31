package fr.llexows.customMobSpawner.commands;

import fr.llexows.customMobSpawner.Core;
import fr.llexows.customMobSpawner.Utils;
import org.bukkit.entity.Player;

/**
 * Created by Llexows.
 *
 * @version 0.1
 */
public class ReloadConfigCommand extends PluginCommand {

    public ReloadConfigCommand() {
        super("rconfig", "customspawner.rconfig");
    }

    @Override
    public void execute(Player player, String[] args) {
        instance.reloadConfig();
        Utils.sendMessage(player, "§3Configuration reloaded !");
    }
}
