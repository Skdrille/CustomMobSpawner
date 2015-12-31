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
    private final String name;
    private final String permission;

    public PluginCommand(String name, String permission){
        this.name = name;
        this.permission = permission;
    }

    public String getName(){
        return name;
    }

    public String getPermission(){
        return permission;
    }

    public abstract void execute(Player player, String[] args);

}
