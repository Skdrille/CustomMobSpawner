package fr.llexows.customMobSpawner.commands;

import fr.llexows.customMobSpawner.Core;
import org.bukkit.entity.Player;

/**
 * Created by Llexows.
 *
 * @version 0.1
 */
public abstract class PluginCommand {

    protected final Core instance = Core.getInstance();

    /**
     * The commande name (use as arg 0 after /customspawner command)
     */
    private final String name;

    /**
     * The command needed permission
     */
    private final String permission;

    public PluginCommand(String name, String permission){
        this.name = name;
        this.permission = permission;
    }

    /**
     * <b>Get the command name</b>
     */
    public String getName(){
        return name;
    }

    /**
     * <b>Get the command needed permission</b>
     */
    public String getPermission(){
        return permission;
    }

    /**
     * <b>Called when a player dispatch the command name</b>
     * @param player the player sender
     * @param args the other arguments
     */
    public abstract void execute(Player player, String[] args);
}
