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
 * @version 1.0.1
 */
public final class CommandHandler implements CommandExecutor {

    /**
     * Set of all the plugin commands
     */
    private Set<PluginCommand> pluginCommands = new HashSet<>();

    public CommandHandler(){
        registerCommand(new GiveCommand());
        registerCommand(new PickaxeCommand());
        registerCommand(new BypassCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        /*
        If the command sender isn't a player
         */
        if(!(sender instanceof Player)){
            sender.sendMessage("§4Only player can use that command !");
            return true;
        }

        Player player = (Player) sender;

        /*
        If the arguments length == 0, then we display the commands list to the player sender
         */
        if(args.length == 0){
            displayPluginCommands(player);
            return true;
        }

        for(PluginCommand pc : pluginCommands){
            if(args[0].equalsIgnoreCase(pc.getName())){
                /*
                If the player doesn't have the command permission
                 */
                if(!player.hasPermission(pc.getPermission())){
                    Utils.sendMessage(player, "§4You are not allowed to use that command");
                    return true;
                }

                pc.execute(player, args);
                return true;
            }
        }

        /*
        If the argument 0 isn't associated with a plugin command, then...
         */
        displayPluginCommands(player);

        return false;
    }

    /**
     * <b>Register a plugin command</b>
     * @param pc the command to register
     */
    private void registerCommand(PluginCommand pc){
        pluginCommands.add(pc);
    }

    /**
     * <b>Display the plugin commands to the target player</b>
     * @param player the target player
     */
    public void displayPluginCommands(Player player){
        String[] commands = new String[]{
                "§b§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---",
                "  §6CustomSpawner commands : ",
                "",
                "  §a/cs give <player> <entity_type>(optional) .",
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
