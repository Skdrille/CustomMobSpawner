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

    private static final String NO_PERM = "§cYou are not allowed to use that command";

    public static void sendMessage(Player player, String message){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', PLUGIN + message));
    }

    public static void noPerm(Player player){
        player.sendMessage(NO_PERM);
    }

}
