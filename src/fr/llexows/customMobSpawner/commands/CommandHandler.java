package fr.llexows.customMobSpawner.commands;

import fr.llexows.customMobSpawner.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Llexows.
 *
 * @version 0.1
 */
public final class CommandHandler implements CommandExecutor {

    private Set<PluginCommand> pluginCommands = new HashSet<>();

    public CommandHandler(){
        registerCommand(new ReloadConfigCommand());
        registerCommand(new GiveCommand());
        registerCommand(new PickaxeCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("§4Only player can use that command !");
            return true;
        }

        Player player = (Player) sender;

        for(PluginCommand pc : pluginCommands){
            if(args[0].equalsIgnoreCase(pc.getName())){
                if(!player.isOp() || !player.hasPermission(pc.getPermission())){
                    Utils.noPerm(player);
                    return true;
                }
                pc.execute(player, args);
            }
        }

        return false;
    }

    private void registerCommand(PluginCommand pc){
        pluginCommands.add(pc);
    }
}
