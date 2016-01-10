package fr.llexows.customMobSpawner.exceptions;

import fr.llexows.customMobSpawner.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Llexows.
 * @version 1.0.1
 */
public class CustomSpawnerException extends Exception {

    public CustomSpawnerException(String message){
        super(message);
    }

    /**
     * <b>Warn the player error source an error occurred.</b>
     * @param player error source
     */
    public void warn(CommandSender sender){
        Utils.sendMessage(sender, "§cAn error occurred, please contact server administrator.");
    }

}
