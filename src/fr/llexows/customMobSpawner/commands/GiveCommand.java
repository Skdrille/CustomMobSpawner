package fr.llexows.customMobSpawner.commands;

import fr.llexows.customMobSpawner.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Llexows.
 *
 * @version 0.1
 */
public class GiveCommand extends PluginCommand {

    public GiveCommand() {
        super("give", "customspawner.give");
    }

    @Override
    public void execute(Player player, String[] args) {
        ItemStack spawner = new ItemStack(Material.MOB_SPAWNER);

        switch(args.length){
            case 1:
                player.getInventory().addItem(spawner);
                Utils.sendMessage(player, "§aYou recieved a mob spawner.");
                break;
            case 2:
                int ammount = 0;
                try{
                    ammount = Integer.parseInt(args[1]);
                }catch(NumberFormatException e){
                    Utils.sendMessage(player, "§cPlease enter a valid ammount.");
                    return;
                }
                spawner.setAmount(ammount == 0 ? 1 : ammount);
                player.getInventory().addItem(spawner);
                Utils.sendMessage(player, "§aYou recieved " + ammount + " mob spawners.");
                break;
        }
    }
}
