package fr.llexows.customMobSpawner.managers;

import fr.llexows.customMobSpawner.Core;
import fr.llexows.customMobSpawner.SpawnerType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by Llexows.
 *
 * @version 0.1
 */
public class SpawnerManager {

    private Inventory inv, inv2;
    private HashMap<CreatureSpawner, SpawnerType> spawners = new HashMap<>();
    private List<UUID> inBypass = new ArrayList<>();
    private HashMap<Player, CreatureSpawner> playerSpawner = new HashMap<>();

    public SpawnerManager(){
        this.inv = Bukkit.createInventory(null, 9*6, "§l♦ Select spawner type ♦");
        this.inv2 = Bukkit.createInventory(null, 9*6,"§l♦ Select spawner type ♦");
}

    public Inventory getSpawnerTypeInventory(int i){
        if(i == 1){
            if(inv == null) {
                inv = Bukkit.createInventory(null, 9 * 6, "§l♦ Select spawner type ♦");
                init();
            }
            return inv;
        }else if(i == 2){
            if(inv2 == null){
                inv2 = Bukkit.createInventory(null, 9*6,"§l♦ Select spawner type ♦");
                init();
            }
            return inv2;
        }
        return null;
    }

    public void init(){
        /*
        Pages
         */
        ItemStack nextPage = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5);
        ItemMeta meta = nextPage.getItemMeta();
        meta.setDisplayName("§a§lNext Page ►");
        nextPage.setItemMeta(meta);

        ItemStack previousPage = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14);
        ItemMeta previousMeta = previousPage.getItemMeta();
        previousMeta.setDisplayName("§c§l◄ Previous Page");
        previousPage.setItemMeta(previousMeta);

        getSpawnerTypeInventory(1).setItem(49, nextPage);
        getSpawnerTypeInventory(2).setItem(49, previousPage);

        /*
        Eggs
         */
        getSpawnerTypeInventory(1).setItem(10, SpawnerType.CREEPER.getEggIcon());
        getSpawnerTypeInventory(1).setItem(11, SpawnerType.SKELETON.getEggIcon());
        getSpawnerTypeInventory(1).setItem(12, SpawnerType.SPIDER.getEggIcon());
        getSpawnerTypeInventory(1).setItem(13, SpawnerType.ZOMBIE.getEggIcon());
        getSpawnerTypeInventory(1).setItem(14, SpawnerType.SLIME.getEggIcon());
        getSpawnerTypeInventory(1).setItem(15, SpawnerType.GHAST.getEggIcon());
        getSpawnerTypeInventory(1).setItem(16, SpawnerType.PIGZOMBIE.getEggIcon());
        getSpawnerTypeInventory(1).setItem(19, SpawnerType.CAVE_SPIDER.getEggIcon());
        getSpawnerTypeInventory(1).setItem(20, SpawnerType.SILVERFISH.getEggIcon());
        getSpawnerTypeInventory(1).setItem(21, SpawnerType.ENDERMAN.getEggIcon());
        getSpawnerTypeInventory(1).setItem(22, SpawnerType.BLAZE.getEggIcon());
        getSpawnerTypeInventory(1).setItem(23, SpawnerType.MAGMA_CUBE.getEggIcon());
        getSpawnerTypeInventory(1).setItem(24, SpawnerType.ENDER_DRAGON.getEggIcon());
        getSpawnerTypeInventory(1).setItem(25, SpawnerType.ENDERMITE.getEggIcon());
        getSpawnerTypeInventory(1).setItem(28, SpawnerType.GUARDIAN.getEggIcon());

        getSpawnerTypeInventory(2).setItem(10, SpawnerType.PIG.getEggIcon());
        getSpawnerTypeInventory(2).setItem(11, SpawnerType.SHEEP.getEggIcon());
        getSpawnerTypeInventory(2).setItem(12, SpawnerType.COW.getEggIcon());
        getSpawnerTypeInventory(2).setItem(13, SpawnerType.CHICKEN.getEggIcon());
        getSpawnerTypeInventory(2).setItem(14, SpawnerType.SQUID.getEggIcon());
        getSpawnerTypeInventory(2).setItem(15, SpawnerType.WOLF.getEggIcon());
        getSpawnerTypeInventory(2).setItem(16, SpawnerType.MUSHROOM_COW.getEggIcon());
        getSpawnerTypeInventory(2).setItem(19, SpawnerType.RABBIT.getEggIcon());
        getSpawnerTypeInventory(2).setItem(20, SpawnerType.VILLAGER.getEggIcon());
    }

    public void openSelectionInventory(Player player, int page){
        player.openInventory(getSpawnerTypeInventory(page));
    }

    public void setSpawnerType(CreatureSpawner theSpawner, SpawnerType spawnerType){
        theSpawner.setCreatureTypeByName(spawnerType.getTypeName());
        spawners.put(theSpawner, spawnerType);
    }

    public SpawnerType getSpawnerType(CreatureSpawner cs){
        if(!spawners.containsKey(cs)) return null;
        return spawners.get(cs);
    }

    public boolean isBypass(Player player){
        return inBypass.contains(player.getUniqueId());
    }

    public void setBypass(Player player, boolean bol){
        if(bol){
            inBypass.add(player.getUniqueId());
        }else{
            inBypass.remove(player.getUniqueId());
        }
    }

    public HashMap<Player, CreatureSpawner> getPlayerSpawner(){
        return playerSpawner;
    }

}
