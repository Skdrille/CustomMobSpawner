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
        If the arguments length == 0, then we display the commands list to the player sender
         */
        if(args.length == 0){
            displayPluginCommands(sender);
            return true;
        }

        for(PluginCommand pc : pluginCommands){
            if(args[0].equalsIgnoreCase(pc.getName())){
                /*
                If the player doesn't have the command permission
                 */
                if(sender instanceof Player && !((Player) sender).hasPermission(pc.getPermission())){
                    Utils.sendMessage((Player) sender, "§4You are not allowed to use that command");
                    return true;
                }

                pc.execute(sender, args);
                return true;
            }
        }

        /*
        If the argument 0 isn't associated with a plugin command, then...
         */
        displayPluginCommands(sender);

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
     * <b>Display the plugin commands to the command sender</b>
     */
    public void displayPluginCommands(CommandSender sender){
        String[] commands = new String[]{
                "§b§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---§m---",
                "  §6CustomSpawner commands : ",
                "",
                "  §a/cs give <player> <entity_type>(optional) <amount>(optional).",
                "  §7(To give mob spawner(s) to the specific player).",
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
            sender.sendMessage(cmd);
        }
    }
}
