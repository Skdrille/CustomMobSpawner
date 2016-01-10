package fr.llexows.customMobSpawner.listeners;

import fr.llexows.customMobSpawner.BuySign;
import fr.llexows.customMobSpawner.Core;
import fr.llexows.customMobSpawner.SpawnerType;
import fr.llexows.customMobSpawner.Utils;
import fr.llexows.customMobSpawner.managers.ConfigManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Llexows.
 *
 * @version 0.1
 */
public class PlayerCreateBuySignListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerCreateBuySign(SignChangeEvent e){
        final Player player = e.getPlayer();
        String[] lines = e.getLines();
        String plugin = lines[0];
        String type = lines[1];

        if(plugin.toLowerCase().contains("[customspawner]")){
            if(player.hasPermission("customspawner.buysign")){
                SpawnerType spawnerType = SpawnerType.getByTypeName(type);
                if(spawnerType != null){
                    double price = 0;
                    /*
                     Check if the 3rd sign's line is an integer
                     */
                    try{

                        price = Double.parseDouble(lines[2]);

                    }catch(NumberFormatException ex){

                        Utils.sendMessage(player, "§cPlease enter a valid price !");

                        return;

                    }
                    BuySign buySign = new BuySign(((Sign) e.getBlock().getState()).getLocation(), spawnerType, price);
                    Core.getInstance().getSpawnerManager().getBuySignsList().add(buySign);
                    /*
                     Update sign
                     */
                    e.setLine(0, Utils.PLUGIN);
                    e.setLine(1, "§2" + spawnerType.getTypeName().toUpperCase());
                    e.setLine(2, (price == 0) ? "§aFree" : ""+price);
                    Utils.sendMessage(player, ConfigManager.getMessage("buy-sign-successful-created"));
                    return;
                }else{
                    Utils.sendMessage(player, "§cInvalid spawner type !");
                }
            }else{
                Utils.sendMessage(player, ConfigManager.getMessage("buy-sign-no-perm"));
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerBuySpawnerFromSign(PlayerInteractEvent e){
        final Player player = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            Block b = e.getClickedBlock();
            if(b.getType().equals(Material.SIGN) || b.getType().equals(Material.SIGN_POST) || b.getType().equals(Material.WALL_SIGN)){
                Sign s = (Sign) b.getState();
                if(Core.getInstance().getSpawnerManager().getBuySignFromLocation(s.getLocation()) != null){
                    BuySign buySign = Core.getInstance().getSpawnerManager().getBuySignFromLocation(s.getLocation());
                    if(Core.getInstance().getEconomyManager().hasEnoughMoney(player, buySign.getPrice())){
                        ItemStack spawner = Core.getInstance().getSpawnerManager().createItemSpawnerWithSpecifiedType(buySign.getSpawnerType());
                        player.getInventory().addItem(spawner);
                        Core.getInstance().getEconomyManager().takeMoney(player, buySign.getPrice());
                    }else{
                        Utils.sendMessage(player, ConfigManager.getMessage("no-enough-money"));
                    }
                }
            }
        }
    }
}
