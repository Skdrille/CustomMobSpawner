package fr.llexows.customMobSpawner;

import fr.llexows.customMobSpawner.managers.ConfigManager;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
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
    PIGZOMBIE("PigZombie", "§d§lPigZombie", 57),
    ENDERMAN("Enderman", "§9Enderman", 58),
    CAVE_SPIDER("CaveSpider", "§8§lCave-Spider", 59),
    SILVERFISH("Silverfish", "§7§lSilverfigh", 60),
    BLAZE("Blaze", "§6§lBlaze", 61),
    MAGMA_CUBE("LavaSlime", "§c§lMagma Cube", 62),
    ENDER_DRAGON("EnderDragon", "§1§ken§r§1§lEnder Dragon §ken", 63),
    ENDERMITE("Endermite", "§5§lEndermite", 67),
    GUARDIAN("Guardian", "§l§lGuardian", 68),
    PIG("Pig", "§d§lPig", 90),
    SHEEP("Sheep", "§7§lSheep", 91),
    COW("Cow", "§e§lCow", 92),
    CHICKEN("Chicken", "§e§lChicken", 93),
    SQUID("Squid", "§b§lSquid", 94),
    WOLF("Wolf", "§7§lWolf", 95),
    MUSHROOM_COW("MushroomCow", "§4§lMush§froom", 96),
    SNOWMAN("SnowMan", "§f§lSnowman", 97),
    RABBIT("Rabbit", "§2§lRabbit", 101),
    VILLAGER("Villager", "§6§lVillager", 120);

    private final String type_name;
    private String displayName;
    private ItemStack eggIcon;
    private final double price;
    private boolean available;

    SpawnerType(String type_name, String displayName, int type_id){
        this.type_name = type_name;
        this.displayName = displayName;
        this.price = ConfigManager.getConfig().getDouble("Spawner-type." + type_name.toUpperCase() + ".price");
        this.available = ConfigManager.getConfig().getBoolean("Spawner-type."+type_name.toUpperCase() + ".available");

        ItemStack eggIcon = new ItemStack(Material.MONSTER_EGG, 1, (byte) type_id);
        ItemMeta meta = eggIcon.getItemMeta();
        meta.setDisplayName(displayName);
        List<String> lore = new ArrayList<>();
        lore.add("Price : " + (price == 0 ? "§aFREE" : "§b" + price));
        lore.add("Status : " + (isAvailable() ? "§aAVAILABLE" : "§cNOT AVAILABLE"));
        meta.setLore(lore);
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

    public boolean isAvailable(){
        return available;
    }

}
