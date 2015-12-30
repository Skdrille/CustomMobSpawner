package fr.llexows.customMobSpawner;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Llexows.
 *
 * @version 0.1
 */
public enum SpawnerType {
    CREEPER("Creeper", "§a§lCreeper", 50),
    SKELETON("Skeleton", "§7§lSkeleton", 51),
    SPIDER("Spider", "§8§lSpider", 52),
    ZOMBIE("Zombie", "§2§lZombie", 54),
    SLIME("Slime", "§a§lSlime", 55),
    GHAST("Ghast", "§lGhast", 56),
    PIG("Pig", "§d§lPig", 90),
    SHEEP("Sheep", "§7§lSheep", 91),
    COW("Cow", "§e§lCow", 92)
    ;

    private final String type_name;
    private String displayName;
    private ItemStack eggIcon;

    SpawnerType(String type_name, String displayName, int type_id){
        this.type_name = type_name;
        this.displayName = displayName;

        ItemStack eggIcon = new ItemStack(Material.MONSTER_EGG, 1, (byte) type_id);
        ItemMeta meta = eggIcon.getItemMeta();
        meta.setDisplayName(displayName);
        eggIcon.setItemMeta(meta);
        this.eggIcon = eggIcon;
    }

    public String getTypeName(){
        return type_name;
    }

    public String getDisplayName(){
        return displayName;
    }

    public ItemStack getEggIcon(){
        return eggIcon;
    }

}
