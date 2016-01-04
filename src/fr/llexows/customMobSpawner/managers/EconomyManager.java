package fr.llexows.customMobSpawner.managers;

import fr.llexows.customMobSpawner.Core;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * Created by Llexows.
 * @version 1.0.1
 */
public final class EconomyManager {

    private Core instance;
    private Economy eco;

    public EconomyManager(Core instance){
        this.instance = instance;
    }

    public boolean setupEconomy() {
        //If the plugin manager can not found Vault plugin, CustomSpawner plugin is stopped.
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

    /**
     * <b>Check if the player has enough money before buy spawner type.</b>
     * @param player the player to check.
     * @param required the money required.
     * @return true if has, not if hasn't.
     */
    public boolean hasEnoughMoney(Player player, double required){
        return eco.getBalance(player) >= required;
    }

    /**
     * <b>Withdraw money from the player account after spawner type buying</b>
     * @param player player to withdraw.
     * @param amount amount to withdray from player account.
     */
    public void takeMoney(Player player, double amount){
        eco.withdrawPlayer(player, amount);
    }


}
