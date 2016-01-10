package fr.llexows.customMobSpawner;

import fr.llexows.customMobSpawner.managers.ConfigManager;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Llexows.
 * @version 1.0.1
 */
public enum SpawnerType {
    CREEPER("Creeper", 50),
    SKELETON("Skeleton",  51),
    SPIDER("Spider", 52),
    ZOMBIE("Zombie", 54),
    SLIME("Slime", 55),
    GHAST("Ghast", 56),
    PIGZOMBIE("PigZombie", 57),
    ENDERMAN("Enderman", 58),
    CAVE_SPIDER("CaveSpider", 59),
    SILVERFISH("Silverfish", 60),
    BLAZE("Blaze", 61),
    MAGMA_CUBE("LavaSlime", 62),
    ENDER_DRAGON("EnderDragon", 63),
    ENDERMITE("Endermite", 67),
    GUARDIAN("Guardian", 68),
    PIG("Pig", 90),
    SHEEP("Sheep", 91),
    COW("Cow", 92),
    CHICKEN("Chicken", 93),
    SQUID("Squid", 94),
    WOLF("Wolf", 95),
    MUSHROOM_COW("MushroomCow", 96),
    RABBIT("Rabbit", 101),
    VILLAGER("Villager", 120);

    /**
     * The livingentity's type name which is use as 'id' for set spawner type.
     */
    private final String type_name;

    /**
     * The name displayed as egg item name.
     */
    private final String displayName;

    /**
     * The egg item associated with the livingentity type (ex:  Skeleton egg for skeleton type, ...)
     */
    private final ItemStack eggIcon;

    /**
     * Required money to set this spawner type (load from the config).
     */
    private double price;

    /**
     * Type status, if false, this spawner type cannot be use from the GUI (load from the config).
     */
    private boolean available;

    SpawnerType(String type_name, int type_id){
        this.type_name = type_name;

        this.displayName = Utils.format(ConfigManager.getConfig().getString("Spawner-type." + type_name.toUpperCase() + ".egg-title"));

        this.price = ConfigManager.getConfig().getDouble("Spawner-type." + type_name.toUpperCase() + ".price");

        this.available = ConfigManager.getConfig().getBoolean("Spawner-type."+type_name.toUpperCase() + ".available");

        this.eggIcon = new ItemStack(Material.MONSTER_EGG, 1, (byte) type_id);

        price = ConfigManager.getConfig().getDouble("Spawner-type." + type_name.toUpperCase() + ".price");
        available = ConfigManager.getConfig().getBoolean("Spawner-type."+type_name.toUpperCase() + ".available");

        /*
        Build the type egg item with the price and the available status in the item description.
         */
        ItemMeta meta = eggIcon.getItemMeta();
        meta.setDisplayName(displayName);
        List<String> lore = new ArrayList<>();
        //If price = 0, display 'FREE'
        lore.add("Price : " + (price == 0 ? Utils.format(ConfigManager.getConfig().getString("Global.price-free")) :  (Utils.format(ConfigManager.getConfig().getString("Global.price-color"))) + price));
        lore.add("Status : " + (isAvailable() ?  Utils.format(ConfigManager.getConfig().getString("Global.available-status")): Utils.format(ConfigManager.getConfig().getString("Global.not-available-status"))));
        meta.setLore(lore);
        eggIcon.setItemMeta(meta);
    }

    /**
     * <b>Get type name</b>
     */
    public String getTypeName(){
        return type_name;
    }

    /**
     * <b>Get type egg item title</b>
     */
    public String getDisplayName(){
        return displayName;
    }

    /**
     * <b>Get type egg item</b>
     */
    public ItemStack getEggIcon(){
        return eggIcon;
    }

    /**
     * <b>Get type status</b>
     */
    public boolean isAvailable(){
        return available;
    }

    /**
     * <b>Get type price</b>
     */
    public double getPrice(){
        return price;
    }

    public static SpawnerType getByTypeName(String type_name){
        for(SpawnerType spawnerType : values()){
            if(spawnerType.getTypeName().equalsIgnoreCase(type_name)){
                return  spawnerType;
            }
        }
        return null;
    }

}
