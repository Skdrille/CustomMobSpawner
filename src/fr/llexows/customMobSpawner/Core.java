package fr.llexows.customMobSpawner;

import fr.llexows.customMobSpawner.commands.CommandHandler;
import fr.llexows.customMobSpawner.managers.ConfigManager;
import fr.llexows.customMobSpawner.managers.SpawnerManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Llexows.
 *
 * @version 0.1
 */
public class Core extends JavaPlugin {

    private static Core instance;
    private static SpawnerManager spawnerManager;
    private ConfigManager configManager;
    private CommandHandler commandHandler;

    @Override
    public void onEnable() {
        instance = this;
        configManager = new ConfigManager(this);
        configManager.setupConfig();
        spawnerManager = new SpawnerManager(this);
        spawnerManager.init();
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        commandHandler = new CommandHandler();
        getCommand("customspawner").setExecutor(commandHandler);
    }

    @Override
    public void onDisable() {

    }

    public static Core getInstance(){
        return instance;
    }

    public static SpawnerManager getSpawnerManager(){
        return spawnerManager;
    }
}
