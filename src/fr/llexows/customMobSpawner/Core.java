package fr.llexows.customMobSpawner;

import fr.llexows.customMobSpawner.commands.CommandHandler;
import fr.llexows.customMobSpawner.listeners.*;
import fr.llexows.customMobSpawner.managers.ConfigManager;
import fr.llexows.customMobSpawner.managers.EconomyManager;
import fr.llexows.customMobSpawner.managers.SpawnerManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Llexows.
 * @version 1.0.1
 */
public class Core extends JavaPlugin {

    private static Core instance;

    private SpawnerManager spawnerManager = new SpawnerManager();

    private ConfigManager configManager = new ConfigManager(this);

    private EconomyManager economyManager = new EconomyManager(this);

    private CommandHandler commandHandler = new CommandHandler();

    @Override
    public void onEnable() {
        instance = this;

        configManager.setupConfig();

        if (!economyManager.setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        spawnerManager.init();

        registerListeners();

        getCommand("customspawner").setExecutor(commandHandler);
    }

    @Override
    public void onDisable() {

    }

    /**
     * Register plugin events.
     */
    private void registerListeners(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerOpenGUIListener(), this);
        pm.registerEvents(new PlayerSelectSpawnerTypeListener(), this);
        pm.registerEvents(new PlayerPlaceSpawnerListener(), this);
        pm.registerEvents(new PlayerBreakSpawnerListener(), this);
        pm.registerEvents(new PlayerCreateBuySignListener(), this);
    }

    /**
     * <b>Get the plugin instance.</b>
     */
    public static Core getInstance(){
        return instance;
    }

    /**
     * <b>Get the spawner manager.</b>
     */
    public SpawnerManager getSpawnerManager(){
        return spawnerManager;
    }

    /**
     * <b>Get the economy manager.</b>
     */
    public EconomyManager getEconomyManager(){
        return economyManager;
    }
}
