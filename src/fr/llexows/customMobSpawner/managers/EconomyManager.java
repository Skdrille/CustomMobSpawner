package fr.llexows.customMobSpawner.managers;

import fr.llexows.customMobSpawner.Core;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * Created by Llexows.
 *
 * @version 0.1
 */
public final class EconomyManager {

    private Core instance;
    private Economy eco;

    public EconomyManager(Core instance){
        this.instance = instance;
    }

    public boolean setupEconomy() {
        if (instance.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = instance.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        eco = rsp.getProvider();
        return eco != null;
    }

    private Economy getEconomy(){
        return eco;
    }

    public boolean hasEnoughMoney(Player player, double required){
        return getEconomy().getBalance(player) >= required;
    }

    public void takeMoney(Player player, double amount){
        getEconomy().withdrawPlayer(player, amount);
    }


}
