package fr.llexows.customMobSpawner;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by Llexows.
 * @version 1.0.1
 */
public final class Utils {

    public static final String PLUGIN = "§8[§6CustomSpawner§8]§r ";

    /**
     * <b>Send a message to the target player with the plugin tag.</b>
     * @param player the target player.
     * @param message the message to send.
     */
    public static void sendMessage(Player player, String message){
        player.sendMessage(format(PLUGIN + message));
    }

    /**
     * <b>Format color codes in the message</b>
     * @param msg the message to format
     * @return the formated message
     */
    public static String format(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
