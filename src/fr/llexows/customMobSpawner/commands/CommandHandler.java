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
        registerCommand(new GiveCommand());
        registerCommand(new PickaxeCommand());
        registerCommand(new BypassCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("§4Only player can use that command !");
            return true;
        }

        Player player = (Player) sender;

        if(args.length == 0){
            displayPluginCommands(player);
            return true;
        }

        for(PluginCommand pc : pluginCommands){
            if(args[0].equalsIgnoreCase(pc.getName())){
                if(!player.hasPermission(pc.getPermission())){
                    Utils.noPerm(player);
                    return true;
                }
                pc.execute(player, args);
                return true;
            }
        }

        displayPluginCommands(player);

        return false;
    }

    private void registerCommand(PluginCommand pc){
        pluginCommands.add(pc);
    }

    public void displayPluginCommands(Player player){
        String[] commands = new String[]{
                "§b§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---",
                "  §6CustomSpawner commands : ",
                "",
                "  §a/cs give <player> ",
                "  §7(To give a mob spawner to the specific player).",
                "",
                "  §a/cs pickaxe <player> .",
                "  §7(To give a pickaxe to the specific player for save a spawner when destroy).",
                "",
                "  §a/cs bypass",
                "  §7(It allows you to break spawners, do the command again to disable the bypass mode).",
                "",
                "§b§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---"
        };
        for(String cmd : commands){
            player.sendMessage(cmd);
        }
    }
}
