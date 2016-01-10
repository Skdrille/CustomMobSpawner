package fr.llexows.customMobSpawner.managers;

import fr.llexows.customMobSpawner.Core;
import fr.llexows.customMobSpawner.Utils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

/**
 * Created by Llexows.
 * @version 1.0.1
 */
public final class ConfigManager {

    private Core instance;
    private static FileConfiguration config;
    private static HashMap<String, String> pluginMessages = new HashMap<>();

    public ConfigManager(Core instance){
        this.instance = instance;
        config = instance.getConfig();
    }

    public void setupConfig(){
        config.options().copyDefaults(true);
        instance.saveDefaultConfig();

        /*
        Register custom messages from the config
         */
        registerMesage("recieved-mob-spawner", "recieved-mob-spawner");
        registerMesage("give-spawner-player", "give-spawner-player");
        registerMesage("cannot-break-spawner", "cannot-break-spawner");
        registerMesage("received-magic-pickaxe", "received-magic-pickaxe");
        registerMesage("config-reloaded", "config-reloaded");
        registerMesage("no-enough-money", "no-enough-money");
        registerMesage("spawner-type-not-available", "spawner-type-not-available");
        registerMesage("player-not-found", "player-not-found");
        registerMesage("spawner-type-changed", "spawner-type-changed");
        registerMesage("invalid-amount", "invalid-amount");
        registerMesage("bypass-enable", "bypass-enable");
        registerMesage("bypass-disable", "bypass-disable");
        registerMesage("gave-magic-pickaxe", "gave-magic-pickaxe");
        registerMesage("buy-sign-no-perm", "buy-sign-no-perm");
        registerMesage("buy-sign-successful-created", "buy-sign-successful-created");
    }

    public static String getMessage(String key){
        if(pluginMessages.get(key) == null || pluginMessages.get(key).equals("")) return "Null";
        return pluginMessages.get(key);
    }

    public static FileConfiguration getConfig(){
        return config;
    }

    private void registerMesage(String key, String section){
        pluginMessages.put(key, Utils.format(config.getString("messages."+section)));
    }

}
