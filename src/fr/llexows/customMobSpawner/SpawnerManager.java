package fr.llexows.customMobSpawner;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

/**
 * Created by Llexows.
 *
 * @version 0.1
 */
public class SpawnerManager {

    private Core instance;
    private Inventory inv, inv2;
    private HashMap<CreatureSpawner, SpawnerType> spawners = new HashMap<>();
    public String actual = "§cNONE";

    public SpawnerManager(Core instance){
        this.instance = instance;
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

        getSpawnerTypeInventory(2).setItem(10, SpawnerType.PIG.getEggIcon());
        getSpawnerTypeInventory(2).setItem(11, SpawnerType.SHEEP.getEggIcon());
        getSpawnerTypeInventory(2).setItem(12, SpawnerType.COW.getEggIcon());
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

}
