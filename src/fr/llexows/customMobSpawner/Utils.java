package fr.llexows.customMobSpawner;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by Llexows.
 *
 * @version 0.1
 */
public final class Utils {

    public static final String PLUGIN = "§8[§6CustomSpawner§8]§r ";

    public static void sendMessage(Player player, String message){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', PLUGIN + message));
    }

    public static void noPerm(Player player){
        player.sendMessage("§4You are not allowed to use that command");
    }

    public static boolean isOnline(Player pl){
        return pl.isOnline();
    }

    public static String format(String msg){
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
